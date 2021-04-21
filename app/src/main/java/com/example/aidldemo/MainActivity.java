package com.example.aidldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.aidlserver.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface aidl;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定服务成功回调
            aidl = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //服务断开时回调
            aidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int result = aidl.add(1, 2);
                    Log.d("test", "Aidl callback " + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent();
        //Android 5.0开始，启动服务必须使用显示的，不能用隐式的
        intent.setPackage(getPackageName());
        intent.setComponent(new ComponentName("com.example.aidlserver", "com.example.aidlserver.MyAidlService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}