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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    /**
     * The TAG Used for Log
     */
    private static String TAG = "bob";
    Context mcontext;
    String[] mstr = new String[100];
    ListView mlv1;
    ArrayList<HashMap<String, Object>> listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFinishOnTouchOutside(true);
        mlv1 = (ListView) findViewById(R.id.mlv1);
        Intent mint = getIntent();
        mstr = mint.getStringArrayExtra("AppInstallFailed");
//        Log.e(TAG, "length = " + mstr.length);
        listItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("appName", "App Name");
        map1.put("failReason", "Failed Reason");
        listItem.add(map1);
        if (mstr != null) {
            for (int i = 0; i < mstr.length; i++) {
                if (mstr[i] == null) i = mstr.length + 1;
                else {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("appName", mstr[i * 2]);
                    map.put("failReason", mstr[i * 2 + 1]);
                    listItem.add(map);
                }
            }
        }
        if ((listItem != null) && (listItem.size() > 1)) {
            SimpleAdapter adapter1 = new SimpleAdapter(this, listItem, R.layout.itemlist, new String[]{"appName", "failReason"}, new int[]{R.id.appname, R.id.failreason});
            mlv1.setAdapter(adapter1);
        }
//        Shell_Folder = mint.getStringExtra("Shell_Folder");
//        Log.e(TAG, "App folder name is  " + App_Folder);
//        Log.e(TAG, "Shell folder name is  " + Shell_Folder);
//        List<String> mst = ReadFileByLine(Shell_Folder + "/" + Shell_Name);
//        for(int i = 0; i < mst.size(); i++){
//            Log.e(TAG,  i + " = " + mst.get(i));
//        }
//        if (!TextUtils.isEmpty(App_Folder)) {
//            InstallAllTheAPP(App_Folder);
//        }
//        if (!TextUtils.isEmpty(Shell_Folder))
//            RunTheShellFile(Shell_Folder + "/" + Shell_Name);
    }

//    /**
//     * Install all the app in the specified folder
//     */
//    private void InstallAllTheAPP(String mfoler_path) {
//        if (getFilesAllName(mfoler_path)) {
//            listItem = new ArrayList<HashMap<String, Object>>();
//            HashMap<String, Object> map1 = new HashMap<String, Object>();
//            map1.put("appName", "App Name");
//            map1.put("failReason", "Failed Reason");
//            listItem.add(map1);
//            for (int i = 0; i < FileList.size(); i++) {
//                String mApp = FileList.get(i);
//                if (!mInstallOneApp(mApp)) {
//                    HashMap<String, Object> map = new HashMap<String, Object>();
//                    FailMesg = GetReturnMesg(CmdRes.errorMsg);
//                    /* /mnt/usb_storage/USB_DISK1/udisk0/ApkToInstallWintec/ */
//                    map.put("appName", mApp.substring(53));
//                    map.put("failReason", FailMesg);
//                    listItem.add(map);
//                }
//            }
//        }
//        if (listItem != null) {
//            SimpleAdapter adapter1 = new SimpleAdapter(this, listItem, R.layout.itemlist, new String[]{"appName", "failReason"}, new int[]{R.id.appname, R.id.failreason});
//            mlv1.setAdapter(adapter1);
//        }
//        Log.e(TAG, "Install completed");
//    }
//
//    /**
//     * Return the failed reason according to the mString
//     *
//     * @param mString: The failed message returned by the CMD executed class
//     * @return: The reason of failure
//     */
//    private String GetReturnMesg(String mString) {
//        String ret;
//        if (mString.contains("INSTALL_FAILED_INVALID_APK"))
//            ret = "Invalid Apk";
//        else if (mString.contains("INSTALL_FAILED_UPDATE_INCOMPATIBLE"))
//            ret = "Incompatible Update";
//        else if (mString.contains("INSTALL_PARSE_FAILED_BAD_MANIFEST"))
//            ret = "Bad Manifest";
//        else if (mString.contains("INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME"))
//            ret = "Bad Package Name";
//        else if (mString.contains("INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES"))
//            ret = "Inconsistent Certificates";
//        else if (mString.contains("INSTALL_PARSE_FAILED_NOT_APK"))
//            ret = "Not Apk";
//        else
//            ret = "Unknown Reason";
//        return ret;
//    }
//
//    /**
//     * Run the shell file in the specified folder.
//     *
//     * @param mshell: The absolute path of the shell file.
//     * @return; Return value.
//     */
//    private boolean RunTheShellFile(String mshell) {
//        Log.e(TAG, "Begin to Run the Shell， file =  " + mshell);
//
//
//        List<String> mst = ReadFileByLine(mshell);
//        CmdExe = new CommandExecution();
//        for (int i = 0; i < mst.size(); i++) {
//            Log.e(TAG, "size = " + mst.size());
//            Log.e(TAG, i + " = " + mst.get(i));
//            CmdRes = CmdExe.execCommand(mst.get(i), true);
//            Log.e(TAG, "1:" + CmdRes.result + "  2:" + CmdRes.errorMsg + " 3:" + CmdRes.successMsg);
//
//        }
//        return CmdRes.result != 1;
//
//    }
//
//    /**
//     * Install the app specified by the mAppNmae
//     *
//     * @param mAppName: The absolute path of App, Path + name
//     * @return: Install succeed of failed. Succeed return true,or false
//     */
//    private boolean mInstallOneApp(String mAppName) {
//        CmdExe = new CommandExecution();
//        CmdRes = CmdExe.execCommand("pm install -rf " + mAppName, true);
//        Log.e(TAG, "1:" + CmdRes.result + "  2:" + CmdRes.errorMsg + " 3:" + CmdRes.successMsg);
//        if (CmdRes.result == 1) return false;
//        else return true;
//    }
//
//    private List<String> ReadFileByLine(String mfile) {
//        List newList = new ArrayList<String>();
//        String[] mret = {};
//        File file = new File(mfile);
//        int mcount = 0;
//        try {
//            if (file.isFile() && file.exists()) {
//                InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
//                BufferedReader br = new BufferedReader(isr);
//                String linetxt = null;
//                while ((linetxt = br.readLine()) != null) {
//                    if (!"".equals(linetxt)) {
//                        newList.add(mcount, linetxt);
//                        mcount++;
//                    }
//                }
//                isr.close();
//                br.close();
//            } else {
//                Toast.makeText(getApplicationContext(), "Can not find file", Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return newList;
//    }
//
//    /**
//     * 获APK包的信息:版本号,名称,图标 等..
//     *
//     * @param usbPath APK包的绝对路径
//     */
//    private void apkInfo(String usbPath) {
//        Log.e(TAG, "apkInfo: ----" + usbPath);
//        PackageManager pm = mcontext.getPackageManager();
//        PackageInfo pkgInfo = pm.getPackageArchiveInfo(usbPath, PackageManager.GET_ACTIVITIES);
//        if (pkgInfo != null) {
//            ApplicationInfo appInfo = pkgInfo.applicationInfo;
//        /* 必须加这两句，不然下面icon获取是default icon而不是应用包的icon */
//            appInfo.sourceDir = usbPath;
//            appInfo.publicSourceDir = usbPath;
//            // 得到应用名
//            String appName = pm.getApplicationLabel(appInfo).toString();
////            this.appName = appName;
//            // 得到包名
//            String packageName = appInfo.packageName;
//            // 得到版本信息
//            String version = pkgInfo.versionName;
////            this.version = version;
//        /* icon1和icon2其实是一样的 */
//            Drawable icon1 = pm.getApplicationIcon(appInfo);// 得到图标信息
//            Drawable icon2 = appInfo.loadIcon(pm);
//            String pkgInfoStr = String.format("PackageName:%s, Vesion: %s, AppName: %s", packageName, version, appName);
//            Log.i(TAG, String.format("PkgInfo: %s", pkgInfoStr));
//        } else {
//            Log.e(TAG, "apkInfo: null");
//        }
//
//    }
//
//
//    /**
//     * Get all the file name in the specified folder.
//     *
//     * @param strFolder: The absolute path of the specified folder
//     * @return: Return true if the folder is not empty, or return false.
//     */
//    private boolean getFilesAllName(String strFolder) {
//        if (strFolder.equals(""))
//            return false;
//        File file = new File(strFolder);
//        if (!file.exists()) {
//            Log.e(TAG, "The dir is not exist");
//            return false;
//        }
//        File[] files = file.listFiles();
//        if (files.length == 0) {
//            Log.e(TAG, "The dir is empty");
//            return false;
//        }
//        FileList = new ArrayList<>();
//        for (int i = 0; i < files.length; i++) {
//            FileList.add(files[i].getAbsolutePath());
//        }
//        return true;
//    }
}
