package BDEntities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Revision {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "NIF")
    private String nif;
    @Basic
    @Column(name = "CONSULTA")
    private Date consulta;
    @Basic
    @Column(name = "OJO_DERECHO")
    private int ojoDerecho;
    @Basic
    @Column(name = "OJO_IZQUIERDO")
    private int ojoIzquierdo;
    @ManyToOne
    @JoinColumn(name = "NIF", referencedColumnName = "NIF", nullable = false)
    private Cliente clienteByNif;
    @ManyToOne
    @JoinColumn(name = "OJO_DERECHO", referencedColumnName = "ID", nullable = false)
    private Ojo ojoByOjoDerecho;
    @ManyToOne
    @JoinColumn(name = "OJO_DERECHO", referencedColumnName = "ID", nullable = false)
    private Ojo ojoByOjoDerecho_0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Date getConsulta() {
        return consulta;
    }

    public void setConsulta(Date consulta) {
        this.consulta = consulta;
    }

    public int getOjoDerecho() {
        return ojoDerecho;
    }

    public void setOjoDerecho(int ojoDerecho) {
        this.ojoDerecho = ojoDerecho;
    }

    public int getOjoIzquierdo() {
        return ojoIzquierdo;
    }

    public void setOjoIzquierdo(int ojoIzquierdo) {
        this.ojoIzquierdo = ojoIzquierdo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Revision revision = (Revision) o;

        if (id != revision.id) return false;
        if (ojoDerecho != revision.ojoDerecho) return false;
        if (ojoIzquierdo != revision.ojoIzquierdo) return false;
        if (nif != null ? !nif.equals(revision.nif) : revision.nif != null) return false;
        if (consulta != null ? !consulta.equals(revision.consulta) : revision.consulta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (consulta != null ? consulta.hashCode() : 0);
        result = 31 * result + ojoDerecho;
        result = 31 * result + ojoIzquierdo;
        return result;
    }

    public Cliente getClienteByNif() {
        return clienteByNif;
    }

    public void setClienteByNif(Cliente clienteByNif) {
        this.clienteByNif = clienteByNif;
    }

    public Ojo getOjoByOjoDerecho() {
        return ojoByOjoDerecho;
    }

    public void setOjoByOjoDerecho(Ojo ojoByOjoDerecho) {
        this.ojoByOjoDerecho = ojoByOjoDerecho;
    }

    public Ojo getOjoByOjoDerecho_0() {
        return ojoByOjoDerecho_0;
    }

    public void setOjoByOjoDerecho_0(Ojo ojoByOjoDerecho_0) {
        this.ojoByOjoDerecho_0 = ojoByOjoDerecho_0;
    }
}
