package com.codinginflow.trashapp.model;

import java.io.Serializable;

public class Kategori implements Serializable {
    private String nama;
    private int icon;

    public Kategori(String nama, int icon) {
        this.nama = nama;
        this.icon = icon;
    }

    public String getNama() {
        return nama;
    }

    public int getIcon() {
        return icon;
    }
}
