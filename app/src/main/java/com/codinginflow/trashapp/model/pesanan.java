package com.codinginflow.trashapp.model;

public class pesanan {
    private String uidUser;
    private String namaPengepul;
    private String tanggal;
    private double hargaTotal;
    private boolean status;

    public pesanan(String uidUser, String namaPengepul, double hargaTotal, String tanggal, boolean status) {
        this.uidUser = uidUser;
        this.namaPengepul = namaPengepul;
        this.hargaTotal = hargaTotal;
        this.tanggal = tanggal;
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(double hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    public String getNamaPengepul() {
        return namaPengepul;
    }

    public void setNamaPengepul(String uidPengepul) {
        this.namaPengepul = uidPengepul;
    }

    public String getUidUser() {
        return uidUser;
    }

    public void setUidUser(String uidUser) {
        this.uidUser = uidUser;
    }
}
