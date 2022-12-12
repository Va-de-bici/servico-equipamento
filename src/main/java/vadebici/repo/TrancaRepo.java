package vadebici.repo;

import vadebici.db_in_memory.Database;
import vadebici.dom.Totem;
import vadebici.dom.Tranca;
import vadebici.dom.TrancaStatus;
import vadebici.dom.dto.PostTranca;
import vadebici.dom.dto.TrancaNaRede;
import vadebici.exception.GenericApiException;
import vadebici.util.Validacao;

import java.util.*;
import java.util.stream.Collectors;

public class TrancaRepo {
    public Tranca salvarTranca(PostTranca postTranca) {
        Validacao.validarCampos(postTranca);
        Tranca tranca = new Tranca();
        String id = UUID.randomUUID().toString();
        tranca.setId(id);
        tranca.setStatus(postTranca.getStatus());
        tranca.setLocalizacao(postTranca.getLocalizacao());
        tranca.setModelo(postTranca.getModelo());
        tranca.setAnoDeFabricacao(postTranca.getAnoDeFabricacao());
        tranca.setNumero(String.valueOf(postTranca.getNumero()));
        Database.TRANCA_TABLE.put(id, tranca);
        return tranca;
    }

    public List<Tranca> buscarTrancas() {
        return Database.TRANCA_TABLE.keySet()
                .stream()
                .map(Database.TRANCA_TABLE::get)
                .collect(Collectors.toList());
    }

    public Tranca buscarTranca(String id) {
        Tranca tranca = Database.TRANCA_TABLE.get(id);
        if (tranca == null) throw new GenericApiException(404, "Tranca não encontrado");
        return tranca;
    }

    public Tranca atualizarTranca(String id, Tranca novaTranca){
        Validacao.validarCampos(novaTranca);
        buscarTranca(id);
        Database.TRANCA_TABLE.replace(id, novaTranca);
        return novaTranca;
    }

    public void deletarTranca(String id){
        Tranca tranca = buscarTranca(id);
        if (tranca.getIdBicicleta() != null) throw new GenericApiException(422, "Tranca não pode ser removida poís possui uma bicicleta.");
        Database.TRANCA_TABLE.remove(id);
    }

    public Tranca alterarStatus(String id, String status){
        Tranca tranca = buscarTranca(id);
        tranca.setStatus(TrancaStatus.valueOf(status));
        return Database.TRANCA_TABLE.replace(tranca.getId(), tranca);
    }

    public void integrarNaRede(TrancaNaRede trancaNaRede) {
        Validacao.validarCampos(trancaNaRede);
        Tranca tranca = buscarTranca(trancaNaRede.getIdTranca());
        Totem totem = buscarTotem(trancaNaRede.getIdTotem());
        tranca.setIdTotem(totem.getId());
//        tranca.setStatus(TrancaStatus.DISPONIVEL);
        Database.TRANCA_TABLE.replace(tranca.getId(), tranca);
    }

    public void removerDaRede(TrancaNaRede trancaNaRede) {
        Validacao.validarCampos(trancaNaRede);
        Tranca tranca = buscarTranca(trancaNaRede.getIdTranca());
        if (tranca.getIdBicicleta() != null) throw new GenericApiException(422, "Tranca possui uma bicicleta.");
        buscarTotem(trancaNaRede.getIdTotem());
        tranca.setIdTotem(null);

    }

    private Totem buscarTotem(String id) {
        return Optional.ofNullable(Database.TOTEM_TABLE.get(id))
                .orElseThrow(() -> new GenericApiException(422, "Totem não encontrado."));
    }
}
