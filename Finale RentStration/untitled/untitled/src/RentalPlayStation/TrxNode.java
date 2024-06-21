package RentalPlayStation;

import java.util.Date;

public class TrxNode {
    private Transaksi trx;
    private TrxNode nextReference;
    private TrxNode prevReference;

    public TrxNode() {
    }

    public TrxNode(Transaksi trx) {
        this.trx = trx;
        this.nextReference = null;
        this.prevReference = null;
    }

    public Transaksi getTrx() {
        return trx;
    }

    public void setTrx(Transaksi trx) {
        this.trx = trx;
    }

    public TrxNode getNextReference() {
        return nextReference;
    }

    public void setNextReference(TrxNode nextReference) {
        this.nextReference = nextReference;
    }

    public TrxNode getPrevReference() {
        return prevReference;
    }

    public void setPrevReference(TrxNode prevReference) {
        this.prevReference = prevReference;
    }
}
