/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Date;

public class Friend {
    
    private int idfriend;
    private String name;
    private String phoneNumber;
    private String address;
    private Date birthday;
    private String image;

    public Friend() {
    }

    public Friend(int idfriend, String name, String phoneNumber, String address, Date birthday, String image) {
        this.idfriend = idfriend;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.image = image;
    }

    public Friend(String name, String phoneNumber, String address, Date birthday, String image) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.image = image;
    }
    
    public Friend(String name, String phoneNumber, String address, Date birthday) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
    }

    public Friend(int id) {
        this.idfriend = id;
    }
    
    

    public int getIdfriend() {
        return idfriend;
    }

    public void setIdfriend(int idfriend) {
        this.idfriend = idfriend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    
    
}
