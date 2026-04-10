import java.util.ArrayList;

public class Celular {
    String nombreDueno;
    String nombre;
    float x, y;
    ETnube nube;

    public Celular(String nombreDueno, String nombre, float x, float y, ETnube nube) {
        this.nombreDueno = nombreDueno;
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.nube = nube;
    }

    public void reportarPosicion(ArrayList<EloTelTag> listaDeTags) {
        this.nube.registrarPosicion(this.nombreDueno, this.nombre, this.x, this.y);

        for (EloTelTag tagActual : listaDeTags) {
            if (this.TAGdetectado(tagActual)) {
                this.nube.registrarPosicion(tagActual.getOwnerName(), tagActual.getName(), tagActual.getX(), tagActual.getY());
            }
        }
    }

    /*public void findMy() {
        this.myVision.FindMy();
    }*/


    public boolean TAGdetectado(EloTelTag tag) {
        float dx = tag.getX() - this.x;
        float dy = tag.getY() - this.y;
        double distancia = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        if (distancia <= 10.0) {
            return true;
        } else {
            return false;
        }
    }
}