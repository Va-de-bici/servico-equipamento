package vadebici.controller;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vadebici.dom.Email;
import vadebici.util.JavalinApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailControllerTest {
    private final static JavalinApp app = new JavalinApp();
    private static final String BASE_URL = "http://va-de-bici.herokuapp.com/enviaEmail";

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
        Email requisicaoEmail = new Email();
        requisicaoEmail.setEmail("erick.m.val@gmail.com");
        requisicaoEmail.setMensagem("321");
        HttpResponse<String> response = Unirest.post(BASE_URL)
                .body(requisicaoEmail)
                .asString();

        assertEquals(200, response.getStatus());
        assertEquals("", response.getBody());
    }
}
