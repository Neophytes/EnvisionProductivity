package com.chhavi.envisionproductivity;

/**
 * Created by jigyasa on 19/2/16.
 */

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * Created by jigyasa on 13/12/15.
 */
public class SplashScreen extends Activity {

    private Thread mSplashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ScaleAnimation scaleAnim1 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        scaleAnim1.setDuration(2000);
        scaleAnim1.setStartOffset(400);
        scaleAnim1.setFillAfter(true);

        ScaleAnimation scaleAnim12 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        scaleAnim12.setDuration(2000);
        scaleAnim12.setStartOffset(900);

        TextView justSpecial = (TextView)findViewById(R.id.just_special_text);
        justSpecial.setVisibility(View.VISIBLE);
        justSpecial.setAnimation(scaleAnim1);

        //ImageView owl = (ImageView)findViewById(R.id.imageView1);
        //owl.setVisibility(View.VISIBLE);
        //owl.setAnimation(scaleAnim1);

        //please use this code in java file inside oncreate()
        //r is the parent view of the xml
        //pink=#FF4081
        // yellow=#fcd116

        int c1 = getResources().getColor(R.color.pink);
        int c2 = getResources().getColor(R.color.yellow);

        LinearLayout  r = (LinearLayout)findViewById(R.id.lineralayoutsplashscreen);

        ObjectAnimator anim = ObjectAnimator.ofInt(r, "backgroundColor", c1, c2);
        anim.setDuration(3000);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.start();

        final SplashScreen sPlashScreen = this;

        // The thread to wait for splash screen events
        mSplashThread =  new Thread(){
            @SuppressWarnings("deprecation")
            @Override
            public void run(){
                try {
                    synchronized(this){
                        // Wait given period of time or exit on touch
                        wait(4500);
                    }
                }
                catch(InterruptedException ex){
                }

                finish();

                // Run next activity
                Intent intent = new Intent();
                intent.setClass(sPlashScreen, Rescuetime_Sample.class);
                startActivity(intent);
                //    stop();
            }
        };

        mSplashThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
}