package vadebici.dom;

import java.util.Objects;

public class Bicicleta {
    private String id;
    private String marca;
    private String Database;
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
        return Objects.hash(id, marca, Database, ano, numero, status);
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "id='" + id + '\'' +
                ", marca='" + marca + '\'' +
                ", Database='" + Database + '\'' +
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
        return Database;
    }

    public void setModelo(String Database) {
        this.Database = Database;
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
