<img src=".github/logo.png"/>

BluetoothKit is an incredibly **lightweight** and **simple** open source library to interface with Bluetooth devices on Android.

## Initialize
```kotlin
val bluetoothKit: BluetoothKit = BluetoothKit()
```

## Usage

### Enable Bluetooth
```kotlin
bluetoothKit.enable()
```
### Get Device
```kotlin
val device = bluetoothKit.getDeviceByName("Rikin's AirPods")
```
### Connect Device
```kotlin
bluetoothKit.connect(device)
```
*That's it*

## Check out the complete docs in the [wiki](https://github.com/sirvar/bluetoothkit-android/wiki/BluetoothKit-Docs)

Made with ❤ by Rikin Katyal
