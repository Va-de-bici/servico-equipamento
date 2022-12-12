package vadebici.dom.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import vadebici.dom.TrancaStatus;

public class PostTranca {

    @NotNull(message = "O campo 'numero' é obrigatório.")
    private Integer numero;
    @NotBlank(message = "O campo 'localizacao' é obrigatório.")
    private String localizacao;
    @NotBlank(message = "O campo 'anoDeFabricacao' é obrigatório.")

    private String anoDeFabricacao;
    @NotBlank(message = "O campo 'modelo' é obrigatório.")

    private String modelo;
    @NotNull(message = "O campo 'numero' é obrigatório.")
    private TrancaStatus status;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(String anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public TrancaStatus getStatus() {
        return status;
    }

    public void setStatus(TrancaStatus status) {
        this.status = status;
    }
}