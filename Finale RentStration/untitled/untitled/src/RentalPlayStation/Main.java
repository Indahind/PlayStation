package RentalPlayStation;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static DLinkedListPS dlPS = new DLinkedListPS();
    public static DLinkedListTransaksiPS dlTrx = new DLinkedListTransaksiPS();
    static QLinkedLinst listantrian = new QLinkedLinst();

    public static Queue queuePS = new Queue();

    public static void main(String[] args) {
        main();
    }

    public static void main() {
        System.out.println("");
        boolean loop = true;

        while (loop) {
            System.out.println("========= PS Station =========");
            System.out.println("====================================");

            System.out.println("1. PS Management");
            System.out.println("2. Rent PlayStation");
            System.out.println("3. Exit");
            System.out.print("Pilihan kamu: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    PlayStationRoom();
                    System.out.println();
                    break;
                case 2:
                    RentRoom();
                    System.out.println("");
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    public static void PlayStationRoom() {
        System.out.println("");
        boolean loopPS = true;

        while (loopPS) {
            System.out.println("========= PlayStation Room =========");
            System.out.println("====================================");
            int choice = 0;
            try {
                System.out.println("1. Input PlayStation");
                System.out.println("2. View Data PlayStation");
                System.out.println("3. Update PlayStation");
                System.out.println("4. Delete Playstation");
                System.out.println("5. Back");
                System.out.print("Pilihan: ");
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException num) {
                System.out.println(num.getMessage());
            }

            switch (choice) {
                case 1:
                    inputPS();
                    System.out.println("");
                    break;
                case 2:
                    displayPS();
                    System.out.println("");
                    break;
                case 3:
                    updatePS();
                    System.out.println("");
                    break;
                case 4:
                    deletePS();
                    System.out.println("-");
                    break;
                case 5:
                    loopPS = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    public static void RentRoom() {
        System.out.println("");
        boolean loopTrx = true;

        while (loopTrx) {
            System.out.println("========= PlayStation Rent =========");
            System.out.println("============ Transaction ===========");

            System.out.println("1. Input Rent PlayStation");
            System.out.println("2. View List Rent PlayStation");
            System.out.println("3. View Peek");
            System.out.println("4. View Income");
            System.out.println("5. Input Return");
            System.out.println("6. Back");
            System.out.print("Pilih: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("");
                    inputRent();
                    break;
                case 2:
                    System.out.println("");
                    displayTrx();
                    break;
                case 3:
                    System.out.println("");
                    queuePS.peek();
                    break;
                case 4:
                    System.out.println("");
                    dlTrx.getRentForaWeek();
                    break;
                case 5:
                    System.out.println("");
                    InputReturn();
                    break;
                case 6:
                    System.out.println("");
                    loopTrx = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void inputRent() {
        System.out.println("");
        double durasi;
        double totalHarga;

        System.out.println("+++ Input Rent PlayStation +++");
        System.out.println(dlTrx.IdRent(dlTrx.getSize()));
        System.out.println("Pilih PS yang tersedia");
        dlPS.displayPS();
        System.out.print("Kode PS: ");

        String idps = input.nextLine();

        System.out.print("Durasi [/jam]: ");
        durasi = Double.parseDouble(input.nextLine());

        // Mendapatkan node PlayStation berdasarkan Kode PS
        PSNode psNode = dlPS.searchByKode(idps);
        double harg = 0;
        if (psNode != null) {
            totalHarga = psNode.getPlayStation().getHarga() * durasi;
            harg = totalHarga;
            if (psNode.getPlayStation().getStatus() == 2) {
                // Jika PS tersedia untuk disewa

                int status = 1;
                psNode.getPlayStation().setStatus(status);

                Transaksi trx = new Transaksi(dlTrx.IdRent(dlTrx.getSize()), idps, durasi, 1, totalHarga);
                TrxNode trxNode = new TrxNode(trx);

                if (queuePS.isEmpty()) {
                    // Jika antrian kosong, langsung sewa PS
                    dlTrx.addLast(trxNode);
                    System.out.println("Transaction Succesed!");
                } else {
                    // Jika ada antrian, tambahkan pelanggan ke antrian
                    queuePS.enqueue(trxNode);
                    System.out.println("Added to queue!");
                }
            } else {
                if (dlPS.searchByKode(idps).getPlayStation().getStatus() == 1) {
                    System.out.println("ID PS SEDANG DISEWA");

                }
                // Jika PS sedang disewa, tambahkan pelanggan ke antrian
                Transaksi trx = new Transaksi(dlTrx.IdRent(dlTrx.getSize()), idps, durasi, 1, harg);
                TrxNode trxNode = new TrxNode(trx);
                queuePS.enqueue(trxNode);
                System.out.println("Anda telah masuk ke antrian. Harap tunggu.");
            }
        } else {
            System.out.println("PS dengan Kode tersebut tidak ditemukan");
        }
    }


    // Metode untuk memproses antrian setelah penyewaan selesai
    public static void processQueue() {
        if (!queuePS.isEmpty()) {
            Transaksi nextTrx = queuePS.peek();
            String idps = nextTrx.getPsId();
            PSNode psNode = dlPS.searchByKode(idps);

            if (psNode != null && psNode.getPlayStation().getStatus() == 0) {
                queuePS.dequeue();
                dlTrx.addLast(new TrxNode());
                System.out.println("Transaction Succesed!");
            } else {
                System.out.println("Antrian pelanggan tidak dapat diproses saat ini.");
            }
        }
    }


    public static void displayTrx() {
        System.out.println("");

        System.out.println("+++ View Transaksi +++");
        System.out.println("Jumlah Trx: " + dlTrx.getSize());
        System.out.println("ID Transaksi | Kode PS | Status | Harga ");
        dlTrx.display();
    }

    public static void InputReturn() {

        System.out.println("");
        String idPj = "", idPs = "", jwb;
        Integer status = 0;
        Double durasi = 0.0;

        System.out.println("+++ Return PlayStation +++");
        dlTrx.displayNotReturn();
        System.out.println("");
        System.out.print("Input ID Transaction: ");
        idPj = input.nextLine();

        // Mencari node dengan kode Trx yang sesuai
        TrxNode nodeToUpdate = dlTrx.searchByKode(idPj);
        if (nodeToUpdate == null) {
            System.out.println("Data Transaction tidak ditemukan.");
            return;
        } else {
            System.out.println("Transaction ID: " + nodeToUpdate.getTrx().getPeminjamanId());
            System.out.println("Kode PS: " + nodeToUpdate.getTrx().getPsId());
            System.out.println("Harga: " + nodeToUpdate.getTrx().getBiayaPeminjaman());
            System.out.print("Selesai sewa? [y/n]: ");
            jwb = input.nextLine();

            if (jwb.equals("y")) {
                status = 2;
                System.out.println("Play Station sudah dikembalikan");
            } else if (jwb.equals("n")) {
                status = 1;
                System.out.print("Durasi tambahan [/jam]: ");
                int waktu = Integer.parseInt(input.nextLine());
                durasi = nodeToUpdate.getTrx().getDurasi() + waktu;
            } else {
                System.out.println("Pilihan tidak valid.");
                return;
            }

            dlTrx.searchByKode(nodeToUpdate.getTrx().getPeminjamanId()).getTrx().setStatusPeminjaman(status);


            // Update status Transaction pada node yang ditemukan

            dlPS.searchByKode(nodeToUpdate.getTrx().getPsId()).getPlayStation().setStatus(status);
            System.out.println("Transaction updated.");

            if (dlTrx.searchByKode(nodeToUpdate.getTrx().getPeminjamanId()).getTrx().getStatusPeminjaman() == 2) {
                if (queuePS.isEmpty()) {
                    main();
                    return;
                }
                // Jika PS sudah dikembalikan, masukkan transaksi ke antrian
                Transaksi trs = queuePS.peek();
                System.out.println("");
                System.out.println(trs);
                System.out.println("");
                TrxNode trxNode = new TrxNode(trs);
                queuePS.dequeue();
                dlTrx.addLast(trxNode);
                System.out.println("Added to trx.");
            }
        }
    }


    public static void deletePS() {
        System.out.println("");
        System.out.println("+++ Delete PlayStation +++");
        dlPS.displayPS();

        if (dlPS.isEmpty()) {
            System.out.println("Tidak ada PlayStation yang tersedia.");
            return;
        }

        System.out.print("Masukkan Kode PS yang akan dihapus: ");
        String kodePS = input.nextLine();

        PSNode psNode = dlPS.searchByKode(kodePS);
        if (psNode != null) {
            // Jika ditemukan, hapus PlayStation dari daftar
            dlPS.deleteNode(psNode);
            System.out.println("PlayStation dengan Kode " + kodePS + " berhasil dihapus.");
        } else {
            System.out.println("PlayStation dengan Kode " + kodePS + " tidak ditemukan.");
        }
    }


    public static void inputPS() {
        System.out.println("");
        Double hrg = 0.0;
        Integer status = 0;

        System.out.println("+++ Input PlayStation +++");
        System.out.print("1. Kode PS: " + dlPS.IdPS(dlPS.getSize()));
        System.out.print("\n2. Harga: ");
        hrg = Double.parseDouble(input.nextLine());
        status = 2;
        System.out.print("3. Status : Tidak Disewa.");
        PlayStation ps = new PlayStation(dlPS.IdPS(dlPS.getSize()), hrg, status);
        PSNode psNode = new PSNode(ps);
        dlPS.addLast(psNode);
        System.out.println("");
    }

    public static void displayPS() {
        System.out.println("");

        System.out.println("+++ View PlayStation +++");
        System.out.println("Jumlah Play Station" + dlPS.getSize());
        System.out.println(" Kode PS | Harga | Status");
        dlPS.displayAll();
    }

    public static void updatePS() {
        System.out.println("");
        String kodePS = "";
        Double hrg = 0.0;
        Integer status = 0;

        System.out.println("+++ Update PlayStation +++");
        dlPS.displayPS();
        System.out.print("Masukkan Kode PS yang akan diupdate: ");
        kodePS = input.nextLine();

        // Mencari node dengan kode PS yang sesuai
        PSNode nodeToUpdate = dlPS.searchByKode(kodePS);
        if (nodeToUpdate == null) {
            System.out.println("Data PlayStation tidak ditemukan.");
            return;
        }

        System.out.print("1. Kode PS: " + kodePS);
        System.out.print("\n2. Harga: ");
        hrg = Double.parseDouble(input.nextLine());
        System.out.print("3. Status " +
                "\n> 1. Disewa." +
                "\n> 2. Tidak Disewa." +
                "\nPilih Status = ");
        status = Integer.parseInt(input.nextLine());

        // Mengupdate data PlayStation pada node yang ditemukan

        dlPS.searchByKode(kodePS).getPlayStation().setHarga(hrg);
        dlPS.searchByKode(kodePS).getPlayStation().setStatus(status);

        System.out.println("Data PlayStation berhasil diupdate.");
    }

    public static void processRentalQueue() {
        while (!queuePS.isEmpty()) {
            Transaksi trxNode = queuePS.peek();
            String psId = trxNode.getPsId();
            PSNode ps = dlPS.searchByKode(psId);

            if (ps != null && dlPS.getPs().getStatus().equals(2)) {
                queuePS.dequeue(); // Menghapus elemen pertama dari antrian
                System.out.println("PS " + psId + " telah selesai disewa");
            } else {
                System.out.println("PS " + psId + " masih dalam proses peminjaman");
                break; // Menghentikan proses antrian
            }
        }
    }

    public static void updatePSStatus(String psId, Integer newStatus) {
        PSNode ps = dlPS.searchByKode(psId);
        if (ps != null) {
            dlPS.getPs().setStatus(newStatus);
            System.out.println("Status PS " + psId + " berhasil diperbarui menjadi " + newStatus);
        } else {
            System.out.println("PS dengan ID " + psId + " tidak ditemukan");
        }
    }

    public static void getRentalQueueStatus() {
        if (queuePS.isEmpty()) {
            System.out.println("Antrian rental PS kosong");
        } else {
            System.out.println("Status antrian rental PS:");
            while (!queuePS.isEmpty()) {
                Transaksi trxNode = queuePS.peek();
                String psId = trxNode.getPsId();
                double durasi = trxNode.getDurasi();
                double totalHarga = trxNode.getBiayaPeminjaman();
                System.out.println("PS ID: " + psId + ", Durasi: " + durasi + " jam, Total Harga: " + totalHarga);
                queuePS.dequeue();
            }
        }
    }
}