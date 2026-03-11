
import java.util.ArrayList;

public class Exercise2 {

    interface Powerable {
        void turnOn();
        void turnOff();
    }

    interface Adjustable {
        void setLevel(int level);
    }

    static abstract class SmartDevice implements Powerable {

        protected String deviceName;
        protected boolean isOn;
        protected static int activeDevicesCount = 0;

        public SmartDevice(String deviceName) {
            this.deviceName = deviceName;
            this.isOn = false;
        }

        public abstract void performSelfDiagnostic();

        @Override
        public void turnOn() {
            if (!isOn) {
                isOn = true;
                activeDevicesCount++;
            }
        }

        @Override
        public void turnOff() {
            if (isOn) {
                isOn = false;
                activeDevicesCount--;
            }
        }
    }

    static class SmartLight extends SmartDevice implements Adjustable {

        private int brightness;

        public SmartLight(String deviceName) {
            super(deviceName);
            this.brightness = 0;
        }

        @Override
        public void setLevel(int level) {
            if (!isOn) {
                System.out.println("Cannot adjust: Device is OFF.");
                return;
            }
            if (level >= 0 && level <= 100) {
                brightness = level;
            }
        }

        @Override
        public void performSelfDiagnostic() {
            System.out.println("Checking LED health...");
        }
    }

    static class SmartThermostat extends SmartDevice implements Adjustable {

        private int temperature;

        public SmartThermostat(String deviceName) {
            super(deviceName);
            this.temperature = 70;
        }

        @Override
        public void turnOn() {
            System.out.println("HVAC System Starting...");
            super.turnOn();
        }

        @Override
        public void setLevel(int level) {
            if (!isOn) {
                System.out.println("Cannot adjust: Device is OFF.");
                return;
            }
            if (level >= 60 && level <= 80) {
                temperature = level;
            }
        }

        @Override
        public void performSelfDiagnostic() {
            System.out.println("Running thermostat diagnostics...");
        }
    }

    public static void main(String[] args) {

        ArrayList<SmartDevice> homeHub = new ArrayList<>();

        SmartLight livingRoom = new SmartLight("Living Room");
        SmartLight kitchen = new SmartLight("Kitchen");
        SmartThermostat hallway = new SmartThermostat("Hallway");

        homeHub.add(livingRoom);
        homeHub.add(kitchen);
        homeHub.add(hallway);

        livingRoom.turnOn();
        hallway.turnOn();

        kitchen.setLevel(75);

        System.out.println("Active Devices: " + SmartDevice.activeDevicesCount);

        for (SmartDevice device : homeHub) {
            device.performSelfDiagnostic();
        }
    }
}