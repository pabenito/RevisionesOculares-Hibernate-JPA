package BDEntities;

import javax.persistence.*;

//Se crea esta clase para no repetir los atributos, aunque pienso que deberiamos dejarlo como estaba para no complicarnos
@Entity
public class Ojo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "ESFERA")
    private float esfera;
    @Basic
    @Column(name = "CILINDRO")
    private float cilindro;
    @Basic
    @Column(name = "ADICION")
    private float adicion;
    @Basic
    @Column(name = "AGUDEZA")
    private float agudeza;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getEsfera() {
        return esfera;
    }

    public void setEsfera(float esfera) {
        this.esfera = esfera;
    }

    public float getCilindro() {
        return cilindro;
    }

    public void setCilindro(float cilindro) {
        this.cilindro = cilindro;
    }

    public float getAdicion() {
        return adicion;
    }

    public void setAdicion(float adicion) {
        this.adicion = adicion;
    }

    public float getAgudeza() {
        return agudeza;
    }

    public void setAgudeza(float agudeza) {
        this.agudeza = agudeza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ojo ojo = (Ojo) o;

        if (id != ojo.id) return false;
        if (Float.compare(ojo.esfera, esfera) != 0) return false;
        if (Float.compare(ojo.cilindro, cilindro) != 0) return false;
        if (Float.compare(ojo.adicion, adicion) != 0) return false;
        if (Float.compare(ojo.agudeza, agudeza) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (esfera != 0.0f ? Float.floatToIntBits(esfera) : 0);
        result = 31 * result + (cilindro != 0.0f ? Float.floatToIntBits(cilindro) : 0);
        result = 31 * result + (adicion != 0.0f ? Float.floatToIntBits(adicion) : 0);
        result = 31 * result + (agudeza != 0.0f ? Float.floatToIntBits(agudeza) : 0);
        return result;
    }
}
