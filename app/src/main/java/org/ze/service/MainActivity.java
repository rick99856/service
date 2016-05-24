package org.ze.service;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button startButton1;
    private Button stopButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
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
}
