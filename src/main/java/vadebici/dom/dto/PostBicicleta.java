package vadebici.dom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import vadebici.dom.BicicletaStatus;

public class PostBicicleta {
    @NotBlank(message = "O campo 'marca' é obrigatório.")
    private String marca;
    @NotBlank(message = "O campo 'modelo' é obrigatório.")
    private String modelo;
    @NotBlank(message = "O campo 'ano' é obrigatório.")
    private String ano;
    @NotNull(message = "O campo 'numero' é obrigatório.")
    private Integer numero;
    @NotNull(message = "O campo 'status' é obrigatório.")
    private BicicletaStatus status;

    @Override
    public String toString() {
        return "PostBicicleta{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano='" + ano + '\'' +
                ", numero=" + numero +
                ", status=" + status +
                '}';
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
