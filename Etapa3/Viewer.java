import java.util.ArrayList;

public class Viewer {
    private String ownerName;
    private ETnube nube;

    public Viewer(String ownerName, ETnube nube) {
        this.ownerName = ownerName;
        this.nube = nube;
    }

    public void FindMy(){
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

            if (!nombreEquipo.equals("celular") && !nombreEquipo.equals("table")){
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
}
