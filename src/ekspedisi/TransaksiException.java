/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ekspedisi;

/**
 *
 * @author Ashifa Safitri
 */
public class TransaksiException extends Exception {
    private static final long serialVersionUID = 1L;
    public TransaksiException(String pesan) {
        super(pesan);
    }
}