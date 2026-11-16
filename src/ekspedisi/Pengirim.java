/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ekspedisi;

/**
 *
 * @author Ashifa Safitri
 */
public class Pengirim extends Aktor {
    private static final long serialVersionUID = 1L;
    public Pengirim(String id, String nama) {
        super(id, nama);
    }
    @Override
    public String getJenisAktor() {
        return "Pengirim";
    }
    @Override
    public void tampilkanInfo() {
        System.out.println("Pengirim " + getId() + " bernama " + getNama());
    }
}