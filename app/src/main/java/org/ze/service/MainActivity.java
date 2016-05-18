package org.ze.service;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button startButton1,startButton2;
    private Button stopButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        startButton1 = (Button) findViewById(R.id.start1);
        startButton2 = (Button)findViewById(R.id.start2);
        stopButton = (Button) findViewById(R.id.stop);
        startButton1.setOnClickListener(start1ClickListener);
        startButton2.setOnClickListener(start2ClickListener);
        stopButton.setOnClickListener(stopClickListener);

    }

    private Button.OnClickListener start1ClickListener = new Button.OnClickListener() {
        public void onClick(View arg0) {
            //啟動服務
            Intent intent = new Intent(MainActivity.this, NickyService.class);
            Bundle bundle = new Bundle();
            bundle.putInt("fun", 1);
            intent.putExtras(bundle);

            startService(intent);
        }
    };
    private Button.OnClickListener start2ClickListener = new Button.OnClickListener() {
        public void onClick(View arg0) {
            //啟動服務
            Intent intent = new Intent(MainActivity.this, NickyService.class);
            Bundle bundle = new Bundle();
            bundle.putInt("fun", 2);
            intent.putExtras(bundle);

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
}
