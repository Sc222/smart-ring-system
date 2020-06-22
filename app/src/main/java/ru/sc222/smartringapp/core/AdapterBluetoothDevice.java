package ru.sc222.smartringapp.core;

public class AdapterBluetoothDevice {
    private String address;
    private String name;


    public AdapterBluetoothDevice(String address, String name)
    {
        this.address=address;
        this.name=name;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
