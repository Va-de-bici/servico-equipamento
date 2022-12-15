package vadebici.model;

import vadebici.dom.Tranca;
import vadebici.dom.TrancaStatus;
import vadebici.dom.dto.PostTranca;

public class TrancaModel {

    public static Tranca getTranca() {
        Tranca tranca = new Tranca();
        tranca.setId("123");
        tranca.setIdBicicleta("123");
        tranca.setIdTotem("123");
        tranca.setModelo("123");
        tranca.setNumero("123");
        tranca.setAnoDeFabricacao("123");
        tranca.setLocalizacao("123");
        tranca.setStatus(TrancaStatus.OCUPADA);
        return tranca;
    }

    public static PostTranca getPostTranca() {
        PostTranca postTranca = new PostTranca();
        postTranca.setLocalizacao("123");
        postTranca.setModelo("123");
        postTranca.setAnoDeFabricacao("123");
        postTranca.setNumero(123);
        postTranca.setStatus(TrancaStatus.DISPONIVEL);
        return postTranca;
    }
}
