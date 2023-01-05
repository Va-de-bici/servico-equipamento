package vadebici.controller;

import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import vadebici.dom.Bicicleta;
import vadebici.dom.dto.PostBicicleta;
import vadebici.repo.BicicletaRepositorio;
import vadebici.util.Validacao;

import java.util.List;


public class BicicletaController {
    private static final String PATH_ID = "idTotem";

    public static BicicletaRepositorio bicicletaRepositorio = new BicicletaRepositorio();

    private BicicletaController(){}

    public static void salvarBicicleta(Context ctx) {
        String body = ctx.body();
        PostBicicleta postBicicleta = JavalinJson.getFromJsonMapper().map(body, PostBicicleta.class);
        Validacao.validarCampos(postBicicleta);
        Bicicleta bicicleta = bicicletaRepositorio.salvarBicicleta(postBicicleta);
        String response = JavalinJson.toJson(bicicleta);
        ctx.result(response);
        ctx.status(201);
    }

    public static void buscarBicicletas(Context ctx) {
        List<Bicicleta> bicicletaList = bicicletaRepositorio.buscarBicicletas();
        String response = JavalinJson.toJson(bicicletaList);
        ctx.result(response);
        ctx.status(200);
    }

    public static void buscarBicicleta(Context ctx) {
        String bicicletaId = ctx.pathParam(PATH_ID);
        Bicicleta bicicleta = bicicletaRepositorio.buscarBicicleta(bicicletaId);
        String response = JavalinJson.toJson(bicicleta);
        ctx.result(response);
        ctx.status(200);
    }

    public static void atualizarBicicleta(Context ctx) {
        String body = ctx.body();
        String id = ctx.pathParam(PATH_ID);
        Bicicleta bicicleta = JavalinJson.getFromJsonMapper().map(body, Bicicleta.class);
        Validacao.validarCampos(bicicleta);
        Bicicleta resultado = bicicletaRepositorio.atualizarBicicleta(id, bicicleta);
        ctx.status(200);
        ctx.result(JavalinJson.toJson(resultado));

    }

    public static void deletarBicicleta(Context ctx) {
        String id = ctx.pathParam(PATH_ID);
        bicicletaRepositorio.deletarBicicleta(id);
        ctx.status(200);
    }

    public static void alterarStatus(Context ctx) {
        String id = ctx.pathParam(PATH_ID);
        String status = ctx.pathParam("acao");
        Bicicleta bicicleta = bicicletaRepositorio.alterarStatus(id, status);
        String response = JavalinJson.getToJsonMapper().map(bicicleta);
        ctx.status(200);
        ctx.result(response);
    }
}
