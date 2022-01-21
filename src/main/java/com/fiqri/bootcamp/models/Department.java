package com.fiqri.bootcamp.models;

public class Department {
    private Integer id;
    private String nama;
    private  String dekripsi;

    public Department(){

    }
    public Department(int id, String nama,String dekripsi){
        this.id=id;
        this.nama=nama;
        this.dekripsi=dekripsi;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + nama + '\'' +
                ", dekripsi='" + dekripsi + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setName(String nama) {
        this.nama = nama;
    }

    public String getDekripsi() {
        return dekripsi;
    }

    public void setDekripsi(String dekripsi) {
        this.dekripsi = dekripsi;
    }

}


