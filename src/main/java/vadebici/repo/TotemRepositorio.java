package vadebici.repo;

import vadebici.db_in_memory.Database;
import vadebici.dom.Totem;
import vadebici.dom.Tranca;
import vadebici.dom.dto.PostTotem;
import vadebici.exception.GenericApiException;
import vadebici.util.Validacao;

import java.util.*;
import java.util.stream.Collectors;

public class TotemRepositorio {

    public Totem salvarTotem(PostTotem postTotem) {
        Validacao.validarCampos(postTotem);
        Totem totem = new Totem();
        String id = UUID.randomUUID().toString();
        totem.setLocalizacao(postTotem.getLocalizacao());
        totem.setId(id);
        Database.TOTEM_TABLE.put(id, totem);
        return totem;
    }

    public List<Totem> buscarTotens() {
        return Database.TOTEM_TABLE.keySet()
                .stream()
                .map(Database.TOTEM_TABLE::get)
                .collect(Collectors.toList());
    }

    public Totem buscarTotem(String id) {
        Totem totem = Database.TOTEM_TABLE.get(id);
        if (totem == null) throw new GenericApiException(404, "Totem não encontrado");
        return totem;
    }

    public Totem atualizarTotem(String id, Totem novoTotem){
        buscarTotem(id);
        novoTotem.setId(id);
        Database.TOTEM_TABLE.replace(id, novoTotem);
        return novoTotem;
    }

    public void deletarTotem(String id){
        if (Database.TOTEM_TABLE.containsKey(id)){
            Database.TOTEM_TABLE.remove(id);
        } else throw new GenericApiException(404, "Totem não encontrado");
    }

    public List<Tranca> buscarTrancas(String id) {
        return Database.TRANCA_TABLE.keySet()
                .stream()
                .map(Database.TRANCA_TABLE::get)
                .filter(t -> t.getIdTotem().equals(id))
                .collect(Collectors.toList());
    }
}
