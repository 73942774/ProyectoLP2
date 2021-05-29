
package Modelo;


public class Cliente {
    int id;
    String dni;
    String nom;
    String dir;
    String estado;
    
    public Cliente(){
    }

    public Cliente(int id, String dni, String nom, String direccion, String estado) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.dir = direccion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getDir() {
        return dir;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}

