package vadebici.controller;

import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import vadebici.dom.RequisicaoEmail;
import vadebici.service.ServicoEmail;

public class EmailController {

    private EmailController() {}

    private static final ServicoEmail servicoEmail = new ServicoEmail();

    public static void enviarEmail(Context ctx) {
        String body = ctx.body();
        RequisicaoEmail requisicaoEmail = JavalinJson.getFromJsonMapper().map(body, RequisicaoEmail.class);
        servicoEmail.enviarMensagem(requisicaoEmail);
        ctx.status(200);

    }
}
