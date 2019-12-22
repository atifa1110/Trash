package com.codinginflow.trashapp.model;

public class barang {
    private String type;
    private Double harga;

    public barang(String type, Double harga) {
        this.type = type;
        this.harga = harga;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }
}
