package org.ze.service;

import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.WindowManager;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

//繼承android.app.Service
public class NickyService extends Service {
    private Handler handler = new Handler();
//    final AlertDialog alertDialog = getAlertDialog("這是一個對話框","請選擇......");
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handler.postDelayed(showTime, 1000);
        handler.postDelayed(com,3000);
        super.onStart(intent, startId);

    }



    @Override
    public void onDestroy() {
        handler.removeCallbacks(showTime);
        super.onDestroy();
    }

    private Runnable showTime = new Runnable() {
        public void run() {
            //log目前時間
            Log.i("time:", new Date().toString());

            handler.postDelayed(this, 2000);
        }
    };
    private  Runnable com = new Runnable() {
        @Override
        public void run() {
            String foregroundProcess = "";
            UsageStatsManager mUsageStatsManager = (UsageStatsManager)getSystemService(USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            // We get usage stats for the last 10 seconds
            List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*10, time);
            // Sort the stats by the last time used
            if(stats != null) {
                SortedMap<Long,UsageStats> mySortedMap = new TreeMap<Long,UsageStats>();
                for (UsageStats usageStats : stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(),usageStats);
                }
                if(mySortedMap != null && !mySortedMap.isEmpty()) {
                    String topPackageName =  mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                    foregroundProcess = topPackageName;

                }
            }
            Log.e("ss", foregroundProcess);
            if(foregroundProcess.equals("whitebird.ptt_now")){
                String a = foregroundProcess;

//                showDialog("ptt","ptt");
                showAlertDialog();
            }
            else{
                handler.postDelayed(this, 5000);
            }


        }
    };


    public void showDialog(String title,String message){
        Log.i("service","show dialog function");
        Log.i("service", "dialog error msg:" + message);
//        errmsg.setText(Html.fromHtml(message));
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("不可以用", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//設定提示框為系統提示框
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY);
        alert.setCanceledOnTouchOutside(true);
        alert.show();
    }
    private void showAlertDialog() {

        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("MyKeyguardLock");
        kl.disableKeyguard();

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK| PowerManager.ACQUIRE_CAUSES_WAKEUP
                | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
        wakeLock.acquire();


//        final CharSequence[] items = { getString(R.string.test1), getString(R.string.tes2), getString(R.string.test3), getString(R.string.cancel) };
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Title");

        AlertDialog alert = builder.create();
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alert.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        alert.show();

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface arg0) {

            }
        });


        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });


    }



}




