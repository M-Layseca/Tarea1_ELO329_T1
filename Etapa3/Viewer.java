import java.util.ArrayList;

public class Viewer {
    private String ownerName;
    private ETnube nube;

    public Viewer(String ownerName, ETnube nube) {
        this.ownerName = ownerName;
        this.nube = nube;
    }

    public void FindMy(){
        System.out.println("Cosas de "+ownerName+": ");
        ArrayList<ETNube.Data> listaEquipos = nube.obtenerEquipos(ownerName);

        System.out.println("Ítems:");
        for (ETNube.Data equipo: listaEquipos){
            if (!equipo.equipmentName.equals("celular") && !equipo.equipmentName.equals("tablet")){
                System.out.println(equipo.equipmentName +": "+equipo.location.getX() + ", "+ equipo.location.getY());
            }
        }

        System.out.println("Dispositivos:");
        for (ETNube.Data equipo: listaEquipos){
            if (equipo.equipmentName.equals("celular") || equipo.equipmentName.equals("tablet")){
                System.out.println(equipo.equipmentName +": "+equipo.location.getX() + ", "+ equipo.location.getY());
            }
        }
    }
}
