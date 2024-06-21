package RentalPlayStation;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;

public class Transaksi {
    private PlayStation ps = new PlayStation();
    private String peminjamanId;
    private String psId;
    private Double durasi;
    private Integer statusPeminjaman;
    private double biayaPeminjaman;

    public Transaksi() {
    }

    public Transaksi( String peminjamanId, String psId, Double durasi, Integer statusPeminjaman, double biayaPeminjaman) {
        this.peminjamanId = peminjamanId;
        this.psId = psId;
        this.durasi = durasi;
        this.statusPeminjaman = statusPeminjaman;
        this.biayaPeminjaman = biayaPeminjaman;
    }

    public PlayStation getPs() {
        return ps;
    }

    public void setPs(PlayStation ps) {
        this.ps = ps;
    }

    public String getPeminjamanId() {
        return peminjamanId;
    }

    public void setPeminjamanId(String peminjamanId) {
        this.peminjamanId = peminjamanId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId() {
        ps.getKode();
    }

    public Double getDurasi() {
        return durasi;
    }

    public void setDurasi(Double durasi) {
        this.durasi = durasi;
    }

    public Integer getStatusPeminjaman() {
        return statusPeminjaman;
    }

    public void setStatusPeminjaman(Integer statusPeminjaman) {
        this.statusPeminjaman = statusPeminjaman;
    }

    public double getBiayaPeminjaman() {
        return biayaPeminjaman;
    }

    public void setBiayaPeminjaman(double biayaPeminjaman) {
        this.biayaPeminjaman = biayaPeminjaman;
    }

    @Override
    public String toString() {
        // Buat objek DecimalFormat
        DecimalFormat df = new DecimalFormat("#,###.##");

        // Atur pemisah ribuan
        DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(symbols);

        // Format harga dengan pemisah tiga angka dan titik desimal
        String totalterformat = df.format(this.biayaPeminjaman);

        String statuString = "";
        if (getStatusPeminjaman() == 1) {
            statuString = "Disewa";
        } else if (getStatusPeminjaman() == 2) {
            statuString = "Selesai Disewa";
        }

        return "ID Transaksi: " + peminjamanId + "\n" +
               "ID PS: " + psId + "\n" +
                "Status Transaksi: " + statuString + "\n" +
                "Total Biaya: " + totalterformat;
    }
}
