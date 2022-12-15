package vadebici;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import vadebici.util.JavalinApp;

public class InicializacaoTest {

    private final JavalinApp javalinApp = new JavalinApp();

    @Test
    void inicializaServidor_QuandoBemSucedido() {
        javalinApp.start(9000);
        assertNotNull(javalinApp);
        javalinApp.stop();
    }
}
