package com.sharinproject.timertutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sharinproject.timertutorial.application.MyApplication;


public class Soal1Activity extends AppCompatActivity implements MyApplication.OnTimeTicking{

    TextView textViewTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal1);

        Button buttonBack = (Button) findViewById(R.id.buttonBack);
        Button buttonNext = (Button) findViewById(R.id.buttonNext);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        //Implementasikan OnTimeTickingnya
        MyApplication.getmInstace().continueTimer(this);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Soal1Activity.this, MainActivity.class));
                finish();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Soal1Activity.this, Soal2Activity.class));
                finish();
            }
        });


    }

    @Override
    public void OnTimeTicked(boolean isFinished, long miliseconds) {
        if (!isFinished){
            try {
                Log.w("Milis", String.valueOf(miliseconds));
                String timeFormat = String.format("%%0%dd", 2);
                String seconds = String.format(timeFormat, (miliseconds / 1000) % 60);
                String minutes = String.format(timeFormat, ((miliseconds / (1000*60)) % 60));
                String hours = String.format(timeFormat, ((miliseconds / (1000*60*60)) % 24));
                String currentTime = hours + ":" + minutes + ":" + seconds;
                Log.i(Soal1Activity.this.getClass().getSimpleName(), currentTime);
                textViewTime.setText(currentTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            //kalau selesai kembali ke mainactivity
            //tindakannya ada di bagian getIntent.hasExtra("finish")
            startActivity(new Intent(Soal1Activity.this, MainActivity.class).putExtra("finish", true));
        }
    }
}
