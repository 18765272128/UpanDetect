package cn.wintec.bob.upandetect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button mstart, mstop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mstart = (Button) findViewById(R.id.startserver);
        mstop = (Button) findViewById(R.id.stopserver);
        mstart.setOnClickListener(this);
        mstop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startserver:
                Log.e("bob", "start service button");
                Intent mint = new Intent(this, UpanService.class);
                startService(mint);
                break;
            case R.id.stopserver:
                Log.e("bob", "stop service button");
                Intent mint1 = new Intent(this, UpanService.class);
                startService(mint1);
                break;
            default:
                    Log.e("bob", "not the right button");
                    break;
        }

    }
}
