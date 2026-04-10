import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T1Stage1 {
    T1Stage1() {
        territory = new Territory();
        nube = new ETnube();
    }
    public static void main (String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            System.exit(-1);
        }
        Scanner confFile = new Scanner(new File(args[0]));
        confFile.useLocale(java.util.Locale.US);
        Scanner movFile = new Scanner(new File(args[1]));
        movFile.useLocale(java.util.Locale.US);

        T1Stage1 stage = new T1Stage1();
        stage.setupSimulator(confFile);

        for (Celular cel : stage.territory.getTodosLosCelulares()) {
            cel.reportarPosicion(stage.territory.getTags());
        }

        Viewer visorCentral = new Viewer(stage.nube, stage.globalDuenos, stage.globalEquipos);
        stage.runSimulation(movFile, System.out, visorCentral);

        System.out.println("\n--- REPORTE FINAL DE LA NUBE (FindMy) ---");
        for (Celular celActual : stage.territory.getTodosLosCelulares()) {
            System.out.println("\n========== PANTALLA DE " + celActual.nombreDueno.toUpperCase() + " ==========");
            visorCentral.FindMy(celActual.nombreDueno);
        }
        visorCentral.cerrar();
    }
    public void setupSimulator(Scanner in) {  // create objects from file
        int personNumber = in.nextInt();

        EloTelTag tag;

        for (int i = 0; i < personNumber; i++) {
            String personName = in.next();
            int tagNumber = in.nextInt();
            boolean isThereTablet = in.nextInt() == 1;
            float celX = in.nextFloat();
            float celY = in.nextFloat();
            Celular nuevoCelular = new Celular(personName, "celular", celX, celY, this.nube);
            territory.addCelular(nuevoCelular);
            this.globalDuenos.add(personName);
            this.globalEquipos.add("celular");

            for (int j = 0; j < tagNumber; j++) {
                String tagName = in.next();
                float x = in.nextFloat();
                float y = in.nextFloat();
                tag = new EloTelTag(personName, tagName, x, y);
                territory.addTag(tag);
                this.globalDuenos.add(personName);
                this.globalEquipos.add(tagName);
            }
            if (isThereTablet) {
                in.nextFloat(); in.nextFloat();  // skip tablet's location
            }
        }
    }

    public void runSimulation(Scanner in, PrintStream output, Viewer visor) {
        territory.printHeader(output);
        territory.printState(output, step);
        visor.registrarPaso(step);
        while (in.hasNext()) {
            step++;
            String equipment = in.next();
            if (!equipment.contains(".")) {
                continue;
            }

            String[] parts = equipment.split("\\.");
            String personName = parts[0];
            String equipmentName = parts[1];
            if (!in.hasNextFloat()) break;
            float deltaX = in.nextFloat();

            if (!in.hasNextFloat()) break;
            float deltaY = in.nextFloat();

            if (equipmentName.equals("celular")) {
                Celular cel = territory.getCelular(personName);
                if (cel != null) {
                    cel.x += deltaX;
                    cel.y += deltaY;
                    cel.reportarPosicion(territory.getTags());
                }
            } else {
                EloTelTag tag = territory.getTag(personName, equipmentName);
                if (tag != null) {
                    tag.move(deltaX, deltaY);
                }
            }

            visor.registrarPaso(step);
            territory.printState(output, step);
        }
    }


    private Territory territory;
    private int step=0;
    private ETnube nube;
    public List<String> globalDuenos = new ArrayList<>();
    public List<String> globalEquipos = new ArrayList<>();
}
