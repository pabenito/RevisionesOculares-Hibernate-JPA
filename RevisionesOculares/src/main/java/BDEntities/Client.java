package BDEntities;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tclient", schema = "revocular")
public class Client {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "tclientByNif")
    private Collection<Eye> teyesByNif;

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

        Client client = (Client) o;

        if (edad != client.edad) return false;
        if (nif != null ? !nif.equals(client.nif) : client.nif != null) return false;
        if (nombre != null ? !nombre.equals(client.nombre) : client.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(client.apellidos) : client.apellidos != null) return false;

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


    public Collection<Eye> getTeyesByNif() {
        return teyesByNif;
    }

    public void setTeyesByNif(Collection<Eye> teyesByNif) {
        this.teyesByNif = teyesByNif;
    }
}
