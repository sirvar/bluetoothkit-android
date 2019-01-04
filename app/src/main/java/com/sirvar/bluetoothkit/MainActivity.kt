package com.sirvar.bluetoothkit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bluetoothKit = BluetoothKit()

        enable.setOnClickListener { bluetoothKit.enable() }
        disable.setOnClickListener { bluetoothKit.disable() }
    }
}
