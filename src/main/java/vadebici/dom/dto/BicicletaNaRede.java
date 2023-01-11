package vadebici.dom.dto;

import jakarta.validation.constraints.NotBlank;

public class BicicletaNaRede {
    @NotBlank(message = "O campo 'idBicicleta' é obrigatório")
    private String idBicicleta;
    @NotBlank(message = "O campo 'idTranca' é obrigatório")
    private String idTranca;

    public String getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(String idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public String getIdTranca() {
        return idTranca;
    }

    public void setIdTranca(String idTranca) {
        this.idTranca = idTranca;
    }
}
