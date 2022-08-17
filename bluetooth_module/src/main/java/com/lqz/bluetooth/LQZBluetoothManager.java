package com.lqz.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Set;

public class LQZBluetoothManager {
    private static LQZBluetoothManager instance;

    public static synchronized LQZBluetoothManager getInstance() {
        if (instance == null) {
            instance = new LQZBluetoothManager();
        }
        return instance;
    }

    BluetoothAdapter mBluetoothAdapter;

    public LQZBluetoothManager() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 蓝牙是否开启
     *
     * @return 蓝牙开启状态
     */
    public boolean isBluetoothEnabled() {
        if (mBluetoothAdapter != null) {
            return mBluetoothAdapter.isEnabled();
        }
        return false;
    }

    /**
     * 连接蓝牙
     */
    public void enable() {
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.enable();
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    /**
     * 是否支持蓝牙
     *
     * @return
     */
    public boolean isSupportBluetooth() {
        return mBluetoothAdapter != null;
    }

    /**
     * 查询已配对设备
     */
    public Set<BluetoothDevice> getBondedDevices() {
        if (mBluetoothAdapter != null) {
            return mBluetoothAdapter.getBondedDevices();
        }
        return null;
    }

    /**
     * 扫描蓝牙设备
     */
    public void startDiscovery() {
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.startDiscovery();
        }
    }

    /**
     * 取消扫描
     */
    public void cancelDiscovery() {
        if (mBluetoothAdapter != null && isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
    }

    public boolean isDiscovering() {
        if (mBluetoothAdapter != null) {
            return mBluetoothAdapter.isDiscovering();
        }
        return false;
    }


    /*// Register for broadcasts when a device is discovered.
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
    registerReceiver(receiver, filter);*/
    /*// Don't forget to unregister the ACTION_FOUND receiver.
    unregisterReceiver(receiver);*/
    /**
     * 广播接收器
     */
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //todo 扫描到设备
                //放入list中或者通过接口回调通知到上层
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //todo 扫描完成
            }
        }
    };
}
