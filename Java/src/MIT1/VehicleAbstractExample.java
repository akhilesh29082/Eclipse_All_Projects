package MIT1;

//VehicleAbstractExample.java
abstract class Vehicle {
 abstract void start();
 abstract void stop();
}

class Car extends Vehicle {
 void start() {
     System.out.println("Car starts with a key.");
 }
 void stop() {
     System.out.println("Car stops with brake pedal.");
 }
}

class Bike extends Vehicle {
 void start() {
     System.out.println("Bike starts with a kick.");
 }
 void stop() {
     System.out.println("Bike stops with hand brake.");
 }
}

class Truck extends Vehicle {
 void start() {
     System.out.println("Truck starts with heavy ignition.");
 }
 void stop() {
     System.out.println("Truck stops with air brakes.");
 }
}

public class VehicleAbstractExample {
 public static void main(String[] args) {
     Vehicle v;

     v = new Car();
     v.start();
     v.stop();

     v = new Bike();
     v.start();
     v.stop();

     v = new Truck();
     v.start();
     v.stop();
 }
}
 