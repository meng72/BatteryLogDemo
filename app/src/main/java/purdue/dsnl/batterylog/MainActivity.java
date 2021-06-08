package purdue.dsnl.batterylog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "BatteryLog";
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BatteryManager bm = (BatteryManager) this.getSystemService(BATTERY_SERVICE);

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, Long.toString(bm.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)));
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }
}