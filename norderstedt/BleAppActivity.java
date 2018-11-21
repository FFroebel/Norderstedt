package com.example.frfr01.norderstedt;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.frfr01.norderstedt.BLE.BleWrapper;
import com.example.frfr01.norderstedt.BLE.BleWrapperUiCallbacks;


/**
 * Created by Friederike Fröbel on 19.06.2018.
 */

public class BleAppActivity extends AppCompatActivity {
    static private BleAppActivity currentForegroundActivity;
    static private BleWrapper mBleWrapper;


    static public BleWrapper getmBleWrapper(){
        return mBleWrapper;
    }

    static public BleAppActivity getCurrentForegroundActivity(){
        return currentForegroundActivity;
    }
    static private void setCurrentForegroundActivity(BleAppActivity bleAppActivity){
        currentForegroundActivity = bleAppActivity;
    }

    static private void setmBleWrapper(BleAppActivity bleAppActivity){
        //mBleWrapper = BleWrapper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCurrentForegroundActivity(this);
        mBleWrapper = new BleWrapper(this, new BleWrapperUiCallbacks.Null(){
        });
    }
    protected void onResume(){
        super.onResume();
        BleAppActivity.setCurrentForegroundActivity(this);
        BleAppActivity.setmBleWrapper(this);
        if (mBleWrapper.isBtEnabled() == false)
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
            finish();
        }
        mBleWrapper.initialize();
    }
}
