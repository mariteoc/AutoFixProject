package com.example.autofix;

public class RemindersModel {
    String date;
    String time;
    String service;

    public RemindersModel(String date, String time, String service) {
        this.date = date;
        this.time = time;
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getService() {
        return service;
    }
}
