package vadebici.controller;

import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import vadebici.dom.RequisicaoEmail;
import vadebici.service.ServicoExterno;

public class EmailController {

    private EmailController() {}

    private static final ServicoExterno servicoEmail = new ServicoExterno();

    public static void enviarEmail(Context ctx) {
        String body = ctx.body();
        RequisicaoEmail requisicaoEmail = JavalinJson.getFromJsonMapper().map(body, RequisicaoEmail.class);
        servicoEmail.enviarEmail(requisicaoEmail);
        ctx.status(200);

    }
}
