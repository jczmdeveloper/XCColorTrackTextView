package com.czm.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.czm.xccolortracktextview.R;
import com.czm.xccolortracktextview.XCColorTrackTextView;

public class MainActivity extends Activity {

    private XCColorTrackTextView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (XCColorTrackTextView) findViewById(R.id.color_track_view);
        mView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.setProgress(mView.getWidth() / 2);
            }
        },100);
        init();
    }
    int mProgress = 0;
    private void init(){

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress = 0;
                new Thread(){
                    @Override
                    public void run() {
                        while(mProgress < mView.getMax()){
                            test();
                            try {
                                Thread.sleep(2);
                                mProgress ++;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });
    }
    private void test(){
        mView.setProgress(mProgress);
    }
}
