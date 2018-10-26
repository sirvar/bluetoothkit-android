<img src=".github/logo.png"/>

<p align="center">
<!--   <a href="https://android-arsenal.com/details/1/6255"><img src="https://img.shields.io/badge/Android%20Arsenal-robin-blue.svg?style=flat" alt="Build Status"></a> -->
  <a href="https://jitpack.io/#sirvar/bluetoothkit-android"><img src="https://jitpack.io/v/sirvar/bluetoothkit-android.svg" alt="Coverage Status"></a>
</p>

BluetoothKit is an incredibly **lightweight** and **simple** open source library to interface with Bluetooth devices on Android.

## Setup
Add this to your `build.gradle`
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
then add the dependency
```gradle
dependencies {
  implementation 'com.github.sirvar:bluetoothkit-android:Tag'
}
```
## Usage

### Initialize
```kotlin
val bluetoothKit: BluetoothKit = BluetoothKit()
```

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
