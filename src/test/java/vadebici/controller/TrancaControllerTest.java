package vadebici.controller;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.javalin.plugin.json.JavalinJson;
import vadebici.db_in_memory.Database;
import vadebici.dom.Tranca;
import vadebici.dom.TrancaStatus;
import vadebici.dom.dto.PostTranca;
import vadebici.util.JavalinApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static vadebici.model.TrancaModel.*;

class TrancaControllerTest {

    private final static JavalinApp app = new JavalinApp();
    private static final String BASE_URL = "http://localhost:7010/tranca";

    @BeforeAll
    static void init() {
        app.start(7010);
    }

    @AfterAll
    static void stop() {
        app.stop();
        Database.limpar();
    }

    @BeforeEach
    void setUp() {
        Database.carregarDados();
    }

    @Test
    void salvaTranca_QuandoBemSucedido() {
        PostTranca postTranca = getPostTranca();
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL)
                .body(JavalinJson.toJson(postTranca))
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(201, response.getStatus());
        assertEquals(String.valueOf(postTranca.getNumero()), jsonResponse.get("numero"));
        assertEquals(postTranca.getLocalizacao(), jsonResponse.get("localizacao"));
        assertEquals(postTranca.getAnoDeFabricacao(), jsonResponse.get("anoDeFabricacao"));
        assertEquals(postTranca.getStatus().toString(), jsonResponse.get("status"));
        assertEquals(postTranca.getModelo(), jsonResponse.get("modelo"));
        assertNotNull(jsonResponse.get("id"));
    }

    @Test
    void buscarTrancas() {
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL)
                .asJson();

        assertEquals(200, response.getStatus());
        assertNotNull(response.getBody().getArray().get(0));
    }

    @Test
    void buscarTranca() {
        Tranca tranca = getTranca();
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL+"/123")
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals(tranca.getNumero(), jsonResponse.get("numero"));
        assertEquals(tranca.getLocalizacao(), jsonResponse.get("localizacao"));
        assertEquals(tranca.getAnoDeFabricacao(), jsonResponse.get("anoDeFabricacao"));
        assertEquals(tranca.getStatus().toString(), jsonResponse.get("status"));
        assertEquals(tranca.getModelo(), jsonResponse.get("modelo"));
        assertEquals(tranca.getId(), jsonResponse.get("id"));
    }

    @Test
    void atualizaTranca_QuandoBemSucedido() {
        Tranca tranca = getTranca();
        tranca.setLocalizacao("ALTERADO");
        HttpResponse<JsonNode> response = Unirest.put(BASE_URL+"/123")
                .body(JavalinJson.toJson(tranca))
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals(tranca.getNumero(), jsonResponse.get("numero"));
        assertEquals(tranca.getLocalizacao(), jsonResponse.get("localizacao"));
        assertEquals(tranca.getAnoDeFabricacao(), jsonResponse.get("anoDeFabricacao"));
        assertEquals(tranca.getStatus().toString(), jsonResponse.get("status"));
        assertEquals(tranca.getModelo(), jsonResponse.get("modelo"));
        assertEquals(tranca.getId(), jsonResponse.get("id"));
    }

    @Test
    void alterarStatus() {
        Tranca tranca = getTranca();
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL+"/123/status/MANUTENCAO")
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals(tranca.getNumero(), jsonResponse.get("numero"));
        assertEquals(tranca.getLocalizacao(), jsonResponse.get("localizacao"));
        assertEquals(tranca.getAnoDeFabricacao(), jsonResponse.get("anoDeFabricacao"));
        assertEquals(TrancaStatus.MANUTENCAO.toString(), jsonResponse.get("status"));
        assertEquals(tranca.getModelo(), jsonResponse.get("modelo"));
        assertEquals(tranca.getId(), jsonResponse.get("id"));
    }
}