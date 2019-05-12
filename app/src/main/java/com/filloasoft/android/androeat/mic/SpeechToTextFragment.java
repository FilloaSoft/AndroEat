package com.filloasoft.android.androeat.mic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.recipe.HomeFragment;
import com.filloasoft.android.androeat.recipe.RecipeFragment;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class SpeechToTextFragment extends Fragment {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;

    OnAskQuestion mQuestionCallback;
    View mSpeechToTextView = null;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSpeechToTextView = (View) inflater.inflate(R.layout.fragment_speech, container , false);

        mVoiceInputTv = (TextView) mSpeechToTextView.findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) mSpeechToTextView.findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        return mSpeechToTextView;
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    public interface OnAskQuestion{
        void onAskQuestion(View view, String question);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (!result.isEmpty()) {
                        mVoiceInputTv.setText(result.get(0));
                        ChatbotAsyncTask chatbotAsyncTask = new ChatbotAsyncTask(result.get(0));
                        chatbotAsyncTask.execute((Void) null);
                    }
                    else{
                        Toast.makeText(getContext(),"Sorry we couldn't understand you", Toast.LENGTH_SHORT);
                    }
                }
                break;
            }

        }

    }

    public class ChatbotAsyncTask extends AsyncTask<Object, Void, List<String>> {

        private String mQuestion;
        private List<String> answer;

        ChatbotAsyncTask(String question) {
            mQuestion = question;
        }

        @Override
        protected List<String> doInBackground(Object... objects) {
            try {
                final String url;
                mQuestion = mQuestion.replaceAll(" ","+");
                //url = getResources().getString(R.string.recipe_url)+mId;
                url = "http://androeat.dynu.net/recipe/chat?text="+mQuestion;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                answer = restTemplate.getForObject(url, List.class);

                return answer;
            } catch (Exception e) {
                Log.e("Error getting answer -", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<String> answer) {
            TextView answerText = (TextView) mSpeechToTextView.findViewById(R.id.answerOutput);
            if (answer.isEmpty()){
                answerText.setText("I don't have a response a response for your question, sorry");
            }else{
                String fullAnswer = "";
                for (String line : answer){
                    fullAnswer = fullAnswer.concat(line).concat("\n");
                }
                answerText.setText(fullAnswer);
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
