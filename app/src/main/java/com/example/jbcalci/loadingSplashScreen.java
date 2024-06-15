package com.example.jbcalci;

import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class loadingSplashScreen extends AppCompatActivity {

    public static final String BILL = "com.example.jbcalci.BILL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_splash_screen);

        // intent shared material
        Intent accept = getIntent();
        String bill = accept.getStringExtra(MainScreen.BILL);

        // Video Animation for Better View
        VideoView videoView=findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.loading);
        videoView.start();


        // Splash Screen for showing loading screen for better animation
        Thread thread=new Thread(){

            public void run()
            {
                try {
                    sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent intent =new Intent(loadingSplashScreen.this , FinalBill.class);
                    intent.putExtra(BILL , bill );
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}