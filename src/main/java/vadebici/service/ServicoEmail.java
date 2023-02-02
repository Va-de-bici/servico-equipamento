package vadebici.service;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import vadebici.dom.RequisicaoEmail;
import vadebici.util.Segredos;

public class ServicoEmail {

    private final MailgunMessagesApi mailgunMessagesApi;

    public ServicoEmail() {
        mailgunMessagesApi = MailgunClient.config(Segredos.getMailgunAccess())
                .createApi(MailgunMessagesApi.class);
    }

    public void enviarMensagem(RequisicaoEmail requisicaoEmail) {
        String MESSAGE_FROM = "noreply@vadebici.com";
        String MESSAGE_SUBJECT = "vadebici.com";
        Message message = Message.builder()
                .from(MESSAGE_FROM)
                .to(requisicaoEmail.getEmail())
                .subject(MESSAGE_SUBJECT)
                .text(requisicaoEmail.getMensagem())
                .build();
        mailgunMessagesApi.sendMessage(Segredos.getEmailDomainName(), message);
    }
}
