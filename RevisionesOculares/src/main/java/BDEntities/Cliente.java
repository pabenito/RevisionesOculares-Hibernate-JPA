package BDEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NIF")
    private String nif;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic
    @Column(name = "EDAD")
    private int edad;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (edad != cliente.edad) return false;
        if (!Objects.equals(nif, cliente.nif)) return false;
        if (!Objects.equals(nombre, cliente.nombre)) return false;
        if (!Objects.equals(apellidos, cliente.apellidos)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nif != null ? nif.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + edad;
        return result;
    }
}
