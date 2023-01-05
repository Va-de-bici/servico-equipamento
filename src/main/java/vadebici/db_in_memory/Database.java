package vadebici.db_in_memory;

import vadebici.dom.*;
import java.util.*;

public class Database {
    public static final Map<String, Totem> TOTEM_TABLE = new HashMap<>();
    public static final Map<String, Tranca> TRANCA_TABLE = new HashMap<>();
    public static final Map<String, Bicicleta> BICICLETA_TABLE = new HashMap<>();

    private Database() {
    }

    public static void limpar() {
        TOTEM_TABLE.clear();
        TRANCA_TABLE.clear();
        BICICLETA_TABLE.clear();
    }

    public static void carregarDados() {
        limpar();
        Totem totem = getTotem();
        Tranca tranca = getTranca();
        Bicicleta bicicleta = getBicicleta();

        TRANCA_TABLE.put(tranca.getId(), tranca);
        TRANCA_TABLE.put(tranca.getId(), tranca);
        TOTEM_TABLE.put(totem.getId(), totem);
        BICICLETA_TABLE.put(bicicleta.getId(), bicicleta);
        BICICLETA_TABLE.put(bicicleta.getId(), bicicleta);
    }

    private static Tranca getTranca() {
        Tranca tranca = new Tranca();
        tranca.setId("123");
        tranca.setIdBicicleta(null);
        tranca.setIdTotem("123");
        tranca.setStatus(TrancaStatus.OCUPADA);
        tranca.setNumero("123");
        tranca.setAnoDeFabricacao("123");
        tranca.setLocalizacao("123");
        tranca.setModelo("123");
        return tranca;
    }

    private static Totem getTotem() {
        Totem totem = new Totem();
        totem.setId("123");
        totem.setLocalizacao("123");
        return totem;
    }

    private static Bicicleta getBicicleta() {
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId("123");
        bicicleta.setMarca("123");
        bicicleta.setModelo("123");
        bicicleta.setAno("123");
        bicicleta.setNumero(123);
        bicicleta.setStatus(BicicletaStatus.DISPONIVEL);
        return bicicleta;
    }
}
