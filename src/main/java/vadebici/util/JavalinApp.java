package vadebici.util;

import io.javalin.plugin.json.JavalinJson;
import vadebici.controller.TotemController;
import vadebici.controller.TrancaController;
import io.javalin.Javalin;
import vadebici.exception.GenericApiException;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApp {
    private static String PATH_TRANCA = "/tranca/:idTranca";

    private final Javalin app =
            Javalin.create(config -> config.defaultContentType = "application/json")
                    .routes(() -> {
                        path("/totem", () -> post(TotemController::salvarTotem));
                        path("/totem", () -> get(TotemController::buscarTotens));
                        path("/totem/:idTotem", () -> delete(TotemController::deletarTotem));
                        path("/totem/:idTotem", () -> put(TotemController::atualizarTotem));
                        path("/totem/:idTotem/trancas", () -> get(TotemController::buscarTrancasPorTotem));

                        path("/tranca", () -> get(TrancaController::buscarTrancas));
                        path("/tranca", () -> post(TrancaController::salvarTranca));
                        path(PATH_TRANCA, () -> get(TrancaController::buscarTranca));
                        path(PATH_TRANCA, () -> delete(TrancaController::deletarTranca));
                        path(PATH_TRANCA, () -> put(TrancaController::atualizarTranca));
                        path(PATH_TRANCA + "/status/:acao", () -> post(TrancaController::alterarStatus));
                    })
                    .error(500, ctx -> {
                        GenericApiException ex = new GenericApiException(500, "Ocorreu um erro durante a operação");
                        ctx.result(JavalinJson.toJson(ex));
                    }).error(404, ctx -> {
                        if (ctx.attribute("handle") == null) {
                            GenericApiException ex = new GenericApiException(404, "Página não encontrada");
                            ctx.result(JavalinJson.toJson(ex));
                        }
                    })
                    .exception(GenericApiException.class, (e, ctx) -> {
                        ctx.attribute("handle", true);
                        String response = JavalinJson.getToJsonMapper().map(e);
                        ctx.status(e.getCodigo());
                        ctx.result(response);
                    });



    public void start(int port) {
        this.app.start(port);
    }

    public void stop() {
        this.app.stop();
    }
}
