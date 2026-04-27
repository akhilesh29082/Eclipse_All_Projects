package MIT;

//EcoTransportSystem.java
interface Vehicle {
 void start();
 void stop();
 void getFuelEfficiency();
}

class ElectricCar implements Vehicle {
 public void start() {
     System.out.println("Electric Car starts silently.");
 }
 public void stop() {
     System.out.println("Electric Car stops with regenerative braking.");
 }
 public void getFuelEfficiency() {
     System.out.println("Fuel Efficiency: Very High (120 km/charge)");
 }
}

class PetrolCar implements Vehicle {
 public void start() {
     System.out.println("Petrol Car starts with ignition.");
 }
 public void stop() {
     System.out.println("Petrol Car stops with hydraulic brakes.");
 }
 public void getFuelEfficiency() {
     System.out.println("Fuel Efficiency: Moderate (15 km/l)");
 }
}

public class EcoTransportSystem {
 public static void main(String[] args) {
     Vehicle v;

     v = new ElectricCar();
     v.start();
     v.getFuelEfficiency();
     v.stop();

     System.out.println();

     v = new PetrolCar();
     v.start();
     v.getFuelEfficiency();
     v.stop();
 }
}
