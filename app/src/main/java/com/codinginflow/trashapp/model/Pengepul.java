package com.codinginflow.trashapp.model;

public class Pengepul {

    private String uid;
    private String nama;
    private String namaUsaha;
    private String alamatUsaha;
    private String kodepos;
    private String nomertelpon;
    private String harga;

    public Pengepul(){

    }

    public Pengepul(String uid, String nama, String namaUsaha, String alamatUsaha, String kodepos, String nomertelpon) {
        this.uid = uid;
        this.nama = nama;
        this.namaUsaha = namaUsaha;
        this.alamatUsaha = alamatUsaha;
        this.kodepos = kodepos;
        this.nomertelpon = nomertelpon;
    }

    public Pengepul(String uid, String nama, String namaUsaha, String alamatUsaha, String kodepos, String nomertelpon, String harga) {
        this.uid = uid;
        this.nama = nama;
        this.namaUsaha = namaUsaha;
        this.alamatUsaha = alamatUsaha;
        this.kodepos = kodepos;
        this.nomertelpon = nomertelpon;
        this.harga = harga;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNamaUsaha() {
        return namaUsaha;
    }

    public void setNamaUsaha(String namaUsaha) {
        this.namaUsaha = namaUsaha;
    }

    public String getAlamatUsaha() {
        return alamatUsaha;
    }

    public void setAlamatUsaha(String alamatUsaha) {
        this.alamatUsaha = alamatUsaha;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }

    public String getNomertelpon() {
        return nomertelpon;
    }

    public void setNomertelpon(String nomertelpon) {
        this.nomertelpon = nomertelpon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String  getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

}
