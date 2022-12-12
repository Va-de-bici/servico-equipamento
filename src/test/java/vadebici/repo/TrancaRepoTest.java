package vadebici.repo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vadebici.db_in_memory.Database;
import vadebici.dom.Tranca;
import vadebici.dom.TrancaStatus;
import vadebici.dom.dto.PostTranca;
import vadebici.dom.dto.TrancaNaRede;
import vadebici.exception.GenericApiException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static vadebici.model.TrancaModel.*;

public class TrancaRepoTest {

    private final TrancaRepo trancaRepositorio = new TrancaRepo();
    @BeforeEach
    void setUp() {
        Database.carregarDados();
    }

    @AfterAll
    static void afterAll() {
        Database.limpar();
    }

    @Test
    void salvaTranca_QuandoBemSucedido() {
        PostTranca postTranca = getPostTranca();
        Tranca trancaResposta = trancaRepositorio.salvarTranca(postTranca);
        Tranca trancaSalva = trancaRepositorio.buscarTranca(trancaResposta.getId());

        assertEquals(trancaSalva, trancaResposta);
        assertEquals(postTranca.getAnoDeFabricacao(), trancaResposta.getAnoDeFabricacao());
        assertEquals(postTranca.getLocalizacao(), trancaResposta.getLocalizacao());
        assertEquals(postTranca.getStatus(), trancaResposta.getStatus());
        assertEquals(postTranca.getModelo(), trancaResposta.getModelo());
        assertEquals(String.valueOf(postTranca.getNumero()), trancaResposta.getNumero());
    }

    @Test
    void buscaTrancas_QuandoBemSucedido() {
        List<Tranca> trancaList = trancaRepositorio.buscarTrancas();

        assertNotNull(trancaList);
        assertEquals(Database.TRANCA_TABLE.keySet().size(), trancaList.size());
    }

    @Test
    void buscaTranca_QuandoBemSucedido() {
        Tranca tranca = getTranca();
        Tranca trancaResposta = trancaRepositorio.buscarTranca(tranca.getId());

        assertEquals(tranca, trancaResposta);
    }

    @Test
    void buscarTranca_LancaExcecao_QuandoTrancaNaoEncontrado() {
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> trancaRepositorio.buscarTranca("000"));
        assertEquals(404,  ex.getCodigo());
        assertEquals("Tranca não encontrado", ex.getMessage());
        assertNotNull(ex.getId());
    }

    @Test
    void atualizaTranca_QuandoBemSucedido() {
        Tranca tranca = getTranca();
        tranca.setModelo("ALTERADO");
        trancaRepositorio.atualizarTranca(tranca.getId(), tranca);
        Tranca trancaResposta = trancaRepositorio.buscarTranca(tranca.getId());

        assertEquals(tranca, trancaResposta);
    }

    @Test
    void atualizarTranca_LancaExcecao_QuandoNaoEncontrado() {
        assertThrows(GenericApiException.class,
                () -> trancaRepositorio.atualizarTranca("000", new Tranca()));
    }

    // @Test
    // void deletaTranca_QuandoBemSucedido() {
    //     Tranca tranca = getTranca();
    //     trancaRepositorio.deletarTranca(tranca.getId());
    //     assertThrows(GenericApiException.class,
    //             () -> trancaRepositorio.buscarTranca(tranca.getId()));
    // }

    @Test
    void deletaTranca_LancaExcecao_QuandoTrancaNaRede() {
        Tranca tranca = getTranca();
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> trancaRepositorio.deletarTranca(tranca.getId()));

        assertEquals("Tranca não pode ser removida poís possui uma bicicleta.", ex.getMensagem());
        assertEquals(422, ex.getCodigo());
        assertNotNull(ex.getId());
    }

    @Test
    void deletarTranca_LancaExcecao_QuandoTrancaNaoEncotrado() {
        assertThrows(GenericApiException.class,
                () -> trancaRepositorio.deletarTranca("000"));
    }

    @Test
    void alteraStatus_QuandoBemSucedido() {
        Tranca tranca = getTranca();
        trancaRepositorio.alterarStatus(tranca.getId(), "OCUPADA");
        Tranca trancaOcupada = trancaRepositorio.buscarTranca(tranca.getId());

        assertEquals(TrancaStatus.OCUPADA, trancaOcupada.getStatus());
    }

    @Test
    void intergraNaRede_QuandoBemSucedido() {
        TrancaNaRede trancaNaRede = getTrancaNaRede();
        trancaRepositorio.integrarNaRede(trancaNaRede);
        Tranca trancaResposta = trancaRepositorio.buscarTranca(trancaNaRede.getIdTranca());

        assertNotNull(trancaResposta.getIdTotem());
    }

    @Test
    void intergraNaRede_LancaExcecao_QuandoTotemNaoExiste() {
        TrancaNaRede trancaNaRede = getTrancaNaRede();
        trancaNaRede.setIdTotem("000");
        assertThrows(GenericApiException.class, () -> trancaRepositorio.integrarNaRede(trancaNaRede));
    }

    // @Test
    // void removeDaRede_QuandoBemSucedido() {
    //     TrancaNaRede trancaNaRede = getTrancaNaRede();
    //     trancaRepositorio.removerDaRede(trancaNaRede);
    //     Tranca trancaResposta = trancaRepositorio.buscarTranca(trancaNaRede.getIdTranca());

    //     assertNull(trancaResposta.getIdTotem());
    // }

    @Test
    void removerDaRede_LancaExcecao_QuandoTrancaOcupada() {
        TrancaNaRede trancaNaRede = getTrancaNaRede();

        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> trancaRepositorio.removerDaRede(trancaNaRede));


        assertEquals("Tranca possui uma bicicleta.", ex.getMensagem());
        assertEquals(422, ex.getCodigo());
    }
}
