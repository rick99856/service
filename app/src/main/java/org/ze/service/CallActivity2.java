package org.ze.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by STU on 2016/5/19.
 */
public  class  CallActivity2  extends Activity {

    @Override
    protected  void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //註冊廣播
        registerReceiver(mHomeKeyEventReceiver,  new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }


    /**
     * 監聽是否點擊了home鍵將客戶端推到後台
     */
    private BroadcastReceiver mHomeKeyEventReceiver =  new  BroadcastReceiver() {
        String SYSTEM_REASON =  "reason" ;
        String SYSTEM_HOME_KEY =  "homekey" ;
        String SYSTEM_HOME_KEY_LONG =  "recentapps" ;

        @Override
        public  void  onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if  (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if  (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    //表示按了home鍵,程序到了後台
                    Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_LONG).show();
                } else  if (TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)){
                    //表示長按home鍵,顯示最近使用的程序列表
                }
            }
        }
    };
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_HOME==keyCode){

            //要寫執行的動作或者任務android.os.Process.killProcess(android.os.Process.myPid());


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onAttachedToWindow(){
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }
}