package com.example.autofix;

public class ProvidersModel {
    String provName;
    String provCity;
    String provPhone;


    public ProvidersModel(String provName, String provCity, String provPhone) {
        this.provName = provName;
        this.provCity = provCity;
        this.provPhone = provPhone;
    }

    public String getProvName() {
        return provName;
    }

    public String getProvCity() {
        return provCity;
    }

    public String getProvPhone() {
        return provPhone;
    }
}
