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

/**
 * Created by wyb on 2018/9/25.
 * 默认打包到系统中，作为系统应用。
 * 实现以下功能：
 * V1.0.0
 * 1、自动安装指定路径下的apk，将安装失败的apk安装包名和对应的失败原因显示出来。
 * 2、自动运行指定路径下的脚本文件，按行读取后执行。
 */
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
    }
}
