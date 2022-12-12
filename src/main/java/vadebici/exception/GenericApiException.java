package vadebici.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;
@JsonIgnoreProperties({"message", "suppressed", "localizedMessage", "stackTrace", "cause"})
public class GenericApiException extends RuntimeException {
    private String id;
    private Integer codigo;
    private String mensagem;

    public GenericApiException(Integer codigo, String mensagem) {
        super(mensagem);
        this.id = UUID.randomUUID().toString();
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public GenericApiException() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
