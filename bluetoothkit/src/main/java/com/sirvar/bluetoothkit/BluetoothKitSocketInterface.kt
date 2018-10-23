package com.sirvar.bluetoothkit

import android.bluetooth.BluetoothSocket
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

interface BluetoothKitSocketInterface {

    val inputStream: InputStream
    val outputStream: OutputStream
    val deviceName: String
    val deviceAddress: String
    val socket: BluetoothSocket

    @Throws(IOException::class)
    fun connect()

    @Throws(IOException::class)
    fun close()
}