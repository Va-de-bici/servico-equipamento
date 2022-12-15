package vadebici.controller;

import vadebici.dom.Totem;
import vadebici.dom.Tranca;
import vadebici.dom.dto.PostTotem;
import vadebici.repo.TotemRepo;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;

import java.util.List;

public class TotemController {

    public static final TotemRepo TOTEM_REPO = new TotemRepo();
    private static final String PATH_ID = "idTotem";

    private TotemController(){}

    public static void salvarTotem(Context ctx) {
        String body = ctx.body();
        PostTotem postTotem = JavalinJson.getFromJsonMapper().map(body, PostTotem.class);
        Totem totem = TOTEM_REPO.salvarTotem(postTotem);
        String response = JavalinJson.toJson(totem);
        ctx.result(response);
        ctx.status(201);
    }

    public static void buscarTotens(Context ctx) {
        List<Totem> totemList = TOTEM_REPO.buscarTotens();
        String response = JavalinJson.toJson(totemList);
        ctx.result(response);
        ctx.status(200);
    }

    public static void atualizarTotem(Context ctx) {
        String body = ctx.body();
        String id = ctx.pathParam(PATH_ID);
        Totem totem = JavalinJson.getFromJsonMapper().map(body, Totem.class);
        Totem response = TOTEM_REPO.atualizarTotem(id, totem);
        ctx.status(200);
        ctx.result(JavalinJson.toJson(response));
    }

    public static void deletarTotem(Context ctx) {
        String id = ctx.pathParam(PATH_ID);
        TOTEM_REPO.deletarTotem(id);
        ctx.status(200);
    }

    public static void buscarTrancasPorTotem(Context ctx) {
        String id = ctx.pathParam(PATH_ID);
        List<Tranca> trancaList = TOTEM_REPO.buscarTrancas(id);
        String response = JavalinJson.toJson(trancaList);
        ctx.status(200);
        ctx.result(response);
    }
}
