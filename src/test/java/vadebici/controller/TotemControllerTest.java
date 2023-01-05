package vadebici.controller;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.*;
import vadebici.db_in_memory.Database;
import vadebici.dom.Totem;
import vadebici.dom.Tranca;
import vadebici.dom.dto.PostTotem;
import vadebici.model.TrancaModel;
import vadebici.util.JavalinApp;

import static org.junit.jupiter.api.Assertions.*;
import static vadebici.model.TotemModel.*;

class TotemControllerTest {

    private final static JavalinApp app = new JavalinApp();
    private static final String BASE_URL = "http://localhost:7010/totem";

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
    void clean() {
        Database.carregarDados();
    }

    @Test
    void salvaTotem_QuandoBemSucedido() {
        PostTotem postTotem = getPostTotem();
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL)
                .body(postTotem)
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();
        assertEquals(201, response.getStatus());
        assertEquals(postTotem.getLocalizacao(), jsonResponse.get("localizacao"));
        assertNotNull(jsonResponse.get("id"));
    }

    @Test
    void buscaTotens_QuandoBemSucedido() {
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL)
                .asJson();
        JSONArray jsonResponse = response.getBody().getArray();
        assertEquals(200, response.getStatus());
        assertNotNull(jsonResponse.get(0));
    }

    @Test
    void atualizaTotem_QuandoBemSucedido() {
        Totem totem = getTotem();
        totem.setLocalizacao("ALTERADO");
        HttpResponse<JsonNode> response = Unirest.put(BASE_URL+"/123")
                .body(totem)
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals(totem.getLocalizacao(), jsonResponse.get("localizacao"));
        assertEquals(totem.getId(), jsonResponse.get("id"));
    }

    @Test
    void deletaTotem_QuandoBemSucedido() {
        HttpResponse<JsonNode> response = Unirest.delete(BASE_URL+"/123")
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals("{}", jsonResponse.toString());
    }

    @Test
    void buscaTrancasPorTotem_QuandoBemSucedido() {
        Tranca tranca = TrancaModel.getTranca();
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL+"/123/trancas")
                .asJson();
        JSONArray jsonResponse = response.getBody().getArray();

        assertEquals(200, response.getStatus());
        assertEquals(tranca.getNumero(), jsonResponse.getJSONObject(0).get("numero"));
        assertEquals(tranca.getLocalizacao(), jsonResponse.getJSONObject(0).get("localizacao"));
        assertEquals(tranca.getAnoDeFabricacao(), jsonResponse.getJSONObject(0).get("anoDeFabricacao"));
        assertEquals(tranca.getStatus().toString(), jsonResponse.getJSONObject(0).get("status"));
        assertEquals(tranca.getModelo(), jsonResponse.getJSONObject(0).get("Database"));
        assertEquals(tranca.getId(), jsonResponse.getJSONObject(0).get("id"));
    }
}