package ApiRest.src.main.java.org.example;
import ApiRest.src.main.java.org.example.controladores.AnalizarFusionController;
import ApiRest.src.main.java.org.example.controladores.FusionarComunidadesController;
import io.javalin.Javalin;
public class App 
{
    public static void main( String[] args )
    {
        Integer port = Integer.parseInt(System.getProperty("port", "8082"));
        Javalin app = Javalin.create().start(port);
        app.get("/", ctx -> ctx.result("Test"));
        app.post("/api/analizar-fusion", new AnalizarFusionController());
        app.post("/api/fusionar-comunidades", new FusionarComunidadesController());
    }
}
