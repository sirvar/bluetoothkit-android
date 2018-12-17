package com.sirvar.bluetoothkit

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.util.Log
import java.io.IOException
import java.util.*

/**
 * BluetoothKit controller to interface with Android BluetoothAdapter and BluetoothSocket
 * @author Rikin Katyal
 */
class BluetoothKit() {

    private val TAG = "BluetoothKit"
    private var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothSocket: BluetoothKitSocketInterface

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
     * Connect to BluetoothDevice with random UUID
     * @param BluetoothDevice device to connect to
     */
    fun connect(device: BluetoothDevice) {
        connect(device, UUID.randomUUID())
    }

    /**
     * Connect to BluetoothDevice with UUID as string
     * @param BluetoothDevice device to connect to
     * @param String connection UUID
     */
    fun connect(device: BluetoothDevice, uuid: String) {
        connect(device, UUID.fromString(uuid))
    }

    /**
     * Connect to BluetoothDevice with specific UUID
     * @param BluetoothDevice device to connect to
     * @param UUID connection UUID
     */
    fun connect(device: BluetoothDevice, uuid: UUID) {
        // attempt fast insecure connection
        bluetoothSocket = BluetoothKitSocket(device.createRfcommSocketToServiceRecord(uuid))
        try {
            bluetoothSocket.connect()
        } catch (e: IOException) {
            Log.w(TAG, "Connection failed. Trying to establish a secure connection")
            // attempt slow secured connection
            bluetoothSocket = BluetoothKitSecuredSocket(bluetoothSocket.socket)
            try {
                bluetoothSocket.connect()
            } catch (e: IOException) {
                Log.w(TAG, "Secure connection failed. Stopping.", e)
            } catch (e: Exception) {
                Log.w(TAG, "Could not connect to device. Stopping.", e)
            }
        }
    }

    /**
     * Write ByteArray to the output stream
     * @param ByteArray the data
     */
    fun write(b: ByteArray) {
        bluetoothSocket.outputStream.write(b)
    }

    /**
     * Write ByteArray to the output stream with offset and length
     * @param ByteArray the data
     * @param Int the start offset in the data
     * @param Int  the number of bytes to write
     *
     */
    fun write(b: ByteArray, off: Int, len: Int) {
        bluetoothSocket.outputStream.write(b, off, len)
    }

    /**
     * Write Int byte to the output stream
     * @param Int the byte
     */
    fun write(b: Int) {
        bluetoothSocket.outputStream.write(b)
    }

    /**
     * Reads next byte of data
     * @return the total number of bytes read into the buffer, or -1 if there is no more data
     */
    fun read(): Int {
        return bluetoothSocket.inputStream.read()
    }

    /**
     * Reads up to len bytes of data from the input stream into an array of bytes
     * @param ByteArray the data
     * @param Int the start offset in the data
     * @param Int the max number of bytes to write
     * @return the total number of bytes read into the buffer, or -1 if there is no more data
     */
    fun read(b: ByteArray, off: Int, len: Int): Int {
        return bluetoothSocket.inputStream.read(b, off, len)
    }

    /**
     * Reads some number of bytes from the input stream and stores them into the buffer array b
     * @param ByteArray the buffer into which the data is read
     */
    fun read(b: ByteArray): Int {
        return bluetoothSocket.inputStream.read(b)
    }

    /**
     * Closes all IO streams and terminates the socket.
     * NOTE: does not turn off bluetooth
     */
    fun disconnect() {
        bluetoothSocket.inputStream.close()
        bluetoothSocket.outputStream.close()
        bluetoothSocket.close()
    }

    /**
     * Get the BluetoothAdapter
     * @return BluetoothAdapter
     * @see android.bluetooth.BluetoothAdapter
     */
    fun getBluetoothAdapter(): BluetoothAdapter {
        return bluetoothAdapter
    }

    /**
     * Get the bluetooth socket implementing BluetoothKitSocketInterface
     * @return BluetoothKitSocketInterface
     * @see BluetoothKitSocketInterface
     */
    fun getBluetoothSocket(): BluetoothKitSocketInterface {
        return bluetoothSocket
    }

}