import java.io.PrintStream;
import java.util.ArrayList;

public class Territory {  // Piece of land where cellulars, tags, and tablets are located and moved.
    public ArrayList<EloTelTag> getTags() { return this.tags; }

    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }
    public EloTelTag getTag(String ownerName, String equipmentName) {
        for (EloTelTag tag : tags) {
            if (tag.getOwnerName().equals(ownerName) && tag.getName().equals(equipmentName)) {
                return tag;
            }
        }
        return null;
    }

    private ArrayList<Celular> celulares = new ArrayList<>();
    public void addCelular(Celular cel) {
        celulares.add(cel);
    }

    public Celular getCelular(String ownerName) {
        for (Celular cel : celulares) {
            if (cel.nombreDueno.equals(ownerName)) {
                return cel;
            }
        }
        return null;
    }

    public void printHeader(PrintStream output) {
        output.print("Step\t");
        for (EloTelTag tag : tags)
            output.print(tag.getHeader()+"\t");
        output.println();
    }

    public void printState(PrintStream output, int step) {
        output.print(step + "\t");
        for (EloTelTag tag : tags) {
            output.print(tag.getState() + "\t");

        }
        output.println();
    }
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();

}
