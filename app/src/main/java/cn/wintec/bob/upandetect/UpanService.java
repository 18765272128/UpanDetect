package cn.wintec.bob.upandetect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpanService extends Service {

    private static String TAG = "bob";
    public UpanService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "In the onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e(TAG, "In the onStartCommand");

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "In the onDestroy");

    }
}
