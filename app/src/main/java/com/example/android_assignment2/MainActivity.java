package com.example.android_assignment2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    Handler waitMsgHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TextView tv1 =  findViewById(R.id.tv1);
            tv1.setText("Nice Job imran !");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1Clicked(View view){

        Runnable r = new Runnable() {
            @Override
            public void run() {

                long futureTime = (System.currentTimeMillis()+ 11000);//11 sec

                while (System.currentTimeMillis()< futureTime){

                    synchronized(this){
                        try {
                            wait(futureTime - System.currentTimeMillis());
                        }catch(Exception e){}
                    }
                }
                waitMsgHandler.sendEmptyMessage(0);






            }
        };

        Thread waitThread = new Thread(r);
        waitThread.start();

        Toast.makeText(MainActivity.this,"Running thread fo 10 seconds",Toast.LENGTH_LONG).show();


    }



}
