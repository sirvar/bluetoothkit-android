package com.sirvar.bluetoothkit

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket

class BluetoothKit() {

    private var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothSocket: BluetoothSocket

    init {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    }

    /**
     * Enables bluetooth on device
     */
    fun enable(): Boolean {
        return bluetoothAdapter.enable()
    }

    /**
     * Disable bluetooth on device
     */
    fun disable(): Boolean {
        return bluetoothAdapter.disable()
    }

    /**
     * Checks if bluetooth is enabled
     * @return boolean enabled status
     */
    fun isEnabled(): Boolean {
        return bluetoothAdapter.isEnabled
    }

    /**
     * Gets Set of paired BluetoothDevice
     * @see android.bluetooth.BluetoothDevice
     * @see Set
     */
    fun getPairedDevices(): Set<BluetoothDevice> {
        return bluetoothAdapter.bondedDevices
    }

    /**
     * Get paired BluetoothDevice by name
     * @param name paired device name to get
     * @return BluetoothDevice the device if it exists, else null
     * @see android.bluetooth.BluetoothDevice
     */
    fun getDeviceByName(name: String): BluetoothDevice? {
        val devices: Set<BluetoothDevice> = getPairedDevices()
        for (device: BluetoothDevice in devices)
            if (device.name.equals(name))
                return device
        return null
    }

    /**
     * Get paired BluetoothDevice by MAC address
     * @param address MAC address of device to get
     * @return BluetoothDevice the device if it exists, else null
     * @see android.bluetooth.BluetoothDevice
     */
    fun getDeviceByAddress(address: String): BluetoothDevice? {
        val devices: Set<BluetoothDevice> = getPairedDevices()
        for (device: BluetoothDevice in devices)
            if (device.address.equals(address))
                return device
        return null
    }

    /**
     * Get the BluetoothAdapter
     * @return BluetoothAdapter
     * @see android.bluetooth.BluetoothAdapter
     */
    fun getBluetoothAdapter(): BluetoothAdapter {
        return bluetoothAdapter
    }

}