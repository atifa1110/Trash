package com.codinginflow.trashapp;

class User {

    private String uid;
    private String nama;
    private String email;



    public User(String uid, String nama, String email) {
        this.uid = uid;
        this.nama = nama;
        this.email = email;

    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUid() {
        return uid;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

}
