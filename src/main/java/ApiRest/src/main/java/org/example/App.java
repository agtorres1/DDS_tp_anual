package ApiRest.src.main.java.org.example;
import ApiRest.src.main.java.org.example.controladores.analizarFusionController;
import ApiRest.src.main.java.org.example.controladores.fusionarComunidadesController;
import io.javalin.Javalin;
public class App 
{
    public static void main( String[] args )
    {
        Integer port = Integer.parseInt(System.getProperty("port", "8082"));
        Javalin app = Javalin.create().start(port);
        app.get("/", ctx -> ctx.result("Test"));
        app.post("/api/analizar-fusion", new analizarFusionController());
        app.post("/api/fusionar-comunidades", new fusionarComunidadesController());
    }
}
