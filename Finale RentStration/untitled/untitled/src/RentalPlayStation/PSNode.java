package RentalPlayStation;

public class PSNode {
    private PlayStation playStation;
    private PSNode nextReference;
    private PSNode prevReference;

    public PSNode() {
    }

    public PSNode(PlayStation playStation) {
        this.playStation = playStation;
        this.nextReference = null;
        this.prevReference = null;
    }

    public PSNode getPrevReference() {
        return prevReference;
    }

    public void setPrevReference(PSNode prevReference) {
        this.prevReference = prevReference;
    }

    public PlayStation getPlayStation() {
        return playStation;
    }

    public void setPlayStation(PlayStation playStation) {
        this.playStation = playStation;
    }


    public PSNode getNextReference() {
        return nextReference;
    }

    public void setNextReference(PSNode nextReference) {
        this.nextReference = nextReference;
    }


}
