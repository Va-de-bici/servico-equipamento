package vadebici.dom.dto;


import jakarta.validation.constraints.NotBlank;

public class PostTotem {
    @NotBlank(message = "O campo 'localizacao' é obrigatório.")
    private String localizacao;

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
