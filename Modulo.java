public abstract class Modulo {
    //Atributos
    protected int rol;
    protected int identificador;
    protected String nombre;
    protected boolean estado;
    protected double costo;
    protected int energia;
    protected int datos;
    //Métodos
    public Modulo(int rol, int identificador, String nombre, boolean estado, double costo, int energia, int datos){
        this.rol = rol;
        this.identificador = identificador;
        this.nombre = nombre;
        this.estado = estado;
        this.costo = costo;
        this.energia = energia;
        this.datos = datos;
    }
    public void setRol(int rol) {
        this.rol = rol;
    }
    public int getRol() {
        return rol;
    }
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public int getIdentificador() {
        return identificador;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public boolean getEstado(){
        return estado;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }
    public double getCosto() {
        return costo;
    }
    public void setEnergia(int energia){
        this.energia = energia;
    }
    public int getEnergia() {
        return energia;
    }
    public void setDatos(int datos){
        this.datos = datos;
    }
    public int getDatos() {
        return datos;
    }
    public abstract int[] procesarCiclo(int bateria, int memoria, int ram);
    public abstract void mostrar();
}
