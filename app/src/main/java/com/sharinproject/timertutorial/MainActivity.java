package com.sharinproject.timertutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sharinproject.timertutorial.application.MyApplication;



public class MainActivity extends AppCompatActivity implements MyApplication.OnTimeTicking{

    TextView textViewTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set current index activity
        /*
        0 => Main Activity
        1 => Soal1 Activity
        2 => Soal2 Activity
         */
        //Cek apakah timer sudah berjalan
        //Jika sudah implementasikan interface
        MyApplication.getmInstace().continueTimer(this);

        Button buttonStart = (Button) findViewById(R.id.btnStartTimer);
        Button buttonStop = (Button) findViewById(R.id.btnStopTimer);
        Button buttonNext = (Button) findViewById(R.id.buttonNext);

        textViewTime = (TextView) findViewById(R.id.textViewTime);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Waktu countdown = 10000 miliseconds
                //10000 milisecond = 10 detik;
                //kalau mau menit atau jam ubah dulu jadi detik
                //kemudian kalikan 1000
                MyApplication.getmInstace().startTimer(MainActivity.this, 10000);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getmInstace().stopTimer();
            }
        });

        if (getIntent().hasExtra("finish")){
            textViewTime.setText("Selesai");
        }

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Soal1Activity.class));
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
                Log.i(MainActivity.this.getClass().getSimpleName(), currentTime);
                textViewTime.setText(currentTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            textViewTime.setText("Selesai");
        }
    }
}
