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
public class Transaksi implements Serializable {
    private static final long serialVersionUID = 1L;
    private Pengirim pengirim;
    private LayananPaket layanan;
    private int jumlah;
    public Transaksi(Pengirim pengirim, LayananPaket layanan, int jumlah) {
        this.pengirim = pengirim;
        this.layanan = layanan;
        this.jumlah = jumlah;
    }
    public Pengirim getPengirim() {
        return pengirim;
    }
    public LayananPaket getLayanan() {
        return layanan;
    }
    public int getJumlah() {
        return jumlah;
    }
    @Override
    public String toString() {
        return pengirim.getId() + " | "
                + layanan.getKode() + " | "
                + jumlah;
    }
}