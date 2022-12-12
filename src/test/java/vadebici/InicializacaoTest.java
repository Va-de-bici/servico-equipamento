package vadebici;

import org.junit.jupiter.api.Test;
import vadebici.util.JavalinApp;

public class InicializacaoTest {

    private final JavalinApp javalinApp = new JavalinApp();

    @Test
    void inicializaServidor_QuandoBemSucedido() {
        javalinApp.start(9000);
        javalinApp.stop();
    }
}
