package com.example.autofix;

public class AppointmentsModel {

    String date;
    String service;
    String provider;

    public AppointmentsModel(String date, String service, String provider) {
        this.date = date;
        this.service = service;
        this.provider = provider;
    }

    public String getDate() {
        return date;
    }

    public String getService() {
        return service;
    }

    public String getProvider() {
        return provider;
    }
}
