package vadebici.dom;

import jakarta.validation.constraints.NotBlank;

public class RequisicaoEmail {
    @NotBlank(message = "O campo 'email' é obrigatório")
    private String email;
    @NotBlank(message = "O campo 'mensagem' é obrigatório")
    private String mensagem;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
