package MIT1;

//SmartDeviceControl.java
interface SmartDevice {
 void turnOn();
 void turnOff();
 void getStatus();
}

class SmartLight implements SmartDevice {
 boolean isOn = false;

 public void turnOn() {
     isOn = true;
     System.out.println("Smart Light is ON.");
 }

 public void turnOff() {
     isOn = false;
     System.out.println("Smart Light is OFF.");
 }

 public void getStatus() {
     System.out.println("Smart Light Status: " + (isOn ? "ON" : "OFF"));
 }
}

class SmartFan implements SmartDevice {
 boolean isOn = false;

 public void turnOn() {
     isOn = true;
     System.out.println("Smart Fan is ON.");
 }

 public void turnOff() {
     isOn = false;
     System.out.println("Smart Fan is OFF.");
 }

 public void getStatus() {
     System.out.println("Smart Fan Status: " + (isOn ? "ON" : "OFF"));
 }
}

public class SmartDeviceControl {
 public static void main(String[] args) {
     SmartDevice light = new SmartLight();
     SmartDevice fan = new SmartFan();

     light.turnOn();
     fan.turnOn();

     light.getStatus();
     fan.getStatus();

     light.turnOff();
     fan.turnOff();
 }
}
