package com.codinginflow.trashapp.model;

public class User {

    private String uid;
    private String nama;
    private String email;
    private String alamat;
    private String kodepos;
    private String nomertelpon;


    public User(String uid, String nama ,String alamat,String kodepos,String nomertelpon) {
        this.uid = uid;
        this.nama = nama;
        this.alamat = alamat;
        this.kodepos = kodepos;
        this.nomertelpon = nomertelpon;
    }

    public User(String uid, String nama ,String email,String alamat,String kodepos,String nomertelpon) {
        this.uid = uid;
        this.nama = nama;
        this.email=email;
        this.alamat = alamat;
        this.kodepos = kodepos;
        this.nomertelpon = nomertelpon;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomertelpon() {
        return nomertelpon;
    }

    public void setNomertelpon(String nomertelpon) {
        this.nomertelpon = nomertelpon;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }
}
