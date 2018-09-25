package cn.wintec.bob.upandetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by adc on 2018/9/25.
 */

public class BootCompletedBroadcastReceiver extends BroadcastReceiver {

    private static String TAG = "bob";
    private static String Action_boot = "android.intent.action.BOOT_COMPLETED";
    private static String Upan_Mount = "android.intent.action.MEDIA_MOUNTED";
    @Override
    public void onReceive(Context context, Intent intent) {
        String maction = intent.getAction();
        Log.e(TAG, "In the Broadcast receiver class， Action = " + maction);
        if(maction == Action_boot ) {
            Intent mint = new Intent(context, UpanService.class);
            context.startService(mint);
        }else  if (maction == Upan_Mount){

        }
    }
}
