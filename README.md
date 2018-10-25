# bluetoothkit-android

## Initialize
```kotlin
val bluetoothKit: BluetoothKit = BluetoothKit()
```
*That's it*

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
