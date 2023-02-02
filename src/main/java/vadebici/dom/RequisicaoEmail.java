package vadebici.dom;

import jakarta.validation.constraints.NotBlank;

public class RequisicaoEmail {
    @NotBlank(message = "O campo 'email' é obrigatório")
    private String endereco;
    @NotBlank(message = "O campo 'mensagem' é obrigatório")
    private String mensagem;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String email) {
        this.endereco = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
