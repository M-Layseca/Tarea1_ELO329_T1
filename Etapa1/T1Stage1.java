import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class T1Stage1 {
    T1Stage1() {
        territory = new Territory();
    }
    public static void main (String args[]) throws IOException {
                                // throws exception to avoid catching
                                // exception in the program
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            System.exit(-1);
        }
        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        T1Stage1 stage = new T1Stage1();
        stage.setupSimulator(confFile);  // read configuration file and create objects
        stage.runSimulation(movFile, System.out); // execute file's instructions
    }
    public void setupSimulator(Scanner in) {  // create objects from file
        EloTelTag tag;
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++) {
            String personName = in.next();
            int tagNumber = in.nextInt();
            boolean isThereTablet= in.nextInt()==1;
            in.nextFloat(); in.nextFloat();  // skip cellular's location
            for (int j = 0; j < tagNumber; j++) {
                String tagName = in.next();
                float x = in.nextFloat();
                float y = in.nextFloat();
                tag = new EloTelTag(personName,tagName,x, y);
                territory.addTag(tag);
            }
            if (isThereTablet) {
                in.nextFloat(); in.nextFloat();  // skip tablet's location
            }
        }
    }
    public void runSimulation(Scanner in, PrintStream output) {
        territory.printHeader(output);
        territory.printState(output, step);
        while (in.hasNextLine()) {
            step++;
            String equipment = in.next();
            String[] parts = equipment.split("\\.");
            String personName = parts[0];
            String tagName = parts[1];
            float deltaX = in.nextFloat();
            float deltaY = in.nextFloat();
            EloTelTag tag = territory.getTag(personName, tagName);
            tag.move(deltaX, deltaY);
            territory.printState(output, step);
        }
    }
    private Territory territory;
    private int step=0;
}
