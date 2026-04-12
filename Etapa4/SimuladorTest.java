import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class SimuladorTest {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java SimuladorTest config.txt move.txt");
            return;
        }

        ETnube nube = new ETnube();
        ArrayList<Celular> celulares = new ArrayList<>();
        ArrayList<EloTelTag> tags = new ArrayList<>();
        ArrayList<Tablet> tablets = new ArrayList<>();
        List<String> listDuenos = new ArrayList<>();
        List<String> listEquipos = new ArrayList<>();

        // --- 1. LECTURA DE CONFIG.TXT ---
        try (Scanner scConfig = new Scanner(new File(args[0]))) {
            scConfig.useLocale(Locale.US); // Para asegurar que lea puntos decimales

            // Usamos nextInt() en lugar de parseInt() porque ignora caracteres invisibles (BOM)
            if (!scConfig.hasNextInt()) return;
            int numPersonas = scConfig.nextInt();

            for (int i = 0; i < numPersonas; i++) {
                String owner = scConfig.next();
                int numTags = scConfig.nextInt();
                boolean tieneTablet = scConfig.nextInt() == 1;

                // Celular
                float celX = scConfig.nextFloat();
                float celY = scConfig.nextFloat();
                celulares.add(new Celular(owner, "celular", celX, celY, nube));
                listDuenos.add(owner); listEquipos.add("celular");

                // Tags
                for (int t = 0; t < numTags; t++) {
                    String tName = scConfig.next();
                    float tx = scConfig.nextFloat();
                    float ty = scConfig.nextFloat();
                    tags.add(new EloTelTag(owner, tName, tx, ty));
                    listDuenos.add(owner); listEquipos.add(tName);
                }

                // Tablet
                if (tieneTablet) {
                    float tabX = scConfig.nextFloat();
                    float tabY = scConfig.nextFloat();
                    tablets.add(new Tablet(owner, "tablet", tabX, tabY));
                    listDuenos.add(owner); listEquipos.add("tablet");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo de configuración no encontrado.");
            return;
        }

        Viewer viewer = new Viewer(nube, listDuenos, listEquipos);
        for (Tablet t : tablets) t.setViewer(viewer);
        for (Celular c : celulares) c.setViewer(viewer);

        ArrayList<EloTelTag> todosLosRastreables = new ArrayList<>();
        todosLosRastreables.addAll(tags);
        todosLosRastreables.addAll(tablets);

        for (Celular c : celulares) c.reportarPosicion(todosLosRastreables);
        viewer.registrarPaso(0);

        // --- 2. LECTURA DE MOVE.TXT ---
        try (Scanner scMove = new Scanner(new File(args[1]))) {
            scMove.useLocale(Locale.US);
            int step = 1;
            while (scMove.hasNext()) {
                String comandoFull = scMove.next();
                String[] id = comandoFull.split("\\.");
                String dueno = id[0];
                String equipo = id[1];

                String accion = scMove.next();

                if (accion.equals("FindMy")) {
                    if (equipo.equals("celular")) {
                        for (Celular c : celulares) {
                            if(c.nombreDueno.equals(dueno)){c.findMy();}
                        }
                    } else if (equipo.equals("tablet")) {
                        for(Tablet t : tablets) {
                            if (t.getOwnerName().equals(dueno)) t.findMy();
                        }
                    }
                } else {
                    float dx = Float.parseFloat(accion);
                    float dy = scMove.nextFloat();

                    if (equipo.equals("celular")) {
                        for(Celular c : celulares) if(c.nombreDueno.equals(dueno)) { c.x += dx; c.y += dy; }
                    } else if (equipo.equals("tablet")) {
                        for(Tablet t : tablets) if(t.getOwnerName().equals(dueno)) t.move(dx, dy);
                    } else {
                        for(EloTelTag t : tags) if(t.getOwnerName().equals(dueno) && t.getName().equals(equipo)) t.move(dx, dy);
                    }
                }

                for (Celular c : celulares) c.reportarPosicion(todosLosRastreables);
                viewer.registrarPaso(step);
                step++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo de movimientos no encontrado.");
        }
        viewer.cerrar();
    }
}