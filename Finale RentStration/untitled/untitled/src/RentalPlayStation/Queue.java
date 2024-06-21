package RentalPlayStation;

import RentalPlayStation.PSNode;

import java.sql.SQLInput;

public class Queue {
    private QLinkedLinst qlist;
    private DLinkedListTransaksiPS dltrx = new DLinkedListTransaksiPS();
    private int size;

    public QLinkedLinst getQlist() {
        return qlist;
    }

    public Queue() {
        dltrx = new DLinkedListTransaksiPS();
        this.size = 0;

    }

    public void DisplayAll() {
        dltrx.display();
    }

    public void enqueue(TrxNode trxNode) {
        System.out.println("Transaksi yang antri : \n" + trxNode.getTrx());
        dltrx.addLast(trxNode);
        size++;
    }

    public Transaksi dequeue() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return null;
        }
        Transaksi trxNodefront = dltrx.getNode(0).getTrx();
        System.out.println("Dequeue: " + trxNodefront.toString());

        dltrx.deleteHead();
        size--;

        if (size == 0) {
            System.out.println("\nAntrian kosong");
        } else {
            System.out.println(dltrx.getNode(0).getTrx().toString() + "\nMasuk antrian berikutnya");
        }
        return trxNodefront;
    }

    public Transaksi peek() {
        if (isEmpty()) {
            System.out.println("Antrian kosong, tidak ada data pertama");
            return null;
        }
        System.out.println(size);
        Transaksi trxNodefront = dltrx.getHead().getTrx();
        System.out.println("Antrian terdepan : " + trxNodefront.toString());

        return trxNodefront;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}
