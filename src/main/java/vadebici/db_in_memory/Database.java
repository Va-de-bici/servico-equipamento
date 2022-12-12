package vadebici.db_in_memory;

import vadebici.dom.*;

import java.util.*;

public class Database {
    public static final Map<String, Totem> TOTEM_TABLE = new HashMap<>();
    public static final Map<String, Tranca> TRANCA_TABLE = new HashMap<>();

    public static void limpar() {
        TOTEM_TABLE.clear();
        TRANCA_TABLE.clear();
    }

    public static void carregarDados() {
        limpar();
        Totem totem = getTotem();
        Tranca tranca = getTranca();
        TRANCA_TABLE.put(tranca.getId(), tranca);
        TRANCA_TABLE.put(tranca.getId(), tranca);
        TOTEM_TABLE.put(totem.getId(), totem);
    }

    private static Tranca getTranca() {
        Tranca tranca = new Tranca();
        tranca.setId("123");
        tranca.setIdBicicleta("945");
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
}
