package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MyAidlService extends Service {
    private IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) {

        }

        @Override
        public int add(int value1, int value2) {
            return value1 + value2;
        }
    };

    public MyAidlService() {
        Log.d("test", "service start");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }


}