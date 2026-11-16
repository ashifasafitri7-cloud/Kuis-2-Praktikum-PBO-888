/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ekspedisi;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ashifa Safitri
 */
public class SistemEkspedisi implements Serializable {
    private static final long serialVersionUID = 1L;
    private String namaPerusahaan;
    private HashMap<String, Pengirim> pengirimMap;
    private HashMap<String, LayananPaket> layananMap;
    private ArrayList<Transaksi> daftarTransaksi;
    public SistemEkspedisi(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
        pengirimMap = new HashMap<>();
        layananMap = new HashMap<>();
        daftarTransaksi = new ArrayList<>();
    }
    public void tambahPengirim(String id, String nama) {
        Pengirim pengirim = new Pengirim(id, nama);
        pengirimMap.put(id, pengirim);
    }
    public void tambahLayananPaket(String kode, String nama, int kuota) {
        LayananPaket layanan = new LayananPaket(kode, nama, kuota);
        layananMap.put(kode, layanan);
    }
    public void jalankanTransaksiDariFile(String namaFile) throws IOException {

        StringBuilder isiFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                isiFile.append(baris).append(" ");
            }
        }
        String[] dataTransaksi = isiFile.toString().trim().split("\\s+");
        for (String data : dataTransaksi) {
            if (data.trim().isEmpty()) {
                continue;
            }
            try {
                prosesSatuTransaksi(data);
            } catch (TransaksiException e) {
                System.out.println("[TRANSAKSI DITOLAK] " + e.getMessage());
            }
        }
    }
    private void prosesSatuTransaksi(String data) throws TransaksiException {
        String idPengirim = null;
        String kodeLayanan = null;
        String teksJumlah = null;
        String[] bagian = data.split(";");
        for (String item : bagian) {
            if (item.trim().isEmpty()) {
                continue;
            }
            String[] pasangan = item.split(":", 2);
            if (pasangan.length != 2) {
                throw new TransaksiException( "Format data tidak valid: " + data
                );
            }
            String kunci = pasangan[0].trim();
            String nilai = pasangan[1].trim();
            if (kunci.equals("ID")) {
                idPengirim = nilai;
            } else if (kunci.equals("KD")) {
                kodeLayanan = nilai;
            } else if (kunci.equals("JML")) {
                teksJumlah = nilai;
            }
        }
        if (idPengirim == null || kodeLayanan == null || teksJumlah == null) {
            throw new TransaksiException( "Data transaksi tidak lengkap: " + data
            );
        }
        int jumlah;
        try {
            jumlah = Integer.parseInt(teksJumlah);
        } catch (NumberFormatException e) {
            throw new TransaksiException( "Jumlah harus berupa angka: " + teksJumlah
            );
        }
        if (jumlah <= 0) {
            throw new TransaksiException( "Jumlah transaksi harus lebih dari 0."
            );
        }
        Pengirim pengirim = pengirimMap.get(idPengirim);
        if (pengirim == null) {
            throw new TransaksiException( "Pengirim dengan ID " + idPengirim + " tidak ditemukan."
            );
        }
        LayananPaket layanan = layananMap.get(kodeLayanan);
        if (layanan == null) {
            throw new TransaksiException( "Layanan dengan kode " + kodeLayanan + " tidak ditemukan."
            );
        }
        if (jumlah > layanan.getKuota()) {
            throw new TransaksiException( "Kuota " + layanan.getNama() + " tidak cukup. Sisa kuota: " + layanan.getKuota() + ", diminta: " + jumlah );
        }
        Transaksi transaksi = new Transaksi( pengirim, layanan, jumlah );
        daftarTransaksi.add(transaksi);
        layanan.kurangiKuota(jumlah);

        System.out.println( "[TRANSAKSI BERHASIL] " + transaksi
        );
    }
    public void simpanLaporanAkhir() {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream("snapshot.obj"))) {
            output.writeObject(this);
            System.out.println( "[SERIALIZATION] Data berhasil disimpan ke snapshot.obj"
            );
        } catch (IOException e) {
            System.out.println( "[ERROR SERIALIZATION] " + e.getMessage()
            );
        }
        try (FileWriter writer = new FileWriter("laporan.txt")) {
            for (String id : pengirimMap.keySet()) {
                writer.write(id);
                writer.write(System.lineSeparator());
            }
            System.out.println( "[LAPORAN] ID pengirim berhasil ditulis ke laporan.txt"
            );
        } catch (IOException e) {
            System.out.println( "[ERROR LAPORAN] " + e.getMessage()
            );
        }
    }
}