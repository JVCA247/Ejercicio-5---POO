public class Energia extends Modulo {
    //Atributos
    private int limite;
    //Métodos
    public Energia(int identificador, String nombre, boolean estado, double costo, int energia, int datos, int limite){
        this.limite = limite;
        int rol = 1;
        super(rol, identificador, nombre, estado, costo, energia, datos);
    }
    public void setLimite(int limite) {
        this.limite = limite;
    }
    public int getLimite() {
        return limite;
    }
    @Override
   //Genera un vector de datos alusivos a las acciones por ciclo
    public int[] procesarCiclo(int bateria, int memoria, int ram) {
        int[] ciclo = new int[4];
         //Esta condicional verifica que no sobrepase el límite de energía energía para funcionar adecuadamente
        if((estado) && (bateria < limite)){
            ciclo[0] = 1;
            ciclo[1] = bateria + energia;
            ciclo[2] = memoria;
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
        System.out.println("Produce: " + energia + "unidades energéticas");
    }
}
