package cn.wintec.bob.upandetect;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    /**
     * The TAG Used for Log
     */
    private static String TAG = "bob";

    private static String Upan_Dir = "udisk0";
    private static String Upan_Path;
    private static String APK_Dir = "Mydir";
    private static String APK_Path;
    private static String FailMesg;
    private List<String> FileList;
    private List<String> AppFail;


    CommandExecution CmdExe;
    CommandExecution.CommandResult CmdRes;
    Context mcontext;
    ListView mlv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFinishOnTouchOutside(true);
        mlv1 = (ListView) findViewById(R.id.mlv1);


        InstallAllTheAPP();


    }

    private void InstallAllTheAPP(){
        if(getFilesAllName("/mnt/usb_storage/USB_DISK1/udisk0/Mydir")){
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, FileList);
//            mlv1.setAdapter(adapter);
            ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
            for(int i = 0; i < FileList.size(); i++){
                String mApp = FileList.get(i);
                if(!mInstallOneApp(mApp)){
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    FailMesg = GetReturnMesg(CmdRes.errorMsg);
                    map.put("appName", mApp.substring(40));
                    map.put("failReason",FailMesg);
                    listItem.add(map);
                }
            }
            if( !listItem.isEmpty() ){
                SimpleAdapter adapter1 = new SimpleAdapter(this, listItem, R.layout.itemlist, new String[]{"appName", "failReason"}, new int[]{R.id.appname, R.id.failreason});
                mlv1.setAdapter(adapter1);

            }
        }
    }

    private String GetReturnMesg(String mString){
        String ret;
        if(mString.contains("INSTALL_FAILED_INVALID_APK"))
            ret = "Invalid Apk";
        else if(mString.contains("INSTALL_FAILED_UPDATE_INCOMPATIBLE"))
            ret = "Incompatible Update";
        else if(mString.contains("INSTALL_PARSE_FAILED_BAD_MANIFEST"))
            ret = "Bad Manifest";
        else if(mString.contains("INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME"))
            ret = "Bad Package Name";
        else if(mString.contains("INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES"))
            ret = "Inconsistent Certificates";
        else if(mString.contains("INSTALL_PARSE_FAILED_NOT_APK"))
            ret = "Not Apk";
        else
            ret = "Unknown Reason";
        return ret;
    }

    /**
     * Install App
     */
    private boolean mInstallOneApp(String mAppName){
//        apkInfo(mAppName);
        CmdExe = new CommandExecution();
        CmdRes = CmdExe.execCommand("pm install -rf " + mAppName, true);
        Log.e(TAG, "1:" + CmdRes.result + "  2:" + CmdRes.errorMsg + " 3:" + CmdRes.successMsg);
        if (CmdRes.result == 1) return false;
        else      return true;
    }

    /**
     * 获APK包的信息:版本号,名称,图标 等..
     *
     * @param usbPath APK包的绝对路径
     */
    private void apkInfo(String usbPath) {
        Log.e(TAG, "apkInfo: ----" + usbPath);
        PackageManager pm = mcontext.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(usbPath, PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
        /* 必须加这两句，不然下面icon获取是default icon而不是应用包的icon */
            appInfo.sourceDir = usbPath;
            appInfo.publicSourceDir = usbPath;
            // 得到应用名
            String appName = pm.getApplicationLabel(appInfo).toString();
//            this.appName = appName;
            // 得到包名
            String packageName = appInfo.packageName;
            // 得到版本信息
            String version = pkgInfo.versionName;
//            this.version = version;
        /* icon1和icon2其实是一样的 */
            Drawable icon1 = pm.getApplicationIcon(appInfo);// 得到图标信息
            Drawable icon2 = appInfo.loadIcon(pm);
            String pkgInfoStr = String.format("PackageName:%s, Vesion: %s, AppName: %s", packageName, version, appName);
            Log.i(TAG, String.format("PkgInfo: %s", pkgInfoStr));
        } else {
            Log.e(TAG, "apkInfo: null");
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
        File[] files1 = new File[0];
        for(int i = 0; i<files.length; i++){
            FileList.add(files[i].getAbsolutePath());
        }
        return true;
    }
}
