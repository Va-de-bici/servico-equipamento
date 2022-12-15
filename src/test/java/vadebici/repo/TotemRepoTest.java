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

    private final TotemRepo TOTEM_REPO = new TotemRepo();

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
        Totem totemSalvo = TOTEM_REPO.salvarTotem(postTotem);
        assertNotNull(totemSalvo.getId());
        assertEquals(postTotem.getLocalizacao(), totemSalvo.getLocalizacao());
    }

    @Test
    void buscaTotens_QuandoBemSucedido() {
        List<Totem> totemList = TOTEM_REPO.buscarTotens();
        assertNotNull(totemList);
        assertEquals(1, totemList.size());
    }

    @Test
    void buscaTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        Totem totemResposta = TOTEM_REPO.buscarTotem(totem.getId());

        assertEquals(totem.getLocalizacao(), totemResposta.getLocalizacao());
        assertEquals(totem.getId(), totemResposta.getId());
    }

    @Test
    void buscarTotem_LancaExcecao_QuandoNaoEncontrado() {
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> TOTEM_REPO.buscarTotem("000"));
        assertEquals("Totem não encontrado", ex.getMessage());
    }

    @Test
    void atualizaTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        Totem totemResposta = TOTEM_REPO.atualizarTotem(totem.getId(), totem);
        assertEquals(totem.getLocalizacao(), totemResposta.getLocalizacao());
        assertEquals(totem.getId(), totemResposta.getId());    }

    @Test
    void atualizarTotem_LancaExecao_QuandoNaoEncontrado() {
        Totem totem = new Totem();
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> TOTEM_REPO.atualizarTotem("000", totem));
        assertEquals("Totem não encontrado", ex.getMessage());
    }

    @Test
    void deletaTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        String id = totem.getId();
        TOTEM_REPO.deletarTotem(totem.getId());
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> TOTEM_REPO.buscarTotem(id));
        assertEquals("Totem não encontrado", ex.getMessage());
    }

    @Test
    void deletarTotem_LancaExecao_QuandoTotemNaoEncotrado() {
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> TOTEM_REPO.deletarTotem("000"));
        assertEquals("Totem não encontrado", ex.getMessage());
    }

    @Test
    void buscaTrancasPorTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        Tranca tranca = getTranca();
        List<Tranca> trancaList = TOTEM_REPO.buscarTrancas(totem.getId());

        assertEquals(1, trancaList.size());
        assertEquals(tranca.getId(), trancaList.get(0).getId());
    }
}
