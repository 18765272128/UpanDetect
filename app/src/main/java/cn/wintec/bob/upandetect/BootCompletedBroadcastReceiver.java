package cn.wintec.bob.upandetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adc on 2018/9/25.
 */

public class BootCompletedBroadcastReceiver extends BroadcastReceiver {

    /**
     * The TAG Used for Log
     */
    private static String TAG = "bob";

    /**
     * The Boot Completed action
     */
    private static String Action_boot = "android.intent.action.BOOT_COMPLETED";
    /**
     * the Udisk mounted action
     */
    private static String Upan_Mount = "android.intent.action.MEDIA_MOUNTED";

    private static String Upan_Dir = "udisk0";
    private static String Folder_Name = "ApkShellWintec";
    private static String Upan_Path;

    Context mcontext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mcontext = context;
        String maction = intent.getAction();
        Log.e(TAG, "In the Broadcast receiver class， Action = " + maction);
        if(maction == Action_boot ) {
            Intent mint = new Intent(context, UpanService.class);
            context.startService(mint);
        }else  if (maction == Upan_Mount){
//            intent.getData().getPath();
            Upan_Path = intent.getData().getPath();
            File file = new File(Upan_Path + "/" + Upan_Dir + "/" + Folder_Name);
            if (file.exists()) {
                Intent mint = new Intent(context, MainActivity.class);
                mint.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mint.putExtra("Udisk_path", Upan_Path);
                context.startActivity(mint);
            }
        }

    }


}
