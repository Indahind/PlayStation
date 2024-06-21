package RentalPlayStation;

import RentalPlayStation.PSNode;
import RentalPlayStation.Transaksi;
import RentalPlayStation.TrxNode;

public class QLinkedLinst {
    public TrxNode head;
    public TrxNode tail;
    private int size; //jumlah node
    private TrxNode trx = new TrxNode();

    public QLinkedLinst() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

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

    public TrxNode getNode (int index) {
        TrxNode pointer;
        int i;

        if (isEmpty())
            return null;

        pointer = head;
        i = 0;
        while (pointer != null) {
            if (i == index)
                return pointer;
            i++;
            pointer = pointer.getNextReference();
        }
        return null;
    }

    public void deleteHead() {
        TrxNode pointer;

        pointer = head;
        head = pointer.getNextReference();
        pointer = null;
    }

    public boolean isEmpty() {
        return ((this.head == null) && (this.tail == null));
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TrxNode getTrx() {
        return trx;
    }

    public void setTrx(TrxNode trx) {
        this.trx = trx;
    }
}
