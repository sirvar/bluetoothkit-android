package com.sirvar.bluetoothkit

import android.bluetooth.BluetoothSocket
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Method

/**
 * Secured Bluetooth Socket wrapper with low level method invocation
 * @author Rikin Katyal
 */
class BluetoothKitSecuredSocket(bluetoothSocket: BluetoothSocket) : BluetoothKitSocket(bluetoothSocket) {

    private val securedSocket: BluetoothSocket

    init {
        val bluetoothDeviceClass = bluetoothSocket.remoteDevice.javaClass
        // createRfcommSocket has 2 overloaded methods, we want channel type int
        val intParamType = arrayOf(Integer.TYPE)
        val method: Method? = bluetoothDeviceClass.getMethod("createRfcommSocket", *intParamType)
        val securedSocket = method?.invoke(socket.remoteDevice, 1)
                ?: throw IllegalStateException("Unable to create a secure socket using reflection.")
        this.securedSocket = securedSocket as BluetoothSocket
    }

    override val inputStream: InputStream
        get() = securedSocket.inputStream

    override val outputStream: OutputStream
        get() = securedSocket.outputStream

    override fun connect() {
        securedSocket.connect()
    }

    override fun close() {
        securedSocket.close()
    }
}
