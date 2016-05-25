package org.ze.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button startButton1;
    private Button stopButton;

    public static boolean OnPause = false;
    public static boolean OnResume = false;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        startButton1 = (Button) findViewById(R.id.start1);
        stopButton = (Button) findViewById(R.id.stop);
        startButton1.setOnClickListener(start1ClickListener);
        stopButton.setOnClickListener(stopClickListener);


    }

    private Button.OnClickListener start1ClickListener = new Button.OnClickListener() {
        public void onClick(View arg0) {
            //啟動服務
            Intent intent = new Intent(MainActivity.this, NickyService.class);

            startService(intent);
        }
    };


    private Button.OnClickListener stopClickListener = new Button.OnClickListener() {
        public void onClick(View arg0) {
            //停止服務
            Intent intent = new Intent(MainActivity.this, NickyService.class);
            stopService(intent);
        }
    };
//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
//        Log.d("mark", "onAttachedToWindow-- ");
//    }


    @Override
    protected void onPause() {
        super.onPause();
        OnPause  = true;
        Log.e("Onpause","onpause" + OnPause);

    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        OnResume = true;
        Log.e("Onresume","Onresume" + OnResume);

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Onstop","Onpause is "+OnPause+" OnResume is "+OnResume);

        if(OnPause == true && OnResume == true)
        {
            Log.e("My activity ", " **** home is press *** ");
            //Do Your Home press code Here.
            Toast.makeText(MainActivity.this,"home",Toast.LENGTH_LONG).show();

        }
        OnPause = false;
        OnResume = false;
        Log.e("stop","stop");
    }
}
