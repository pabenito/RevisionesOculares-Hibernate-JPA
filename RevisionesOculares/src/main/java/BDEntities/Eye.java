package BDEntities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "teye", schema = "revocular")
public class Eye {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "NIF", insertable = false, updatable = false)
    private String nif;
    @Basic
    @Column(name = "CONSULTA")
    private Date consulta;
    @Basic
    @Column(name = "OD_ESFERA")
    private double odEsfera;
    @Basic
    @Column(name = "OD_CILINDRO")
    private double odCilindro;
    @Basic
    @Column(name = "OD_ADICION")
    private double odAdicion;
    @Basic
    @Column(name = "OD_AGUDEZA")
    private double odAgudeza;
    @Basic
    @Column(name = "OI_ESFERA")
    private double oiEsfera;
    @Basic
    @Column(name = "OI_CILINDRO")
    private double oiCilindro;
    @Basic
    @Column(name = "OI_ADICION")
    private double oiAdicion;
    @Basic
    @Column(name = "OI_AGUDEZA")
    private double oiAgudeza;
    @ManyToOne
    @JoinColumn(name = "NIF", referencedColumnName = "NIF", nullable = false)
    private Client tclientByNif;

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

    public java.sql.Date getConsulta() {
        return consulta;
    }

    public void setConsulta(java.sql.Date consulta) {
        this.consulta =consulta;
    }

    public double getOdEsfera() {
        return odEsfera;
    }

    public void setOdEsfera(double odEsfera) {
        this.odEsfera = odEsfera;
    }

    public double getOdCilindro() {
        return odCilindro;
    }

    public void setOdCilindro(double odCilindro) {
        this.odCilindro = odCilindro;
    }

    public double getOdAdicion() {
        return odAdicion;
    }

    public void setOdAdicion(double odAdicion) {
        this.odAdicion = odAdicion;
    }

    public double getOdAgudeza() {
        return odAgudeza;
    }

    public void setOdAgudeza(double odAgudeza) {
        this.odAgudeza = odAgudeza;
    }

    public double getOiEsfera() {
        return oiEsfera;
    }

    public void setOiEsfera(double oiEsfera) {
        this.oiEsfera = oiEsfera;
    }

    public double getOiCilindro() {
        return oiCilindro;
    }

    public void setOiCilindro(double oiCilindro) {
        this.oiCilindro = oiCilindro;
    }

    public double getOiAdicion() {
        return oiAdicion;
    }

    public void setOiAdicion(double oiAdicion) {
        this.oiAdicion = oiAdicion;
    }

    public double getOiAgudeza() {
        return oiAgudeza;
    }

    public void setOiAgudeza(double oiAgudeza) {
        this.oiAgudeza = oiAgudeza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Eye eye = (Eye) o;

        if (id != eye.id) return false;
        if (Double.compare(eye.odEsfera, odEsfera) != 0) return false;
        if (Double.compare(eye.odCilindro, odCilindro) != 0) return false;
        if (Double.compare(eye.odAdicion, odAdicion) != 0) return false;
        if (Double.compare(eye.odAgudeza, odAgudeza) != 0) return false;
        if (Double.compare(eye.oiEsfera, oiEsfera) != 0) return false;
        if (Double.compare(eye.oiCilindro, oiCilindro) != 0) return false;
        if (Double.compare(eye.oiAdicion, oiAdicion) != 0) return false;
        if (Double.compare(eye.oiAgudeza, oiAgudeza) != 0) return false;
        if (nif != null ? !nif.equals(eye.nif) : eye.nif != null) return false;
        if (consulta != null ? !consulta.equals(eye.consulta) : eye.consulta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (consulta != null ? consulta.hashCode() : 0);
        temp = Double.doubleToLongBits(odEsfera);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(odCilindro);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(odAdicion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(odAgudeza);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(oiEsfera);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(oiCilindro);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(oiAdicion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(oiAgudeza);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Client getTclientByNif() {
        return tclientByNif;
    }

    public void setTclientByNif(Client tclientByNif) {
        this.tclientByNif = tclientByNif;
    }
}
