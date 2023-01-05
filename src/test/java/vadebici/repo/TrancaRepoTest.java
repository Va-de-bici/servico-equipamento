package vadebici.repo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vadebici.db_in_memory.Database;
import vadebici.dom.Tranca;
import vadebici.dom.TrancaStatus;
import vadebici.dom.dto.PostTranca;
import vadebici.exception.GenericApiException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static vadebici.model.TrancaModel.*;

public class TrancaRepoTest {

    private final TrancaRepositorio trancaRepositorio = new TrancaRepositorio();
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

        assertNotNull(tranca);
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
        Tranca tranca = new Tranca();
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> trancaRepositorio.atualizarTranca("000", tranca));
        assertEquals("Tranca não encontrado", ex.getMessage());
    }

    @Test
    void deletaTranca_QuandoBemSucedido() {
        trancaRepositorio.deletarTranca("123");
        assertThrows(GenericApiException.class,
                () -> trancaRepositorio.deletarTranca("321"));
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
}
