package com.sirvar.bluetoothkit

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Method

/**
 * Secured Bluetooth Socket wrapper with low level method invocation
 * @author Rikin Katyal
 */
class BluetoothKitSecuredSocket(bluetoothSocket: BluetoothSocket) : BluetoothKitSocket(bluetoothSocket) {

    private val TAG = "BluetoothKit"
    private var securedSocket: BluetoothSocket? = null

    init {
        val bluetoothDeviceClass: Class<BluetoothDevice> = bluetoothSocket.remoteDevice.javaClass
        // createRfcommSocket has 2 overloaded methods, we want channel type int
        val intParamType: Array<Class<*>> = arrayOf(Integer.TYPE)
        val method: Method = bluetoothDeviceClass.getMethod("createRfcommSocket", *intParamType)
        securedSocket = method.invoke(socket.remoteDevice, 1) as BluetoothSocket
    }

    override val inputStream: InputStream
        @Throws(IOException::class)
        get() = securedSocket!!.inputStream

    override val outputStream: OutputStream
        @Throws(IOException::class)
        get() = securedSocket!!.outputStream

    @Throws(IOException::class)
    override fun connect() {
        securedSocket!!.connect()
    }

    @Throws(IOException::class)
    override fun close() {
        securedSocket!!.close()
    }

}