import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class Viewer {
    private ETnube nube;
    private PrintWriter csvWriter;
    private List<String> duenos;
    private List<String> equipos;

    public Viewer(ETnube nube, List<String> duenos, List<String> equipos) {
        this.nube = nube;
        this.duenos = duenos;
        this.equipos = equipos;

        try {
            this.csvWriter = new PrintWriter(new File("output.csv"));
            escribirEncabezado();
        } catch (IOException e) {
            System.out.println("Error al crear output.csv" + e.getMessage());
        }
    }

    private void escribirEncabezado() {
        StringBuilder sb = new StringBuilder("Step");
        for (int i = 0; i < duenos.size(); i++) {
            sb.append("\t").append(duenos.get(i)).append(".").append(equipos.get(i)).append(".x\t.y");
        }
        csvWriter.println(sb.toString());
    }

    public void registrarPaso(int step){
        StringBuilder sb = new StringBuilder();
        sb.append(step);
        for (int i = 0; i < duenos.size(); i++) {
            float[] pos = nube.obtenerUltimaPosicion(duenos.get(i), equipos.get(i));
            sb.append("\t").append(pos[0]).append("\t").append(pos[1]);
        }
        csvWriter.println(sb.toString());
        csvWriter.flush();
    }

    public void FindMy(String ownerName){
        System.out.println("Cosas de "+ ownerName + ": ");

        String reporte = nube.obtenerBienesPersonales(ownerName);

        if (reporte.startsWith("No se encontraron")){
            System.out.println(reporte);
            return;
        }

        String[] lineasEquipos = reporte.split("\n");

        System.out.println("Ítems: ");
        for (String linea : lineasEquipos){
            String[] partes = linea.split(": ");
            String nombreEquipo = partes[0];

            String coordenadas = partes[1].replace("\t",", ");

            if (!nombreEquipo.equals("celular") && !nombreEquipo.equals("tablet")){
                System.out.println(nombreEquipo +": "+coordenadas);
            }
        }

        System.out.println("Dispositivos: ");
        for (String linea : lineasEquipos){
            String[] partes = linea.split(": ");
            String nombreEquipo = partes[0];

            String coordenadas = partes[1].replace("\t",", ");

            if (nombreEquipo.equals("celular") || nombreEquipo.equals("tablet")){
                System.out.println(nombreEquipo +": "+coordenadas);
            }

        }
    }

    public void cerrar(){
        if(csvWriter != null){
            csvWriter.close();
        }
    }
}
