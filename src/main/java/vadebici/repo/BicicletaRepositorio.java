package vadebici.repo;

import vadebici.db_in_memory.Database;
import vadebici.dom.Bicicleta;
import vadebici.dom.BicicletaStatus;
import vadebici.dom.dto.PostBicicleta;
import vadebici.dom.dto.BicicletaNaRede;
import vadebici.dom.Tranca;
import vadebici.dom.TrancaStatus;
import vadebici.exception.GenericApiException;
import vadebici.util.Validacao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class BicicletaRepositorio {
    public Bicicleta salvarBicicleta(PostBicicleta postBicicleta) {
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(UUID.randomUUID().toString());
        bicicleta.setStatus(postBicicleta.getStatus());
        bicicleta.setModelo(postBicicleta.getModelo());
        bicicleta.setAno(postBicicleta.getAno());
        bicicleta.setNumero(postBicicleta.getNumero());
        bicicleta.setMarca(postBicicleta.getMarca());
        Database.BICICLETA_TABLE.put(bicicleta.getId(), bicicleta);

        return bicicleta;
    }

    public List<Bicicleta> buscarBicicletas() {
        return Database.BICICLETA_TABLE.keySet()
                .stream()
                .map(Database.BICICLETA_TABLE::get)
                .collect(Collectors.toList());
    }

    public Bicicleta buscarBicicleta(String id) {
        Bicicleta bicicleta = Database.BICICLETA_TABLE.get(id);
        if (bicicleta == null) throw new GenericApiException(404, "Bicicleta não encontrado");
        return bicicleta;
    }

    public Bicicleta atualizarBicicleta(String id, Bicicleta novaBicicleta){
        buscarBicicleta(id);
        Database.BICICLETA_TABLE.replace(id, novaBicicleta);
        return novaBicicleta;
    }

    public void deletarBicicleta(String id){
        Bicicleta bicicleta = buscarBicicleta(id);

        if (bicicleta.getStatus() != BicicletaStatus.DISPONIVEL) Database.BICICLETA_TABLE.remove(id);
        else throw new GenericApiException(422, "Não é possível deletar uma bicicleta na rede");
    }

    public Bicicleta alterarStatus(String id, String status){
        Bicicleta bicicleta = buscarBicicleta(id);
        bicicleta.setStatus(BicicletaStatus.valueOf(status));
        Database.BICICLETA_TABLE.replace(id, bicicleta);
        return bicicleta;
    }

    private Tranca buscarTranca(String id) {
        return Optional.of(Database.TRANCA_TABLE.get(id))
                .orElseThrow(() -> new GenericApiException(404, "Tranca não encontrado."));
    }

    public void integrarNaRede(BicicletaNaRede bicicletaNaRede) {
        Validacao.validarCampos(bicicletaNaRede);
        Bicicleta bicicleta = buscarBicicleta(bicicletaNaRede.getIdBicicleta());
        Tranca tranca = buscarTranca(bicicletaNaRede.getIdTranca());
        if (tranca.getIdBicicleta() != null) throw new GenericApiException(422, "Tranca ocupada.");
        tranca.setIdBicicleta(bicicleta.getId());
        tranca.setStatus(TrancaStatus.OCUPADA);
        bicicleta.setStatus(BicicletaStatus.DISPONIVEL);
        Database.BICICLETA_TABLE.replace(bicicleta.getId(), bicicleta);
        Database.TRANCA_TABLE.replace(tranca.getId(), tranca);
    }

    public void removerDaRede(BicicletaNaRede bicicletaNaRede) {
        Validacao.validarCampos(bicicletaNaRede);
        Bicicleta bicicleta = buscarBicicleta(bicicletaNaRede.getIdBicicleta());
        Tranca tranca = buscarTranca(bicicletaNaRede.getIdTranca());
        if (tranca.getIdBicicleta() == null) throw new GenericApiException(422, "Tranca sem bicicleta.");
        tranca.setIdBicicleta(null);
        Database.BICICLETA_TABLE.replace(bicicleta.getId(), bicicleta);
        Database.TRANCA_TABLE.replace(tranca.getId(), tranca);
    }
}
