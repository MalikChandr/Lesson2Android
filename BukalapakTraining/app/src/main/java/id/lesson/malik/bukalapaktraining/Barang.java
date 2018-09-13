package id.lesson.malik.bukalapaktraining;

public class Barang {
    public int idBarang;
    public String namaBarang;
    public String hargaBarang;
    public String deskripsi;
    public String status;

    public Barang(int idBarang, String namaBarang, String hargaBarang, String deskripsi, String status){
        this.idBarang       = idBarang;
        this.namaBarang     = namaBarang;
        this.hargaBarang    = hargaBarang;
        this.deskripsi      = deskripsi;
        this.status         = status;
    }
}
