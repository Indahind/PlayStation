package RentalPlayStation;

public class DLinkedListTransaksiPS {
    private TrxNode head; //kepala node
    private TrxNode tail; //ekor node
    private int size; //jumlah node
    private Transaksi trx = new Transaksi();

    public DLinkedListTransaksiPS() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public String IdRent(int size) {
        String id = "";

        // Mengecek apakah size (jumlah data) sama dengan 0
        if (size == 0) {
            id = "TRX001";
        } else {
            // Membuat variabel increment untuk nomor ID
            int increment = size + 1;

            // Mengonversi nomor ID menjadi format tiga digit dengan leading zeroes
            String formattedNumber = String.format("%03d", increment);

            // Menggabungkan format "PS" dengan nomor ID yang sudah diformat
            id = "TRX" + formattedNumber;
        }

        // Mengatur ID pada objek ps
        trx.setPeminjamanId(id);

        return id;
    }

    //method untuk cek linkedlist nya kosong atau ga
    public boolean isEmpty() {
        return ((this.head == null) && (this.tail == null));
    }

    //method untuk nambah node di awal linked list
    public void addFirstPS(TrxNode node) {
        if (!isEmpty()) {
            node.setNextReference(head);
            head = node;
        } else {
            node.setNextReference(null);
            tail = node;
            head = node;
        }
        ++this.size;
    }

    public void InsertTengah(TrxNode nodebaru, String insertAfter) {
        TrxNode pointer = head;
        while (pointer.getNextReference() != null) {
            if (pointer.getTrx().equals(insertAfter)) {
                nodebaru.setNextReference(pointer.getNextReference());
                pointer.setNextReference(nodebaru);
                break;
            } else {
                pointer = pointer.getNextReference();
            }
        }
        if (pointer == tail) {
            addLast(nodebaru);
        }
    }

    public int getSize() {
        return size;
    }

    //method untuk nambah node di akhir
    public void addLast(TrxNode node) {
        if (!isEmpty()) {
            node.setNextReference(null);
            this.tail.setNextReference(node);
            tail = node;
        } else {
            node.setNextReference(null);
            tail = node;
            head = node;
        }
        ++this.size;
    }

    //method buat tampilin
    public void display() {
        TrxNode pointer;

        pointer = head;

        //System.out.println("Jumlah data PlayStation : " + this.size);

        while (pointer != null) {
            System.out.println(pointer.getTrx());
            pointer = pointer.getNextReference();
        }
    }

    public void displayNotReturn() {
        TrxNode pointer = head;

        while (pointer != null) {
            Transaksi trx = pointer.getTrx();
            if (trx != null && trx.getStatusPeminjaman().equals(1)) {
                System.out.println(trx);
                System.out.println("");
            }
            pointer = pointer.getNextReference();
        }
    }

    public void getRentForaWeek() {
        double totalRentalRevenue = 0.0;
        int loop = 1;
        TrxNode pointer = head;

        System.out.println("Play Station Transaction Data for the Week:");
        while (pointer != null){
            System.out.println("No. " + loop);

            System.out.println("Transaction ID: " + pointer.getTrx().getPeminjamanId());
            System.out.println("PS ID: " + pointer.getTrx().getPsId());
            System.out.println("Durasi sewa : " + pointer.getTrx().getDurasi() + " jam");
            System.out.println("Harga Rp. " + pointer.getTrx().getBiayaPeminjaman() + "------------------------");

            totalRentalRevenue += pointer.getTrx().getBiayaPeminjaman(); // Accumulate the total rental revenue

            loop++;
            pointer = pointer.getNextReference();
        }
        System.out.println("Total Pemasukan rental PS MInggu ini : Rp." + totalRentalRevenue);
    }

    // method cari data dimulai dr head ke hingga ketemu data
    public TrxNode searchByKode(String data) {
        TrxNode pointer = head;

        if (isEmpty()) return null;

        while (pointer != null) {
            Transaksi trx = pointer.getTrx();
            if (trx != null && trx.getPeminjamanId().equals(data)) {
                return pointer;
            }

            pointer = pointer.getNextReference();
        }

        return null;
    }


    public void deleteHead() {
        TrxNode pointer;

        pointer = head;
        head = pointer.getNextReference();
        size--;
    }

    public void deleteTail() {
        TrxNode pointer;
        pointer = head;

        while (pointer.getNextReference() != tail) {
            pointer = pointer.getNextReference();
        }
        tail = pointer;
        tail.setNextReference(null);
        this.size--;
    }

    public TrxNode getNode(int index) {
        TrxNode pointer;
        int i;

        if (isEmpty()) return null;

        pointer = head;
        i = 0;
        while (pointer != null) {
            if (i == index) return pointer;
            i++;
            pointer = pointer.getNextReference();
        }
        return null;
    }

    public TrxNode getHead() {
        return head;
    }

    public void setHead(TrxNode head) {
        this.head = head;
    }

    public TrxNode getTail() {
        return tail;
    }

    public void setTail(TrxNode tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Transaksi getTrx() {
        return trx;
    }

    public void setTrx(Transaksi trx) {
        this.trx = trx;
    }

    public Double getTotalHarga() {
        double total = 0;
        for (int i = 0; i < getSize(); i++) {
            total += getTrx().getBiayaPeminjaman();
            return total;
        }
        return null;
    }
}
