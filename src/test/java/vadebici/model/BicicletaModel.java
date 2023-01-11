package vadebici.model;

import vadebici.dom.Bicicleta;
import vadebici.dom.BicicletaStatus;
import vadebici.dom.dto.PostBicicleta;
import vadebici.dom.dto.BicicletaNaRede;

public class BicicletaModel {

    public static Bicicleta getBicicleta() {
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId("123");
        bicicleta.setMarca("123");
        bicicleta.setModelo("123");
        bicicleta.setAno("123");
        bicicleta.setNumero(123);
        bicicleta.setStatus(BicicletaStatus.DISPONIVEL);
        return bicicleta;
    }

    public static PostBicicleta getPostBicicleta() {
        PostBicicleta postBicicleta = new PostBicicleta();
        postBicicleta.setStatus(BicicletaStatus.DISPONIVEL);
        postBicicleta.setMarca("123");
        postBicicleta.setModelo("123");
        postBicicleta.setAno("123");
        postBicicleta.setNumero(123);
        return postBicicleta;
    }

    public static BicicletaNaRede getBicicletaNaRede() {
        BicicletaNaRede bicicletaNaRede = new BicicletaNaRede();
        bicicletaNaRede.setIdBicicleta("123");
        bicicletaNaRede.setIdTranca("123");
        return bicicletaNaRede;
    }
}
