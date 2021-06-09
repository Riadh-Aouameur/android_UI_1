package com.example.killjoy;

import android.widget.EditText;

public class User {

   String name ;
  String email ;
    String  password ;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
