package vadebici.dom.dto;

import jakarta.validation.constraints.NotBlank;

public class TrancaNaRede {
    @NotBlank(message = "O campo 'idTotem' é obrigatório")
    private String idTotem;
    @NotBlank(message = "O campo 'idTranca' é obrigatório")
    private String idTranca;

    public String getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(String idTotem) {
        this.idTotem = idTotem;
    }

    public String getIdTranca() {
        return idTranca;
    }

    public void setIdTranca(String idTranca) {
        this.idTranca = idTranca;
    }
}
