package com.example.ptkmprogmob.Model;

import com.google.gson.annotations.SerializedName;

public class Home {
        @SerializedName("countSkp")
    private String countSkp;
    @SerializedName("countKategori")
    private String countKategori;

    public Home() {
    }

    public Home(String countSkp, String countKategori) {
        this.countSkp = countSkp;
        this.countKategori = countKategori;
    }

    public String getCountSkp() {
        return countSkp;
    }

    public void setCountSkp(String countSkp) {
        this.countSkp = countSkp;
    }

    public String getCountKategori() {
        return countKategori;
    }

    public void setCountKategori(String countKategori) {
        this.countKategori = countKategori;
    }
}
