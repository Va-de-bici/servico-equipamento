package vadebici;

import vadebici.db_in_memory.Database;
import vadebici.util.JavalinApp;

public class Main {
    public static void main(String[] args) {
        JavalinApp app = new JavalinApp();
        Database.carregarDados();
        app.start(getHerokuAssignedPort());
    }

    private static int getHerokuAssignedPort() {
        String herokuPort = System.getenv("PORT");
        if (herokuPort != null) {
            return Integer.parseInt(herokuPort);
        }
        return 7001;
    }
}
