package com.example.cabp.ourapp;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech speak;
    EditText tex;
    Button button;
    SeekBar seek;
    SeekBar spd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tex=(EditText)findViewById(R.id.editText);
        button=(Button)findViewById(R.id.but);
        seek=(SeekBar)findViewById(R.id.pitch);
        spd=(SeekBar)findViewById(R.id.speed);

        //instantiating object for TextToSpeak
        speak=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){
                    speak.setLanguage(Locale.ENGLISH);
                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spk=tex.getText().toString();
                speak.speak(spk,TextToSpeech.QUEUE_FLUSH,null);
                float pitch = (float) seek.getProgress()/50;
                if (pitch<0.1)pitch= 0.1f;

                speak.setPitch(pitch);

                float speed=(float)spd.getProgress()/50;
                if (speed<0.1)speed=0.1f;
                speak.setSpeechRate(speed);


            }
        });



    }
//Closing the TextToSpeak
    @Override
    protected void onDestroy() {
        if (speak!=null){
            speak.stop();
            speak.shutdown();
        }
        super.onDestroy();
    }
}
