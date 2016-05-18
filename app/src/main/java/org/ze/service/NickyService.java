package org.ze.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;
import java.util.List;

//繼承android.app.Service
public class NickyService extends Service {
    private Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handler.postDelayed(showTime, 1000);
        handler.postDelayed(com,7000);
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
//            aa();
            handler.postDelayed(this,5000);
            ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            // 取得所有執行中服務
            List<ActivityManager.RunningTaskInfo> apps = manager.getRunningTasks(20);
            // 顯示所有 running 服務列表
            //        showServiceList();
            String a = apps.get(0).baseActivity.getPackageName();
            Log.e("aa",a);
            if(apps.size()>1) {
                //                Log.e("1",apps.get(1).processName);

                String info = "";

                for (ActivityManager.RunningTaskInfo task:apps){
                    info += "一个任务信息开始:\n";
                    info += "启动当前任务的activity名称:" + task.baseActivity.getClassName()+ "\n";
                    info += "当前任务状态的描述:" + task.description+ "\n";
                    info += "当前任务Id:" + task.id+ "\n";
                    info += "任务中所运行的Activity数量,包含已停止的:" + task.numActivities+ "\n";
                    info += "任务中所运行的Activity数量,不包含已停止或不延续运行的:" + task.numRunning+ "\n";
//                    System.out.print(info);
                    Log.e("bb", task.baseActivity.getClassName());

                }
            }
        }
    };
    private  Runnable com = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), CallActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        }
    };



}




