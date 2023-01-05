package vadebici.dom;

import java.util.Objects;

public class Bicicleta {
    private String id;
    private String marca;
    private String modelo;
    private String ano;
    private Integer numero;
    private BicicletaStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicicleta bicicleta = (Bicicleta) o;
        return Objects.equals(id, bicicleta.id) && Objects.equals(marca, bicicleta.marca) && Objects.equals(Database, bicicleta.Database) && Objects.equals(ano, bicicleta.ano) && Objects.equals(numero, bicicleta.numero) && status == bicicleta.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, modelo, ano, numero, status);
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "id='" + id + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano='" + ano + '\'' +
                ", numero=" + numero +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modeloBici) {
        this.modelo = modeloBici;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public BicicletaStatus getStatus() {
        return status;
    }

    public void setStatus(BicicletaStatus status) {
        this.status = status;
    }
}
