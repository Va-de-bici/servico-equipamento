package vadebici.model;

import vadebici.dom.Totem;
import vadebici.dom.dto.PostTotem;

public class TotemModel {

    public static PostTotem getPostTotem() {
        PostTotem postTotem = new PostTotem();
        postTotem.setLocalizacao("Avenida 123");
        return postTotem;
    }

    public static Totem getTotem() {
        Totem totem = new Totem();
        totem.setLocalizacao("123");
        totem.setId("123");
        return totem;
    }
}
