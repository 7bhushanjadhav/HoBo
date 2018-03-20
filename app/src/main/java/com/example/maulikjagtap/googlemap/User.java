package com.example.maulikjagtap.googlemap;

/**
 * Created by Maulik Jagtap on 3/20/2018.
 */

public class User {
    String uid;
    String uname;
    String  uemail;
    String upass;

    User()
    {}

    public User(String uname, String  uemail, String upass) {

        this.uname = uname;
        this.uemail = uemail;
        this.upass = upass;
    }

    public String getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String  getUemail() {
        return uemail;
    }

    public String getUpass() {
        return upass;
    }
}
