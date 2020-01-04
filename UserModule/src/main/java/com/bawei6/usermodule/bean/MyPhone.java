package com.bawei6.usermodule.bean;

public class MyPhone {
    String name;
    String phone;
    String phonebook_label;
    int flag;

    public MyPhone(String name, String phone, String phonebook_label, int flag) {
        this.name = name;
        this.phone = phone;
        this.phonebook_label = phonebook_label;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhonebook_label() {
        return phonebook_label;
    }

    public void setPhonebook_label(String phonebook_label) {
        this.phonebook_label = phonebook_label;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
