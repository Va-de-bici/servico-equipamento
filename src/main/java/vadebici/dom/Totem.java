package vadebici.dom;


public class Totem {

    private String id;
    private String localizacao;

    public Totem(String id, String localizacao) {
        this.id = id;
        this.localizacao = localizacao;
    }

    public Totem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
