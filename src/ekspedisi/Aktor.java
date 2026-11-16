/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ekspedisi;

import java.io.Serializable;

/**
 *
 * @author Ashifa Safitri
 */
public abstract class Aktor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nama;
    public Aktor(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }
    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public abstract String getJenisAktor();
    public void tampilkanInfo() {
        System.out.println(getJenisAktor() + ": " + id + " - " + nama);
    }
}