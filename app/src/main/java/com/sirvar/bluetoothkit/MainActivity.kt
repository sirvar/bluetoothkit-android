package com.sirvar.bluetoothkit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG: String = "MActivity"
    lateinit var bluetoothKit: BluetoothKit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothKit = BluetoothKit()

        enable.setOnClickListener { bluetoothKit.enable() }
        disable.setOnClickListener { bluetoothKit.disable() }
    }
}
