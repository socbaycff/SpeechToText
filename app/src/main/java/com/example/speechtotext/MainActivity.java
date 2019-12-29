package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int REQUEST_SPEECH_INPUT = 1;
    TextView textView;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView2 = findViewById(R.id.textView2);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //lay text ve tu startActivityForResult(intent, requestCode)
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SPEECH_INPUT && resultCode == RESULT_OK) {
            ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); // intent tra ve arraylist voi key RecognizerIntent.EXTRA_RESULTS
            textView2.setText( stringArrayListExtra.get(0) );

        }

    }

    public void speechInput(View view) { // mo hop thoai input cua google qua api co san



        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // loai action recognize speech
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); // search standard // KIEU SEARCH
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); // ngon ngu lay theo setting cua may
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "May noi di"); // cau chao khi bat dau
        try {
            startActivityForResult(intent, REQUEST_SPEECH_INPUT); // can dua code do chi co 1 ham onActivityResult callback result
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry your device not supported", Toast.LENGTH_SHORT).show();
        }
    }
}
