public class Vuelo extends Modulo{
    //Atributos
    public String tipo;
    //Métodos
    public Vuelo(int identificador, String nombre, boolean estado, double costo, int energia, int datos, String tipo){
        this.tipo = tipo;
        int rol = 2;
        super(rol, identificador, nombre, estado, costo, energia, datos);
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    @Override
    //Genera un vector de datos alusivos a las acciones por ciclo
    public int[] procesarCiclo(int bateria, int memoria, int ram) {
        int[] ciclo = new int[4];
        //Esta condicional verifica que haya energía para funcionar adecuadamente
        if((estado) && (0 <= bateria + energia)){
            ciclo[0] = 1;
            ciclo[1] = bateria + energia;
            ciclo[2] = memoria + datos;
            ciclo[3] = ram;
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
        System.out.println("Colecta: " + datos + "MB");
    }
}
