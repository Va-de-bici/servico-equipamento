package vadebici.controller;

import vadebici.dom.Totem;
import vadebici.dom.Tranca;
import vadebici.dom.dto.PostTotem;
import vadebici.repo.TotemRepo;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;

import java.util.List;

public class TotemController {

    public static TotemRepo totemRepositorio = new TotemRepo();

    private TotemController(){}

    public static void salvarTotem(Context ctx) {
        String body = ctx.body();
        PostTotem postTotem = JavalinJson.getFromJsonMapper().map(body, PostTotem.class);
        Totem totem = totemRepositorio.salvarTotem(postTotem);
        String response = JavalinJson.toJson(totem);
        ctx.result(response);
        ctx.status(201);
    }

    public static void buscarTotens(Context ctx) {
        List<Totem> totemList = totemRepositorio.buscarTotens();
        String response = JavalinJson.toJson(totemList);
        ctx.result(response);
        ctx.status(200);
    }

    public static void atualizarTotem(Context ctx) {
        String body = ctx.body();
        String id = ctx.pathParam("idTotem");
        Totem totem = JavalinJson.getFromJsonMapper().map(body, Totem.class);
        Totem response = totemRepositorio.atualizarTotem(id, totem);
        ctx.status(200);
        ctx.result(JavalinJson.toJson(response));
    }

    public static void deletarTotem(Context ctx) {
        String id = ctx.pathParam("idTotem");
        totemRepositorio.deletarTotem(id);
        ctx.status(200);
    }

    public static void buscarTrancasPorTotem(Context ctx) {
        String id = ctx.pathParam("idTotem");
        List<Tranca> trancaList = totemRepositorio.buscarTrancas(id);
        String response = JavalinJson.toJson(trancaList);
        ctx.status(200);
        ctx.result(response);
    }
}
