package vadebici.dom;

public class Tranca {
    private String id;
    private String idTotem;

    private String idBicicleta;
    private String numero;
    private String localizacao;
    private String anoDeFabricacao;
    private String Database;
    private TrancaStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(String idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public String getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(String idTotem) {
        this.idTotem = idTotem;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
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
        return Database;
    }

    public void setModelo(String Database) {
        this.Database = Database;
    }

    public TrancaStatus getStatus() {
        return status;
    }

    public void setStatus(TrancaStatus status) {
        this.status = status;
    }
}
