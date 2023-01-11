package vadebici.repo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vadebici.db_in_memory.Database;
import vadebici.dom.Bicicleta;
import vadebici.dom.Tranca;
import vadebici.dom.BicicletaStatus;
import vadebici.dom.dto.PostBicicleta;
import vadebici.dom.dto.BicicletaNaRede;
import vadebici.exception.GenericApiException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static vadebici.model.BicicletaModel.*;

public class BicicletaRepositorioTest {
    private final BicicletaRepositorio bicicletaRepositorio = new BicicletaRepositorio();
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
    void salvaBicicleta_QuandoBemSucedido() {
        PostBicicleta postBicicleta = getPostBicicleta();
        Bicicleta bicicletaResposta = bicicletaRepositorio.salvarBicicleta(postBicicleta);
        Bicicleta bicicletaSalva = bicicletaRepositorio.buscarBicicleta(bicicletaResposta.getId());

        assertEquals(bicicletaSalva, bicicletaResposta);
        assertEquals(postBicicleta.getModelo(), bicicletaResposta.getModelo());
        assertEquals(postBicicleta.getAno(), bicicletaResposta.getAno());
        assertEquals(postBicicleta.getNumero(), bicicletaResposta.getNumero());
        assertEquals(postBicicleta.getMarca(), bicicletaResposta.getMarca());
        assertEquals(postBicicleta.getStatus(), bicicletaResposta.getStatus());
    }

    @Test
    void buscaBicicletas_QuandoBemSucedido() {
        List<Bicicleta> bicicletaListResposta = bicicletaRepositorio.buscarBicicletas();

        assertNotNull(bicicletaListResposta);
        assertEquals(1, bicicletaListResposta.size());
    }

    @Test
    void buscaBicicleta_QuandoBemSucedido() {
        Bicicleta bicicleta = getBicicleta();
        Bicicleta bicicletaResposta = bicicletaRepositorio.buscarBicicleta(bicicleta.getId());

        assertEquals(bicicleta, bicicletaResposta);
    }

    @Test
    void buscarBicicleta_LancaExcecao_QuandoNaoEncontrado() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> bicicletaRepositorio.buscarBicicleta("000"));

        assertEquals("Bicicleta não encontrado", ex.getMessage());
    }

    @Test
    void atualizaBicicleta_QuandoBemSucedido() {
        Bicicleta bicicleta = getBicicleta();
        bicicleta.setMarca("ALTERADO");
        Bicicleta bicicletaResposta = bicicletaRepositorio.atualizarBicicleta(bicicleta.getId(), bicicleta);

        assertEquals(bicicleta, bicicletaResposta);
    }

    @Test
    void atualizarBicicleta_LancaExcecao_QuandoNaoEncontrado() {
        Bicicleta bicicleta = new Bicicleta();
        assertThrows(RuntimeException.class,
                () -> bicicletaRepositorio.atualizarBicicleta("000", bicicleta));
    }

    @Test
    void alteraStatus_QuandoBemSucedido() {
        Bicicleta bicicleta = getBicicleta();
        Bicicleta respostaBicicleta = bicicletaRepositorio.alterarStatus(bicicleta.getId(), "APOSENTADA");
        assertEquals(BicicletaStatus.APOSENTADA, respostaBicicleta.getStatus());
    }

    @Test
    void deletarBicicleta_LancaExcecao_QuandoNaoEncotrado() {
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> bicicletaRepositorio.deletarBicicleta("000"));
        assertEquals("Bicicleta não encontrado", ex.getMessage());
    }

    @Test
    void removeBicicletaDaRede_QuandoBemSucedido() {
        BicicletaNaRede bicicletaNaRede = getBicicletaNaRede();
        bicicletaRepositorio.removerDaRede(bicicletaNaRede);
        Tranca tranca = trancaRepositorio.buscarTranca(bicicletaNaRede.getIdTranca());

        assertNull(tranca.getIdBicicleta());
    }

    @Test
    void intergraBicicletaNaRede_QuandoBemSucedido() {
        Bicicleta bicicleta = getBicicleta();
        BicicletaNaRede bicicletaNaRede = getBicicletaNaRede();
        bicicletaRepositorio.removerDaRede(bicicletaNaRede);
        bicicletaRepositorio.integrarNaRede(bicicletaNaRede);
        Bicicleta bicicletaResposta = bicicletaRepositorio.buscarBicicleta(bicicleta.getId());

        assertEquals(BicicletaStatus.DISPONIVEL, bicicletaResposta.getStatus());
    }

    @Test
    void deletaBicicleta_QuandoBemSucedido() {
        Bicicleta bicicleta = getBicicleta();
        BicicletaNaRede bicicletaNaRede = getBicicletaNaRede();
        bicicletaRepositorio.removerDaRede(bicicletaNaRede);
        bicicletaRepositorio.alterarStatus(bicicletaNaRede.getIdBicicleta(), "APOSENTADA");
        bicicletaRepositorio.deletarBicicleta(bicicleta.getId());

        assertThrows(GenericApiException.class,
                () -> bicicletaRepositorio.buscarBicicleta(bicicleta.getId()));
    }

    @Test
    void deletarBicicleta_LancaExcecao_QuandoBicicletaNaRede() {
        Bicicleta bicicleta = getBicicleta();
        GenericApiException ex = assertThrows(GenericApiException.class,
                () -> bicicletaRepositorio.deletarBicicleta(bicicleta.getId()));
        assertEquals("Não é possível deletar uma bicicleta na rede", ex.getMessage());
    }
}
