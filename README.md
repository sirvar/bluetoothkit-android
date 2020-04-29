<img src=".github/logo.png"/>

<p align="center">
  <a href="https://android-arsenal.com/details/1/7283"><img src="https://img.shields.io/badge/Android%20Arsenal-BluetoothKit-blue.svg?style=flat" border="0" alt="Android Arsenal"></a>
  <a href="https://jitpack.io/#sirvar/bluetoothkit-android"><img src="https://jitpack.io/v/sirvar/bluetoothkit-android.svg" alt="JitPack"></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License MIT"></a>
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
  implementation 'com.github.sirvar:bluetoothkit-android:v0.3'
}
```
Alternatively, you can add this to maven
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
and add the dependency
```xml
<dependency>
  <groupId>com.github.sirvar</groupId>
  <artifactId>bluetoothkit-android</artifactId>
  <version>0.3</version>
</dependency>

```
## Usage

### Initialize
```kotlin
val bluetoothKit = BluetoothKit()
```

### Enable Bluetooth
```kotlin
bluetoothKit.enable()
```
Alternatively, you may also do:
```kotlin
bluetoothKit.isEnabled = true
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

### Check out the complete docs [here](https://sirvar.github.io/bluetoothkit-android/com.sirvar.bluetoothkit/-bluetooth-kit/index.html)

#### Apps currently using BluetoothKit:
- https://play.google.com/store/apps/details?id=com.sirvar.quickconnect

*If you're using BluetoothKit, feel free to make a PR and add it to the list*

Made with ❤ by Rikin Katyal
