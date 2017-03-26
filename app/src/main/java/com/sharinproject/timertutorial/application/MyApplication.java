package com.sharinproject.timertutorial.application;

import android.app.Application;
import android.os.CountDownTimer;

/**
 * Created by Hermansyah on 26/03/2017.
 */

public class MyApplication extends Application {
    private static  MyApplication mInstace;
    private OnTimeTicking onTimeTicking;
    private MyCountDownTimer myCountDownTimer;

    public MyApplication() {
        //Daftarkan MyApplicatin di manifest => AndroidManifest.xml
        //lihat di bagian android:name
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstace = this;
    }

    public static synchronized MyApplication getmInstace(){
        return mInstace;
    }

    public void startTimer(OnTimeTicking onTimeTicking, long milisInFuture){
        this.onTimeTicking = onTimeTicking;
        if (myCountDownTimer!=null){
            myCountDownTimer.cancel();
        }
        /*
        5000 milisecond = 5 second => waktu hitung mundur
        1000 milisecond = 1 second => interval penghitungan waktu mundur
         */
        myCountDownTimer = new MyCountDownTimer(milisInFuture,1000);
        myCountDownTimer.start();
    }

    public void continueTimer(OnTimeTicking onTimeTicking){
        if (myCountDownTimer != null){
            this.onTimeTicking = onTimeTicking;
        }
    }

    public void stopTimer(){
        if (myCountDownTimer!=null){
            myCountDownTimer.cancel();
            myCountDownTimer = null;
        }
    }

    public interface OnTimeTicking {
        void OnTimeTicked(boolean isFinished, long miliseconds);
    }

    class MyCountDownTimer extends CountDownTimer{
        /*
        millisInFuture => waktu hitung mundur
        countDownInterval => interval penghitungan waktu mundur
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            MyApplication.getmInstace().onTimeTicking.OnTimeTicked(false, l);
        }

        @Override
        public void onFinish() {
            MyApplication.getmInstace().onTimeTicking.OnTimeTicked(true, 0);
        }
    }
}
