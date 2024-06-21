package RentalPlayStation;

public class DLinkedListPS {
    private PSNode head; //kepala node
    private PSNode tail; //ekor node
    private int size; //jumlah node
    private PlayStation ps = new PlayStation();

    //constructor kelas
    public DLinkedListPS() {
        //inisialisasi atribut
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public String IdPS(int size) {
        String id = "";

        // Mengecek apakah size (jumlah data) sama dengan 0
        if (size == 0) {
            id = "PS001";
        } else {
            // Membuat variabel increment untuk nomor ID
            int increment = size + 1;

            // Mengonversi nomor ID menjadi format tiga digit dengan leading zeroes
            String formattedNumber = String.format("%03d", increment);

            // Menggabungkan format "PS" dengan nomor ID yang sudah diformat
            id = "PS" + formattedNumber;
        }

        // Mengatur ID pada objek ps
        ps.setKode(id);

        return id;
    }


    //method untuk cek linkedlist nya kosong atau ga
    public boolean isEmpty() {
        return ((this.head == null) && (this.tail == null));
    }

    //method untuk nambah node di awal linked list
    public void addFirstPS(PSNode node) {
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

    public void InsertTengah(PSNode nodebaru, String insertAfter) {
        PSNode pointer = head;
        while (pointer.getNextReference() != null) {
            if (pointer.getPlayStation().equals(insertAfter)) {
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
    public void addLast(PSNode node) {
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
    public void displayAll() {
        PSNode pointer = head;

        while (pointer != null) {
            System.out.println(pointer.getPlayStation().getKode() + "  " + pointer.getPlayStation().getHarga() + "  " + pointer.getPlayStation().getStatusString());
            pointer = pointer.getNextReference();
        }
    }


    public void displayPS() {
        PSNode pointer = head;

        //System.out.println("Jumlah data PlayStation : " + this.size);
        int id = 1;
        while (pointer != null) {
            if (pointer.getPlayStation().getStatus().equals(2)) {
                System.out.println(id + ". " + pointer.getPlayStation().getKode());
                id++;

            }
            pointer = pointer.getNextReference();
        }

    }


    // method cari data dimulai dr head ke hingga ketemu data
    public PSNode searchByKode(String data) {
        PSNode pointer = head;

        if (isEmpty()) return null;

        while (pointer != null) {
            if (pointer.getPlayStation().getKode().equals(data))
                return pointer;

            pointer = pointer.getNextReference();
        }

        return null;
    }

    public void getTotalPrice() {
        PSNode pointer = head;
        int totalHarga = 0;
        while (pointer != null) {
            totalHarga += pointer.getPlayStation().getHarga();
            pointer = pointer.getNextReference();
        }
        System.out.printf("Total : "+ totalHarga);
    }

    public void deleteHead() {
        PSNode pointer;

        pointer = head;
        head = pointer.getNextReference();
        pointer = null;
    }

    public void deleteNode(PSNode nodeToDelete) {
        PSNode current = head;
        PSNode previous = null;

        while (current != null) {
            if (current == nodeToDelete) {
                if (previous == null) {
                    // Node yang akan dihapus adalah head
                    head = current.getNextReference();
                } else {
                    // Node yang akan dihapus berada di tengah atau akhir daftar
                    previous.setNextReference(current.getNextReference());
                }
                // Jangan lupa untuk menghapus node yang sudah tidak digunakan
                nodeToDelete = null;
                return;
            }

            // Pindah ke node selanjutnya
            previous = current;
            current = current.getNextReference();
        }

        System.out.println("Node tidak ditemukan dalam daftar.");
    }


    public void deleteTail() {
        PSNode pointer;
        pointer = head;

        while (pointer.getNextReference() != tail) {
            pointer = pointer.getNextReference();
        }
        tail = pointer;
        tail.setNextReference(null);
        this.size--;
    }

    public PSNode getHead() {
        return head;
    }

    public void setHead(PSNode head) {
        this.head = head;
    }

    public PSNode getTail() {
        return tail;
    }

    public void setTail(PSNode tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PlayStation getPs() {
        return ps;
    }
    public void setPs(PlayStation ps) {
        this.ps = ps;
    }
}
