package vadebici.service;

import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import vadebici.dom.*;
import vadebici.exception.GenericApiException;

public class ServicoExterno {
    public void enviarEmail(RequisicaoEmail email) {
        HttpResponse<String> response = Unirest.post("http://va-de-bici.herokuapp.com/enviarEmail")
                .body(email)
                .asString();
        validarResposta(response);
    }

    private void validarResposta(HttpResponse<String> response) {
        if (response.getStatus() != 200 && response.getStatus() != 201) {
            throw JavalinJson.fromJson(response.getBody(), GenericApiException.class);
        }
    }
}
