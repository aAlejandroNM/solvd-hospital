package com.solvd.hospital.util;

public class HospitalLogger {
    private HospitalLogger() {}

    private static class InstanceMolder {
        private static final HospitalLogger INSTANCE = new HospitalLogger();
    }

    public static HospitalLogger getInstance() {
        return InstanceMolder.INSTANCE;
    }

    public void log(String message) {
        System.out.println("[HospitalLogger] " + message);
    }
}

