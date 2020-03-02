package com.example.finaltry;

public class Users {
    private String ID;
    private String fName;
    private String lName;
    private String username;
    private String eMail;
    private String password;
    private String phonenumber;
    private String gender;
    private String timeStamp=null;
    public Users(String id,String fname,String lname,String username,String email,String password,String phonenumber,String gender){
        this.ID=id;
        this.fName=fname;
        this.lName=lname;
        this.username=username;
        this.eMail=email;
        this.password=password;
        this.phonenumber=phonenumber;
        this.gender=gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
