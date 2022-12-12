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

    public void setNumero(Integer num) {
        this.numero = num;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String loc) {
        this.localizacao = loc;
    }

    public String getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(String anoFab) {
        this.anoDeFabricacao = anoFab;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String mod) {
        this.modelo = mod;
    }

    public TrancaStatus getStatus() {
        return status;
    }

    public void setStatus(TrancaStatus stt) {
        this.status = stt;
    }
}