package com.sirvar.bluetoothkit

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHeadset
import android.util.Log
import java.io.IOException
import java.util.*

/**
 * BluetoothKit controller to interface with Android BluetoothAdapter and BluetoothSocket
 * @author Rikin Katyal
 */
class BluetoothKit {

    companion object {
        private const val TAG = "BluetoothKit"
    }

    /**
     * The BluetoothAdapter being used
     * @see android.bluetooth.BluetoothAdapter
     */
    var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        private set

    /**
     * The bluetooth socket implementing BluetoothKitSocketInterface
     * @see BluetoothKitSocketInterface
     */
    lateinit var bluetoothSocket: BluetoothKitSocketInterface
        private set

    var isEnabled: Boolean
        /**
         * Checks if bluetooth is enabled
         * @return boolean enabled status
         */
        get() = bluetoothAdapter.isEnabled
        /**
         * Enables or disables bluetooth on device
         */
        set(value) {
            if (value) enable() else disable()
        }

    /**
     * Enables bluetooth on device
     * @return whether or not bluetooth was enabled successfully
     */
    fun enable(): Boolean {
        return bluetoothAdapter.enable()
    }

    /**
     * Disable bluetooth on device
     * @return whether or not bluetooth was disabled successfully
     */
    fun disable(): Boolean {
        return bluetoothAdapter.disable()
    }

    /**
     * Checks if bluetooth is connected
     * @return boolean connection status
     */
    fun isConnected(): Boolean {
        return bluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED
    }

    /**
     * The set of paired BluetoothDevice
     * @see android.bluetooth.BluetoothDevice
     * @see Set
     */
    val pairedDevices: Set<BluetoothDevice>
        get() = bluetoothAdapter.bondedDevices

    /**
     * Get a paired BluetoothDevice by name
     * @param name paired device name to get
     * @return BluetoothDevice the device if it exists, else null
     * @see android.bluetooth.BluetoothDevice
     */
    fun getDeviceByName(name: String): BluetoothDevice? {
        return pairedDevices.singleOrNull { device ->
            device.name == name
        }
    }

    /**
     * Get a paired BluetoothDevice by MAC address
     * @param address MAC address of device to get
     * @return BluetoothDevice the device if it exists, else null
     * @see android.bluetooth.BluetoothDevice
     */
    fun getDeviceByAddress(address: String): BluetoothDevice? {
        return pairedDevices.singleOrNull { device ->
            device.address == address
        }
    }

    /**
     * Connect to a BluetoothDevice with a given string UUID
     * @param device device to connect to
     * @param uuid connection UUID
     * @return whether or not the connection was successful
     */
    fun connect(device: BluetoothDevice, uuid: String): Boolean {
        return connect(device, UUID.fromString(uuid))
    }

    /**
     * Connect to BluetoothDevice with specific UUID or a random one if not specified
     * @param device device to connect to
     * @param uuid connection UUID
     * @return whether or not the connection was successful
     */
    fun connect(device: BluetoothDevice, uuid: UUID = UUID.randomUUID()): Boolean {
        // attempt fast insecure connection
        bluetoothSocket = BluetoothKitSocket(device.createRfcommSocketToServiceRecord(uuid))
        return try {
            bluetoothSocket.connect()
            true
        } catch (e: IOException) {
            Log.w(TAG, "Connection failed. Trying to establish a secure connection")
            try {
                // attempt slow secured connection
                bluetoothSocket = BluetoothKitSecuredSocket(bluetoothSocket.socket)
                try {
                    bluetoothSocket.connect()
                    true
                } catch (e: IOException) {
                    Log.w(TAG, "Secure connection failed. Stopping.", e)
                    false
                }
            } catch (e: IllegalStateException) {
                Log.w(TAG, "Could not create secure connection. Stopping.", e)
                false
            }
        } catch (e: Exception) {
            Log.w(TAG, "Could not connect to device. Stopping.", e)
            false
        }
    }

    /**
     * Write Int byte to the output stream
     * @param b the byte
     */
    fun write(b: Int) {
        bluetoothSocket.outputStream.write(b)
    }

    /**
     * Write ByteArray to the output stream with offset and length
     * @param b the data
     * @param off the start offset in the data (defaults to 0)
     * @param len the number of bytes to write (defaults to the total number of bytes)
     */
    fun write(b: ByteArray, off: Int = 0, len: Int = b.size) {
        bluetoothSocket.outputStream.write(b, off, len)
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
     * @param b the data
     * @param off the start offset in the data (defaults to 0)
     * @param len the max number of bytes to write (defaults to the total number of bytes)
     * @return the total number of bytes read into the buffer, or -1 if there is no more data
     */
    fun read(b: ByteArray, off: Int = 0, len: Int = b.size): Int {
        return bluetoothSocket.inputStream.read(b, off, len)
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
}
