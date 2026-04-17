

public class Tablet extends EloTelTag {
    private Viewer viewer;

    public Tablet(String ownerName, String name, float x, float y) {
        super(ownerName, name, x, y);
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public void findMy() {
        if (this.viewer != null) {
            this.viewer.FindMy(this.getOwnerName());
        }
    }
}