package com.sharinproject.timertutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sharinproject.timertutorial.application.MyApplication;


public class Soal2Activity extends AppCompatActivity implements MyApplication.OnTimeTicking{

    TextView textViewTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal2);

        Button buttonBack = (Button) findViewById(R.id.buttonBack);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        //Implementasikan OnTimeTickingnya
        MyApplication.getmInstace().continueTimer(this);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Soal2Activity.this, Soal1Activity.class));
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
                Log.i(Soal2Activity.this.getClass().getSimpleName(), currentTime);
                textViewTime.setText(currentTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            //kalau selesai kembali ke mainactivity
            //tindakannya ada di bagian getIntent.hasExtra("finish")
            startActivity(new Intent(Soal2Activity.this, MainActivity.class).putExtra("finish", true));
        }
    }
}
