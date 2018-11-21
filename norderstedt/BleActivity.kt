package com.example.frfr01.norderstedt

import android.Manifest
import android.app.PendingIntent.getActivity
import android.bluetooth.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.frfr01.norderstedt.BLE.BleDefinedUUIDs.Characteristic.NORDIC_UART_TX
import com.example.frfr01.norderstedt.BLE.BleDefinedUUIDs.Service.NORDIC_UART
import com.example.frfr01.norderstedt.BLE.BleNamesResolver
import com.example.frfr01.norderstedt.BLE.BleWrapper
import com.example.frfr01.norderstedt.BLE.BleWrapperUiCallbacks
import kotlinx.android.synthetic.main.activity_ble.*


class BLEActivity : AppCompatActivity() {

    //instantiate the BLE-Wrapper
    //private var mBleWrapper: BleWrapper? = null
    lateinit var device: BluetoothDevice
    lateinit var gatt: BluetoothGatt
    lateinit var c: BluetoothGattCharacteristic
    //    lateinit var mState: mSensorState
    var mBleWrapper: BleWrapper? = null

    private val TAG = "PermissionDemo"
    val PERMISSION_REQUEST_COARSE_LOCATION = 1

/*    companion object {
        var mBleWrapper: BleWrapper? = null
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ble)

        //ACCESS_COARSE_LOCATION
        locationAccess()

        //BLEService.initiliaze()

        initializeBLEWrapper()



        //check if device has Ble
        if (mBleWrapper!!.checkBleHardwareAvailable() == false) {
            Toast.makeText(this, "The device doesnt' support BLE", Toast.LENGTH_SHORT).show()
            finish()
        }

/*        var status = mBleWrapper!!.connect(device.getAddress().toString())
        if (status == false) {
            Log.d("DEBUG", "uiDeviceFound: Connection problem!")
        } else {
            Log.d("DEBUG","Connected with Adafruit")
            //ble_text_intro.setText(getString(R.string.ble_text_on))
            //ble_on.setBackgroundTintList(ColorStateList.valueOf(colorAccent));
            //ble_on.isClickable = true
        }
        if(mBleWrapper!!.isConnected()){
            //ble_on.isClickable = true
            Log.d("CONNECT", "Allready connected")
        }*/

/*        val start_scan = findViewById<Button>(R.id.action_scan)
        start_scan.setOnClickListener{

            mBleWrapper?.startScanning()
            //ble_off.setOnClickListener{
            //    mBleWrapper?.stopScanning()
            //    ble_off.setImageResource(R.drawable.ic_close_black_24dp)
            //}

        }*/
/*        val stop_scan = findViewById<Button>(R.id.action_stop)
        stop_scan.setOnClickListener {
            testButton()
            //mBleWrapper?.isConnected()

        }*/

        val ble_on: View = findViewById(R.id.ble_on)
        //val ble_on = findViewById<FloatingActionButton>(R.id.ble_on)
        ble_on.setOnClickListener {

            //mBleWrapper!!.startScanning()
            val intent = Intent(this, MainActivity::class.java)
            //intent.putExtra(MainActivity., )
            startActivity(intent)
            //write "a" to get blue blinky
            gatt = mBleWrapper?.getGatt()!!
            Log.d("gatt", gatt.toString())
            c = gatt.getService(NORDIC_UART).getCharacteristic(NORDIC_UART_TX)
            val testVal = "a"
            val bytes = testVal.toByteArray()
            mBleWrapper?.writeDataToCharacteristic(c, bytes)
            //toast("Bluetooth verbunden")

        }
        ble_on.isClickable = false
        //ble_on.setColorFilter(Color.GRAY)
        //ble_on.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
        ble_text_intro.setText(getString(R.string.ble_text_off))

        val ble_off: View = findViewById(R.id.ble_off)
        ble_off.setOnClickListener {
            onStopScanClicked()
            //toast("Ohne Bluetooth funktioniert das nicht!")
        }


    }

    private fun testButton() {

        if (mBleWrapper!!.isConnected()) {
            Log.d("CONNECT", "CONNECTION")
            gatt = mBleWrapper?.getGatt()!!
            Log.d("gatt", gatt.toString())
            c = gatt.getService(NORDIC_UART).getCharacteristic(NORDIC_UART_TX)
            val BleOn = "m"
            val bytes = BleOn.toByteArray()
            mBleWrapper?.writeDataToCharacteristic(c, bytes)
            return
        }
    }

    private fun locationAccess() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        PERMISSION_REQUEST_COARSE_LOCATION)
                }
            } else {
                // Permission has already been granted
                Log.d("LOCATION", "bereits erlaubt")
            }
        }
    }

    private fun initializeBLEWrapper() {
        mBleWrapper = BleWrapper(this, object : BleWrapperUiCallbacks.Null() {
            //shows founded devices
            override fun uiDeviceFound(
                device: BluetoothDevice,
                rssi: Int,
                record: ByteArray) {
                //val msg = "uiDeviceFound: " + device.getName() + ", " + rssi + ", " + rssi.toString();
                //Log.d("DEBUG", "uiDeviceFound: " + msg)
                if (device.getName() != null) {
                    if (device.getName().equals("ADAFRUIT FLORA BLE7568") == true) {
                        //var status: Boolean? = null
                        var status = mBleWrapper!!.connect(device.getAddress().toString())
                        if (status == false) {
                            Log.d("DEBUG", "uiDeviceFound: Connection problem!")
                        } else {
                            Log.d("DEBUG", "Connected with Adafruit")
                            ble_text_intro.setText(getString(R.string.ble_text_on))
                            ble_on.setColorFilter(Color.BLACK)
                            //ble_on.setBackgroundTintList(ColorStateList.valueOf(colorAccent));
                            ble_on.isClickable = true
                        }

                    }
                }
            }


            //Shows available BLE-Services
            override fun uiAvailableServices(gatt: BluetoothGatt?,
                                             device: BluetoothDevice?,
                                             services: MutableList<BluetoothGattService>?) {

                if (services != null) {
                    for (service in services) {
                        val serviceName = BleNamesResolver.resolveUuid(service.getUuid().toString())
                        Log.d("LOGTAG", serviceName)
                    }
                }
            }


            override fun uiNewValueForCharacteristic(gatt: BluetoothGatt?,
                                                     device: BluetoothDevice?,
                                                     service: BluetoothGattService?,
                                                     ch: BluetoothGattCharacteristic?,
                                                     strValue: String?,
                                                     intValue: Int,
                                                     rawValue: ByteArray?,
                                                     timestamp: String?) {
                super.uiNewValueForCharacteristic(gatt, device, service, ch, strValue, intValue, rawValue, timestamp)
                Log.d("LOGTAG", "uiNewValueForCharacteristic")
                for (b in rawValue!!) {
                    Log.d("LOGTAG", "Val: " + b)
                }
                //enable Services
                Log.d("LOGTAG", "uiAvailableServices: Enabling services")
                if (gatt != null) {
                    c = gatt.getService(NORDIC_UART).getCharacteristic(NORDIC_UART_TX)
                    Log.d("LOGTAG", "Characteristic" + c)
                }
                val testVal = "a"
                val bytes = testVal.toByteArray()
                mBleWrapper!!.writeDataToCharacteristic(c, bytes)

            }
        })
    }


    override fun onResume() {
        super.onResume()

        //Check for Bluetooth enabled on each resume
        if (mBleWrapper?.isBtEnabled() == false) {
            //Bluetooth is not enabled. Request to user to turn it on
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivity(enableBtIntent)
            finish()
        }
        //init ble wrapper
        mBleWrapper?.initialize()
        if (mBleWrapper?.isBtEnabled == true) {
            onStartScanClicked()
        }
    }

    override fun onPause() {
        super.onPause()

        mBleWrapper?.disconnect()
        mBleWrapper?.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Don't forget to unregister the ACTION_FOUND receiver.
        //unregisterReceiver(mReceiver);
    }

    fun onStopScanClicked() {
        Log.d("DEBUG", "Stoppt scan")
        mBleWrapper?.stopScanning()
        //ble_off.setImageResource(R.drawable.ic_replay_black_24dp)
        ble_off.setOnClickListener {
            //mBleWrapper?.startScanning()
            onStartScanClicked()
            Log.d("DeBug", "Stopp")
        }
    }

    fun onStartScanClicked() {
        Log.d("DEBUG", "scanning")
        mBleWrapper?.startScanning()
        //ble_off.setImageResource(R.drawable.ic_close_black_24dp)
        ble_off.setOnClickListener {
            onStopScanClicked()
            //mBleWrapper?.stopScanning()
            Log.d("DeBug", "Start")
        }
    }

}