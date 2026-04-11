

public class Tablet extends EloTelTag {
    private Viewer viewer;

    // El constructor hereda los atributos de EloTelTag usando 'super'
    public Tablet(String ownerName, String name, float x, float y) {
        super(ownerName, name, x, y);
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    // Método exclusivo de la tablet para consultar la nube
    public void findMy() {
        if (this.viewer != null) {
            this.viewer.FindMy(this.getOwnerName());
        }
    }
}