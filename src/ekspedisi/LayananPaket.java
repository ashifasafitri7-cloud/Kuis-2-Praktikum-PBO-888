/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ekspedisi;

/**
 *
 * @author Ashifa Safitri
 */
public class LayananPaket extends ObjekPaket {
    private static final long serialVersionUID = 1L;
    public LayananPaket(String kode, String nama, int kuota) {
        super(kode, nama, kuota);
    }
    @Override
    public String getJenisObjek() {
        return "Layanan Paket";
    }
}