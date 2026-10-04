import java.util.ArrayList;
import java.util.Scanner;

public class Simulacion {
    //Atributos
    private int codigoS;
    private int numero;
    private boolean nosimulando;
    private ArrayList<Modulo> modulos;
    private ArrayList<int[]> vectores;
    private ArrayList<String> historialActividad;
    private ArrayList<String> historialEnergia;
    private ArrayList<String> historialDatos;
    //Métodos
    public Simulacion(int numero){
        this.numero = numero;
        codigoS = numero*100 + 1000;
        nosimulando = true;
        modulos = new ArrayList<Modulo>();
        vectores = new ArrayList<int[]>();
        historialActividad = new ArrayList<String>();
        historialEnergia = new ArrayList<String>();
        historialDatos = new ArrayList<String>();
    }
    public void setCodigoS(int codigoS) {
        this.codigoS = codigoS;
    }
    public int getCodigoS() {
        codigoS++;
        return codigoS;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getNumero() {
        return numero;
    }
    public void setNosimulando(boolean nosimulando) {
        this.nosimulando = nosimulando;
    }
    public boolean getNosimulando(){
        return nosimulando;
    }
    public void setModulos(ArrayList<Modulo> modulos) {
        this.modulos = modulos;
    }
    public ArrayList<Modulo> getModulos() {
        return modulos;
    }
    public void setVectores() {
        vectores = new ArrayList<int[]>(); 
    }
    public ArrayList<int[]> getVectores() {
        return vectores;
    }
    public void setHistorialActividad(ArrayList<String> historialActividad) {
        this.historialActividad = historialActividad;
    }
    public ArrayList<String> getHistorialActividad() {
        return historialActividad;
    }
    public void setHistorialEnergia(ArrayList<String> historialEnergia) {
        this.historialEnergia = historialEnergia;
    }
    public ArrayList<String> getHistorialEnergia() {
        return historialEnergia;
    }
    public void setHistorialDatos(ArrayList<String> historialDatos) {
        this.historialDatos = historialDatos;
    }
    public ArrayList<String> getHistorialDatos() {
        return historialDatos;
    }
    public void masModulo(int rol, String nombre, double costo, int energia, int datos, String custom){
        // Crea y agrega módulos según su tipo
        switch (rol) {
            case 1:
                int limite = Integer.parseInt(custom);
                Energia menergia = new Energia(getCodigoS(), nombre, true, costo, energia, datos, limite);
                modulos.add(menergia);
                System.out.println("Modulo de energía agreagado con éxito");
                menergia.mostrar();
                break;
            
            case 2:
                String tipo = custom;
                Vuelo mvuelo = new Vuelo(getCodigoS(), nombre, true, costo, energia, datos, tipo);
                modulos.add(mvuelo);
                System.out.println("Modulo de vuelo agreagado con éxito");
                mvuelo.mostrar();
                break;

            case 3:
                String estacion = custom;
                Tierra mTierra = new Tierra(getCodigoS(), nombre, true, costo, energia, datos, estacion);
                modulos.add(mTierra);
                System.out.println("Modulo de tierra agreagado con éxito");
                mTierra.mostrar();
                break;
            
        }
    }
    public void menosModulo(int identificador){
        boolean noesta = true;
        for(int n = 0; n < modulos.size(); n++){
            if(modulos.get(n) != null){
                if(modulos.get(n).getIdentificador() == identificador){
                    modulos.remove(identificador);
                    System.out.println("Modulo removido con éxito");
                    noesta = false;
                }
            }
        }
        if (noesta) {
            System.out.println("No se pudo completar la operación, no se logró encontrar el módulo");
        }
    }
    public Modulo verModulo(int identificador){
        Modulo vmodulo = null;
        for(int n = 0; n < modulos.size(); n++){
            if(modulos.get(n) != null){
                if(modulos.get(n).getIdentificador() == identificador){
                    vmodulo = modulos.get(n);
                }
            }
        }
        return vmodulo;
    }
    public void run(int bateria, int memoria, int ram){
        //Ejecuta un ciclo y según lo que haya pasado genera los vectores de acción correspondiente
        for(int n = 0; n < modulos.size(); n++){
            if(modulos.get(n) != null){
                int[] ciclo = modulos.get(n).procesarCiclo(bateria, memoria, ram);
                vectores.add(ciclo);
                int nbateria = ciclo[1];
                int nmemoria = ciclo[2];
                if(ciclo[0] > 0){
                    historialActividad.add("El módulo No." + modulos.get(n).getIdentificador() + " funcionó");
                }
                else{
                    historialActividad.add("El módulo No." + modulos.get(n).getIdentificador() + " no funcionó");
                }
                historialEnergia.add(Integer.toString(nbateria));
                historialDatos.add(Integer.toString(nmemoria));
            }
        }
        System.out.println("Ciclo completado");
        if(nosimulando){
            setNosimulando(false);
        }
    }
    public void verHistorialActividad(){
        //Genera un historial de actividad en el espacio por medio del vector de acciones
        int nciclo = 1;
        System.out.println("Ciclo No. 1");
        for(int n = 0; n < historialActividad.size(); n++){
            int multiplo = n + 1;
            if(modulos.size() > 0){
                if(multiplo % modulos.size() == 0){
                    nciclo++;
                    System.out.println("Ciclo No. " + nciclo);
                }
            }
            System.out.println(multiplo + ". " + historialActividad.get(n));
        }
    }
     public void verHistorialEnergia(){
        //Genera un historial de la energía en el espacio por medio del vector de acciones
        int nciclo = 1;
        System.out.println("Ciclo No. 1");
        for(int n = 0; n < historialEnergia.size(); n++){
            if(modulos.size() > 0){
                if(n + 1 % modulos.size() == 0){
                    nciclo++;
                    System.out.println("Ciclo No. " + nciclo);
                }
            }
            int numero = n + 1;
            System.out.println(numero + ". La diferencia de energía fue de " + historialEnergia.get(n) + "unidades de energía");
        }
    }
     public void verHistorialDatos(){
        //Genera un historial de los datos en el espacio por medio del vector de acciones
        int nciclo = 1;
        System.out.println("Ciclo No. 1");
        for(int n = 0; n < historialDatos.size(); n++){
            if(modulos.size() > 0){
                if(n + 1 % modulos.size() == 0){
                    nciclo++;
                    System.out.println("Ciclo No. " + nciclo);
                }
            }
            int numero = n + 1;
            System.out.println(numero + ". La diferencia de datos fue de " + historialDatos.get(n) + "MB");
        }
    }
    public int entradaIntB(Scanner teclado, int min, int max){
        //Valida que se entren datos válidos
        boolean incorrecto = true;
        int numero = -1;
        while (incorrecto) {
            String entrada = teclado.nextLine();
            try {
                numero = Integer.parseInt(entrada);
                if((numero >=  min) && (numero <= max)){
                    incorrecto = false;
                }
                else{
                    System.out.println("Ingrese un número dentro del rango de opciones");
                }
            } 
            catch (Exception e) {
                System.out.println("Ingrese un número natural a corde a lo que se solicita");
            }
        }
        return numero;
    }
    public void funcionamientoB(Scanner teclado){
        boolean operacionesB = true;
        while (operacionesB) {
            try {
                System.out.println("Menú de simulación No. " + numero);
                if(nosimulando){
                    //Valida que el proceso de simulación no haya comenzado
                    System.out.println("IMPORTANTE: Una vez iniciado el proceso de simulación no va a poder agregar o quitar modulos");
                    System.out.println("¿Que desea hacer?");
                    if(modulos.size() < 10){
                        //Valida que no se haya alcanzado el límite de módulos
                        System.out.println("1. Agregar un nuevo módulo");
                    }
                    else{
                        System.out.println("1. Ha alcanzado el límite de 10 módulos");
                    }
                    System.out.println("2. Eliminar un módulo");
                    System.out.println("3. Ver un módulo");
                    System.out.println("4. Ver todos los módulos en orden");
                    System.out.println("5. Empezar simulación");
                    System.out.println("6. Salir");
                    System.out.println("Ingrese el número de la opción correcta");
                    int eleccion1B = entradaIntB(teclado, 1, 6);
                    switch (eleccion1B) {
                        //Ejecuta la elección según el menú
                        case 1:
                            System.out.println("Ingrese el nombre del módulo");
                            String nombre1 = teclado.nextLine();
                            System.out.println("Ingrese el costo del módulo");
                            int costo1 = entradaIntB(teclado, 0, 31999);
                            System.out.println("¿Cuál es el rol de su módulo?");
                            System.out.println("1. Módulo de energía");
                            System.out.println("2. Módulo de vuelo");
                            System.out.println("3. Módulo de tierra");
                            System.out.println("Ingrese el número de la opción correcta");
                            int eleccion2B = entradaIntB(teclado, 1, 3);
                            switch (eleccion2B) {
                                case 1:
                                    System.out.println("Ingrese la cantidad de energía que produce");
                                    int energia11 = entradaIntB(teclado, 0, 31999);
                                    System.out.println("Ingrese el límite de energía");
                                    String limite1 = Integer.toString(entradaIntB(teclado, 0, 31999));
                                    masModulo(eleccion2B, nombre1, costo1, energia11, 0, limite1);
                                    break;
                            
                                case 2:
                                    System.out.println("Ingrese la cantidad de energía que consume");
                                    int energia12 = - entradaIntB(teclado, 0, 31999);
                                    System.out.println("Ingrese la cantidad de datos que genera");
                                    int datos12 = entradaIntB(teclado, 0, 31999);
                                    System.out.println("Ingrese el tipo de datos que recolecta");
                                    String tipo1 = teclado.nextLine();
                                    masModulo(eleccion2B, nombre1, costo1, energia12, datos12, tipo1);
                                    break;

                                case 3:
                                    System.out.println("Ingrese la cantidad de energía que consume");
                                    int energia13 = - entradaIntB(teclado, 0, 31999);
                                    System.out.println("Ingrese la cantidad de datos que exporta a tierra");
                                    int datos13 = entradaIntB(teclado, 0, 31999);
                                    System.out.println("Ingrese el nombre de la estación receptora");
                                    String estacion1 = teclado.nextLine();
                                    masModulo(eleccion2B, nombre1, costo1, energia13, datos13, estacion1);
                                    break;
                            }
                            break;
                        
                        case 2:
                            System.out.println("Ingrese el identificador del módulo a eliminar");
                            int codigo2 = entradaIntB(teclado, 0, 31999);
                            menosModulo(codigo2);
                            break;
                        
                        case 3:
                            System.out.println("Ingrese el identificador del módulo que le interesa");
                            int codigo3 = entradaIntB(teclado, 0, 31999);
                            verModulo(codigo3); 
                            break;
                        
                        case 4:
                            for(int n = 0; n < modulos.size(); n++){
                                if(modulos.get(n) != null){
                                    modulos.get(n).mostrar();
                                }
                            }
                            break;
                        
                        case 5:
                            System.out.println("ADVERTENCIA. Si empieza a simular no puede editar módulos");
                            System.out.println("¿Desea proseguir? (si/no)");
                            String respuesta5 = teclado.nextLine();
                            if(respuesta5.equalsIgnoreCase("si")){
                                System.out.println("¿Cuantas simulaciones desea realizar?");
                                int veces5 = entradaIntB(teclado, 0, 1000);
                                for(int n = 0; n < veces5; n++){
                                    if(vectores.size() > 0){
                                        run(0, 0, 0);
                                    }
                                    else{
                                        int so = vectores.size() - 1;
                                        int[] ciclo5 = vectores.get(so);
                                        run(ciclo5[1], ciclo5[2], ciclo5[3]);
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                else{
                    System.out.println("¿Qué desea hacer?");
                    System.out.println("1. Ver módulos");
                    System.out.println("2. Ver todos los m+odulos en orden");
                    System.out.println("3. Iniciar simulación");
                    System.out.println("4. Ver el historial de actividad");
                    System.out.println("5. Ver el historial del consumo de energía");
                    System.out.println("6. Ver historial de datos en el espacios");
                    System.out.println("7. Salir");
                    System.out.println("Ingrese el número de la opción correcta");
                    int eleccion3B = entradaIntB(teclado, 0, 7);
                    switch (eleccion3B) {
                        case 1:
                            System.out.println("Ingrese el identificador del módulo que le interesa");
                            int codigo3 = entradaIntB(teclado, 0, 31999);
                            verModulo(codigo3); 
                            break;
                        case 2:
                             for(int n = 0; n < modulos.size(); n++){
                                if(modulos.get(n) != null){
                                    modulos.get(n).mostrar();
                                }
                            }
                            break;
                        case 3:
                            System.out.println("¿Cuantas simulaciones desea realizar?");
                                int veces5 = entradaIntB(teclado, 0, 1000);
                                for(int n = 0; n < veces5; n++){
                                    if(vectores.size() > 0){
                                        run(0, 0, 0);
                                    }
                                    else{
                                        int so = vectores.size() - 1;
                                        int[] ciclo5 = vectores.get(so);
                                        run(ciclo5[1], ciclo5[2], ciclo5[3]);
                                    }
                                }
                            break;
                        case 4:
                            verHistorialActividad();
                            break;
                        case 6:
                            verHistorialDatos();
                            break;
                        case 5:
                            verHistorialEnergia();
                            break;
                    
                        default:
                            break;
                    }
                } 
            } 
            catch (Exception e) {
                System.out.println("Felicidades, encontraste un error");
            }
            finally{
                System.out.println("¿Desea realizar otra operación dentro de la simulación? (si / no)");
                String salir = teclado.nextLine();
                if (!salir.equalsIgnoreCase("si")) {
                    operacionesB = false;
                }
            }
        }
    }
}
