import java.util.HashMap;

public class ETnube {
    private HashMap<String, String> memoria;
    public ETnube() {
        this.memoria = new HashMap<>();
    }
    public void registrarPosicion(String dueno, String equipo, float x, float y) {
        String clave = dueno + "." + equipo;
        String valor = x + "\t" + y;
        this.memoria.put(clave, valor);
    }


    public String obtenerBienesPersonales(String duenoABuscar) {
        String reporte = "";

        String prefijo = duenoABuscar + ".";

        for (String clave : this.memoria.keySet()) {
            if (clave.startsWith(prefijo)) {
                String nombreEquipo = clave.substring(prefijo.length());
                String coordenadas = this.memoria.get(clave);
                reporte += nombreEquipo + ": " + coordenadas + "\n";
            }
        }

        if (reporte.equals("")) {
            return "No se encontraron equipos reportados para " + duenoABuscar;
        }

        return reporte;
    }

}
