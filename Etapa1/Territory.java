import java.util.ArrayList;
import java.util.Iterator;
import java.io.PrintStream;

public class Territory {
    private ArrayList<EloTelTag> tags;

    public Territory() {
        this.tags = new ArrayList<EloTelTag>();
    }

    public void addTag(EloTelTag tag) {
        this.tags.add(tag);
    }

    public EloTelTag getTag(String ownerName, String tagName) {
        for (EloTelTag tag : tags) {
            if (tag.getOwnerName().equals(ownerName) && tag.getName().equals(tagName)) {
                return tag;
            }
        }
        return null;
    }

    public void printHeader(PrintStream out) {
        out.print("Step\t");
        for (EloTelTag tag : tags) {
            // Imprime el identificador único del tag como cabecera
            out.print(tag.getOwnerName() + "." + tag.getName() + "\t");
        }
        out.println();
    }

    public void printState(PrintStream out, int step) {
        out.print(step + "\t");
        for (EloTelTag tag : tags) {
            // Usamos getState() que ya está en tu EloTelTag.class y devuelve las coordenadas
            out.print(tag.getState() + "\t");
        }
        out.println();
    }
}
