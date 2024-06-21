package RentalPlayStation;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PlayStation {
    private String Kode;
    private Double Harga;
    private Integer Status;

    public PlayStation() {
    }

    public PlayStation(String kode, Double harga, Integer status) {
        Kode = kode;
        Harga = harga;
        Status = status;
    }

    public String getKode() {
        return Kode;
    }
    public void setKode(String kode) {
        Kode = kode;
    }
    public Integer getStatus() {
        return Status;
    }
    public void setStatus(Integer status) {
        Status = status;
    }
    public Double getHarga() {
        return Harga;
    }
    public void setHarga(Double harga) {
        Harga = harga;
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
        String hargaterformat = df.format(this.Harga);
        return Kode + "\t  " + hargaterformat + "\t  " + Status;
    }

    public String getStatusString() {
        if (getStatus().equals(1)) {
            return "Disewa.";
        } else if (getStatus().equals(2)) {
            return "Tidak Disewa";
        }
        return null;
    }
}
