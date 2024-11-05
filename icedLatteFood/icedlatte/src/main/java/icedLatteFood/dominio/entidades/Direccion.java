package icedLatteFood.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Direccion {
    @Column
    private String calle;
    @Column
    private int numero;
    @Column
    private String complemento;
    @Column
    private int codigoPostal;
    @Column
    private String municipio;

    // Constructor
    public Direccion(String calle, int numero, String complemento, int codigoPostal, String municipio) {
        this.calle = calle;
        this.numero = numero;
        this.complemento = complemento;
        this.codigoPostal = codigoPostal;
        this.municipio = municipio;
    }

    // Métodos getters y setters
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    // Método toString para imprimir la dirección
    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", municipio='" + municipio + '\'' +
                '}';
    }

}
