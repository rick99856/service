package org.ze.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by STU on 2016/5/19.
 */
public  class  CallActivity2  extends Activity {
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
//    private BroadcastReceiver mHomeKeyEventReceiver;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);

        setContentView(R.layout.activity_main2);
        registerReceiver(mHomeKeyEventReceiver, new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        Toast.makeText(this,"要等到５秒後才能離開喔!!",Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.finish();

        registerReceiver(mHomeKeyEventReceiver,  new  IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS));


    }
    BroadcastReceiver mHomeKeyEventReceiver =  new  BroadcastReceiver() {
        String SYSTEM_REASON =  "reason" ;
        String SYSTEM_HOME_KEY =  "homekey" ;
        String SYSTEM_HOME_KEY_LONG =  "recentapps";

        @Override
        public  void  onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if  (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if  (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    //表示按了home鍵,程序到了後台
                    Toast.makeText(getApplicationContext(), "home2", Toast.LENGTH_LONG).show();
                } else  if (TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)){
                    //表示長按home鍵,顯示最近使用的程序列表
                }
            }
        }
    };


    /**
     * 監聽是否點擊了home鍵將客戶端推到後台
     */

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                //do something
//                Toast.makeText(this,"back",Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(CallActivity2.this)
                        .setTitle("確認視窗")
                        .setMessage("確定要結束應用程式嗎?")
                        .setIcon(R.drawable.setting)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        Toast.makeText(CallActivity2.this,"很遺憾 你無法離開 你必須接受懲罰",Toast.LENGTH_LONG).show();
                                    }
                                })
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // TODO Auto-generated method stub

                                    }
                                }).show();
                break;

            case KeyEvent.KEYCODE_HOME:
                    Toast.makeText(CallActivity2.this,"home",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onAttachedToWindow(){
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }
}