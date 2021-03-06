package org.ze.service;

import android.app.Activity;
import android.app.KeyguardManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class CallActivity extends Activity {
    private Button startButton;
    private Button stopButton;
    Handler handler = null;
    private KeyguardManager.KeyguardLock mKeyguardLock;
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
    static final String SYSTEM_REASON = "reason";
    static final String SYSTEM_HOME_KEY = "homekey";//home key

    private static final String TAG = "CallActivity";
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);

        setContentView(R.layout.activity_main2);
        Toast.makeText(this,"要等到20秒後才能離開喔!!",Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.finish();





    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
//            int i = getCurrentRingValue ();   //获取手机当前音量值
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this,"vo down",Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                //do something
                Toast.makeText(this,"vo up",Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_BACK:
                //do something
                Toast.makeText(this,"back",Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_MENU:
                //do something
                Toast.makeText(this,"menu",Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_HOME:
                //invalid...
                Toast.makeText(this,"home",Toast.LENGTH_LONG).show();
                break;
        }
//        return super.onKeyDown (keyCode, event);
        return true;
    }
    @Override
    protected void onPause() {
        super.onPause();
    }



}

