package vadebici.controller;

import vadebici.dom.Tranca;
import vadebici.dom.Bicicleta;
import vadebici.dom.dto.PostTranca;
import vadebici.dom.dto.TrancaNaRede;
import vadebici.repo.TrancaRepositorio;
import vadebici.util.Validacao;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;

import java.util.List;


public class TrancaController {

    private TrancaController(){}
    public static final TrancaRepositorio trancaRepositorio = new TrancaRepositorio();
    private static final String TRANCA_ID = "idTranca";

    public static void salvarTranca(Context ctx) {
        String body = ctx.body();
        PostTranca postTranca = JavalinJson.getFromJsonMapper().map(body, PostTranca.class);
        Validacao.validarCampos(postTranca);
        Tranca tranca = trancaRepositorio.salvarTranca(postTranca);
        String response = JavalinJson.toJson(tranca);
        ctx.result(response);
        ctx.status(201);
    }

    public static void buscarTrancas(Context ctx) {
        List<Tranca> trancaList = trancaRepositorio.buscarTrancas();
        String response = JavalinJson.toJson(trancaList);
        ctx.result(response);
        ctx.status(200);
    }

    public static void buscarTranca(Context ctx) {
        String trancaId = ctx.pathParam(TRANCA_ID);
        Tranca tranca = trancaRepositorio.buscarTranca(trancaId);
        String response = JavalinJson.toJson(tranca);
        ctx.result(response);
        ctx.status(200);
    }

    public static void atualizarTranca(Context ctx) {
        String body = ctx.body();
        String id = ctx.pathParam(TRANCA_ID);
        Tranca tranca = JavalinJson.getFromJsonMapper().map(body, Tranca.class);
        Tranca resultado = trancaRepositorio.atualizarTranca(id, tranca);
        Validacao.validarCampos(tranca);
        ctx.status(200);
        ctx.result(JavalinJson.toJson(resultado));
    }

    public static void deletarTranca(Context ctx) {
        String id = ctx.pathParam(TRANCA_ID);
        trancaRepositorio.deletarTranca(id);
        ctx.status(200);
    }

    public static void buscaBicicletaPorTranca(Context ctx) {
        String id = ctx.pathParam(TRANCA_ID);
        Bicicleta bicicleta = trancaRepositorio.buscarBicicletaPorTranca(id);
        String response = JavalinJson.getToJsonMapper().map(bicicleta);
        ctx.status(200);
        ctx.result(response);
    }

    public static void alterarStatus(Context ctx) {
        String id = ctx.pathParam(TRANCA_ID);
        String status = ctx.pathParam("acao");
        Tranca tranca = trancaRepositorio.alterarStatus(id, status);
        String response = JavalinJson.getToJsonMapper().map(tranca);
        ctx.status(200);
        ctx.result(response);
    }

    public static void integrarNaRede(Context ctx) {
        String body = ctx.body();
        TrancaNaRede trancaNaRede = JavalinJson.getFromJsonMapper().map(body, TrancaNaRede.class);
        Validacao.validarCampos(trancaNaRede);
        trancaRepositorio.integrarNaRede(trancaNaRede);
        ctx.status(200);
    }

    public static void removerDaRede(Context ctx) {
        String body = ctx.body();
        TrancaNaRede trancaNaRede = JavalinJson.getFromJsonMapper().map(body, TrancaNaRede.class);
        Validacao.validarCampos(trancaNaRede);
        trancaRepositorio.removerDaRede(trancaNaRede);
        ctx.status(200);
    }
}
