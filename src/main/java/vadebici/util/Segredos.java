package vadebici.util;

public class Segredos {
    private static final String MAILGUN_ACCESS = "dee8d6edc08cb1bb2b76d237e510bc78-75cd784d-50e92491";
    private static final String EMAIL_DOMAIN = "sandbox5fbb90af6cb6400da7d87a1b51eb78ad.mailgun.org";
    private static final String CLIENT_ID = "167ae729-9d7d-45da-920f-e672cd3219b9";
    private static final String CLIENT_S_GETNET= "e4d66975-4389-4bdb-afaa-ccac84e6f9ca";
    private static final String SELLER_ID = "393a0b03-e028-421f-8298-9add9f0bbd27";


    private Segredos(){ //teste
         }


    public static String getMailgunAccess() {
        return MAILGUN_ACCESS;
    }

    public static String getEmailDomainName() {
        return EMAIL_DOMAIN;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }

    public static String getClientSecret() {
        return CLIENT_S_GETNET;
    }

    public static String getSellerId() {
        return SELLER_ID;
    }
}

