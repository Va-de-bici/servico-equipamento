package vadebici.controller;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vadebici.dom.RequisicaoEmail;
import vadebici.util.JavalinApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailControllerTest {
    private final static JavalinApp app = new JavalinApp();
    private static final String BASE_URL = "http://localhost:7010";

    @BeforeAll
    static void init() {
        app.start(7010);
    }

    @AfterAll
    static void stop() {
        app.stop();
    }

    @Test
    void enviaEmail_QuandoBemSucedido() {
        RequisicaoEmail requisicaoEmail = new RequisicaoEmail();
        requisicaoEmail.setEndereco("erick.m.val@edu.unirio.br");
        requisicaoEmail.setMensagem("teste email");
        HttpResponse<String> response = Unirest.post(BASE_URL+"/enviarEmail")
                .body(requisicaoEmail)
                .asString();

        assertEquals(200, response.getStatus());
        assertEquals("", response.getBody());
    }
}
