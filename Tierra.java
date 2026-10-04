public class Tierra extends Modulo{
    //Atributos
    public String estacion;
    //Métodos
    public Tierra(int identificador, String nombre, boolean estado, double costo, int energia, int datos, String estacion){
        this.estacion = estacion;
        int rol = 3;
        super(rol, identificador, nombre, estado, costo, energia, datos);
    }
    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }
    public String getEstacion() {
        return estacion;
    }
    @Override
    //Genera un vector de datos alusivos a las acciones por ciclo
    public int[] procesarCiclo(int bateria, int memoria, int ram) {
        int[] ciclo = new int[4];
        //Esta condicional verifica que haya datos que enviar y energía para funcionar adecuadamente
        if((estado) && (bateria + energia >= 0) && (memoria > 0)){
            ciclo[0] = 1;
            ciclo[1] = bateria + energia;
            if(datos > memoria){
                ciclo[2] = 0;
                ciclo[3] = ram + memoria;
            }
            else{
                ciclo[2] = memoria - datos;
                ciclo[3] = ram + datos;
            }
        }
        else{
            ciclo[0] = 0;
            ciclo[1] = bateria;
            ciclo[2] = memoria;
            ciclo[3] = ram;
        }
        return ciclo;
    }
    public void mostrar() {
        System.out.println("Módulo de energía No. " + identificador);
        System.out.println("Nombre: " + nombre);
        //Traduce de boolean a String
        if(estado){
            System.out.println("Condiciones estables");
        }
        else{
            System.out.println("Presenta fallas");
        }
        System.out.println("Costo: Q" + costo);
        int maenergia = - energia;
        System.out.println("Consume: " + maenergia + "unidades de energía");
        System.out.println("Envía: " + datos + "MB");
    }
}
