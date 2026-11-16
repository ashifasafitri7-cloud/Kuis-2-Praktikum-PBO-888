/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ekspedisi;

/**
 *
 * @author Ashifa Safitri
 */
public class MainEkspedisi {
    public static void main(String[] args) {
        SistemEkspedisi ekspedisi = new SistemEkspedisi("Kargo Cepat");
        ekspedisi.tambahPengirim("ACT-01", "Kiki");
        ekspedisi.tambahPengirim("ACT-02", "Lina");
        ekspedisi.tambahLayananPaket("OBJ-A", "Kargo Udara Premium", 10);
        ekspedisi.tambahLayananPaket("OBJ-B", "Reguler Darat", 2);
        ekspedisi.tambahLayananPaket("OBJ-C", "Ekonomi Laut", 50);
        System.out.println("--- MEMULAI PEMBACAAN FILE TRANSAKSI ---");
        try {
            ekspedisi.jalankanTransaksiDariFile("input_transaksi.txt");
        } catch (Exception e) {
            System.out.println("[SISTEM FATAL ERROR] File gagal dibaca/diproses: " + e.getMessage());
        }
        ekspedisi.simpanLaporanAkhir();
    }
}