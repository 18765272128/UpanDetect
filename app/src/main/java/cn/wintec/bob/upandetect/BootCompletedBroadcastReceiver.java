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
 * Created by wyb on 2018/9/25.
 * Get the broadcast of the Udisk install and mount, then get the path of the udisk that has been mount.
 * Send the **App Folder Path & Shell Folder Path** to the MainActivity through Intent.
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
    /*The name of the first part of the udisk*/
    private static String Upan_Dir = "udisk0";
    /*The folder name that saves the Apps to install*/
    private static String App_Folder = "ApkToInstallWintec";
    /*The folder name that saves the shell to exe */
    private static String Shell_Folder = "ShellToExeWintec";
    /* The absolute path of Udisk has been mounted*/
    private static String Upan_Path;

    Context mcontext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mcontext = context;
        String maction = intent.getAction();
        Log.e(TAG, "In the Broadcast receiver class， Action = " + maction);
        if (maction == Action_boot) {
            Intent mint = new Intent(context, UpanService.class);
//            context.startService(mint);
        } else if (maction == Upan_Mount) {
            Upan_Path = intent.getData().getPath();
            File app_folder = new File(Upan_Path + "/" + Upan_Dir + "/" + App_Folder);
            File shell_folder = new File(Upan_Path + "/" + Upan_Dir + "/" + Shell_Folder);
            if (app_folder.exists() || shell_folder.exists()) {
                Intent mint = new Intent(context, UpanService.class);
                mint.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (app_folder.exists())
                    mint.putExtra("App_Folder", Upan_Path + "/" + Upan_Dir + "/" + App_Folder);
                if (shell_folder.exists())
                    mint.putExtra("Shell_Folder", Upan_Path + "/" + Upan_Dir + "/" + Shell_Folder);
                context.startService(mint);
            }
        }

    }

}
