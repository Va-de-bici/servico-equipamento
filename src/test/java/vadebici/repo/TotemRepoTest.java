package vadebici.repo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vadebici.db_in_memory.Database;
import vadebici.dom.Totem;
import vadebici.dom.Tranca;
import vadebici.dom.dto.PostTotem;
import vadebici.exception.GenericApiException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static vadebici.model.TotemModel.getPostTotem;
import static vadebici.model.TotemModel.getTotem;
import static vadebici.model.TrancaModel.getTranca;

public class TotemRepoTest {

    private final TotemRepo totemRepositorio = new TotemRepo();

    @BeforeEach
    void setUp() {
        Database.carregarDados();
    }

    @AfterAll
    static void afterAll() {
        Database.limpar();
    }

    @Test
    void salvaTotem_QuandoBemSucedido() {
        PostTotem postTotem = getPostTotem();
        Totem totemSalvo = totemRepositorio.salvarTotem(postTotem);
        assertNotNull(totemSalvo.getId());
        assertEquals(postTotem.getLocalizacao(), totemSalvo.getLocalizacao());
    }

    @Test
    void buscaTotens_QuandoBemSucedido() {
        List<Totem> totemList = totemRepositorio.buscarTotens();
        assertNotNull(totemList);
        assertEquals(1, totemList.size());
    }

    @Test
    void buscaTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        Totem totemResposta = totemRepositorio.buscarTotem(totem.getId());

        assertEquals(totem.getLocalizacao(), totemResposta.getLocalizacao());
        assertEquals(totem.getId(), totemResposta.getId());
    }

    @Test
    void buscarTotem_LancaExcecao_QuandoNaoEncontrado() {
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> totemRepositorio.buscarTotem("000"));
        assertEquals("Totem não encontrado", ex.getMessage());
    }

    @Test
    void atualizaTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        Totem totemResposta = totemRepositorio.atualizarTotem(totem.getId(), totem);
        assertEquals(totem.getLocalizacao(), totemResposta.getLocalizacao());
        assertEquals(totem.getId(), totemResposta.getId());    }

    @Test
    void atualizarTotem_LancaExecao_QuandoNaoEncontrado() {
        assertThrows(GenericApiException.class,
                () -> totemRepositorio.atualizarTotem("000", new Totem()));
    }

    @Test
    void deletaTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        totemRepositorio.deletarTotem(totem.getId());
        assertThrows(GenericApiException.class,
                () -> totemRepositorio.buscarTotem(totem.getId()));
    }

    @Test
    void deletarTotem_LancaExecao_QuandoTotemNaoEncotrado() {
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> totemRepositorio.deletarTotem("000"));
        assertEquals("Totem não encontrado", ex.getMessage());
    }

    @Test
    void buscaTrancasPorTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        Tranca tranca = getTranca();
        List<Tranca> trancaList = totemRepositorio.buscarTrancas(totem.getId());

        assertEquals(1, trancaList.size());
        assertEquals(tranca, trancaList.get(0));
    }




}
