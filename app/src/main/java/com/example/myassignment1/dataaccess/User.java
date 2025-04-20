package com.example.myassignment1.dataaccess;

public class User {
    private String name;
    private String Uname;
    private String password;

    private String location;

    private String phone ;

    public User(String name, String uname,  String password,String phone, String location) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.password = password;
        Uname = uname;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
