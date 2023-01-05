package vadebici.controller;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vadebici.db_in_memory.Database;
import vadebici.dom.Bicicleta;
import vadebici.dom.BicicletaStatus;
import vadebici.dom.dto.PostBicicleta;
import vadebici.util.JavalinApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static vadebici.model.BicicletaModel.*;

class BicicletaControllerTest {

    private final static JavalinApp app = new JavalinApp();
    private static final String BASE_URL = "http://localhost:7010/bicicleta";

    @BeforeAll
    static void init() {
        app.start(7010);
    }

    @AfterAll
    static void stop() {
        app.stop();
    }

    @BeforeEach
    void clean() {
        Database.carregarDados();
    }

    @AfterAll
    static void afterAll() {
        Database.limpar();
    }

    @Test
    void salvaBicicleta_QuandoBemSucedido() {
        PostBicicleta postBicicleta = getPostBicicleta();
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL)
                .body(postBicicleta)
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(201, response.getStatus());
        assertEquals(postBicicleta.getNumero(), jsonResponse.get("numero"));
        assertEquals(postBicicleta.getStatus().toString(), jsonResponse.get("status"));
        assertEquals(postBicicleta.getAno(), jsonResponse.get("ano"));
        assertEquals(postBicicleta.getModelo(), jsonResponse.get("Database"));
        assertNotNull(jsonResponse.get("id"));
    }

    @Test
    void buscarBicicletas() {
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL)
                .asJson();

        assertEquals(200, response.getStatus());
        assertNotNull(response.getBody().getArray().get(0));
    }

    @Test
    void buscarBicicleta() {
        Bicicleta bicicleta = getBicicleta();
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL+"/123")
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals(bicicleta.getNumero(), jsonResponse.get("numero"));
        assertEquals(bicicleta.getStatus().toString(), jsonResponse.get("status"));
        assertEquals(bicicleta.getAno(), jsonResponse.get("ano"));
        assertEquals(bicicleta.getModelo(), jsonResponse.get("Database"));
        assertEquals(bicicleta.getId(), jsonResponse.get("id"));
    }

    @Test
    void atualizaBicicleta_QuandoBemSucedido() {
        Bicicleta bicicleta = getBicicleta();
        bicicleta.setMarca("ALTERADO");

        HttpResponse<JsonNode> response = Unirest.put(BASE_URL+"/123")
                .body(bicicleta)
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals(bicicleta.getNumero(), jsonResponse.get("numero"));
        assertEquals(bicicleta.getStatus().toString(), jsonResponse.get("status"));
        assertEquals(bicicleta.getAno(), jsonResponse.get("ano"));
        assertEquals(bicicleta.getModelo(), jsonResponse.get("Database"));
        assertEquals(bicicleta.getId(), jsonResponse.get("id"));
    }

    @Test
    void alterarStatus() {
        Bicicleta bicicleta = getBicicleta();
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL+"/123/status/APOSENTADA")
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals(bicicleta.getNumero(), jsonResponse.get("numero"));
        assertEquals(bicicleta.getAno(), jsonResponse.get("ano"));
        assertEquals(bicicleta.getModelo(), jsonResponse.get("Database"));
        assertEquals(bicicleta.getId(), jsonResponse.get("id"));
        assertEquals(BicicletaStatus.APOSENTADA.toString(), jsonResponse.get("status"));

    }
}