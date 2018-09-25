package cn.wintec.bob.upandetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
    private static String Upan_Path;
    private static String APK_Dir = "Mydir";
    private static String APK_Path;
    private List<String> FileList;
    @Override
    public void onReceive(Context context, Intent intent) {
        String maction = intent.getAction();
        Log.e(TAG, "In the Broadcast receiver class， Action = " + maction);
        if(maction == Action_boot ) {
            Intent mint = new Intent(context, UpanService.class);
            context.startService(mint);
        }else  if (maction == Upan_Mount){
            Log.e(TAG, "str = " + intent.getData().getPath());
            Upan_Path = intent.getData().getPath() + "/" + Upan_Dir;
            APK_Path = Upan_Path + "/" + APK_Dir;
            getFilesAllName(APK_Path);
        }
    }

    private boolean getFilesAllName(String strFolder){
        File file = new File(strFolder);
        if (!file.exists()) {
            Log.e(TAG, "The dir is not exist");
            return false;
        }
        File[] files=file.listFiles();
        if (files.length == 0){
            Log.e(TAG, "The dir is empty");
            return false;
        }
        FileList = new ArrayList<>();
        for(int i = 0; i<files.length; i++){
            FileList.add(files[i].getAbsolutePath());
        }

        return true;
    }
}
