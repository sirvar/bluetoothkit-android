package com.sirvar.bluetoothkit

import android.bluetooth.BluetoothSocket
import java.io.InputStream
import java.io.OutputStream

/**
 * Bluetooth Socket wrapper
 * @author Rikin Katyal
 */
open class BluetoothKitSocket(override val socket: BluetoothSocket) : BluetoothKitSocketInterface {

    override val inputStream: InputStream
        get() = socket.inputStream
    override val outputStream: OutputStream
        get() = socket.outputStream
    override val deviceName: String
        get() = socket.remoteDevice.name
    override val deviceAddress: String
        get() = socket.remoteDevice.address

    override fun connect() {
        socket.connect()
    }

    override fun close() {
        socket.close()
    }
}
