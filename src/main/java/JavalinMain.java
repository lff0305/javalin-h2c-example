import io.javalin.Javalin;
import org.eclipse.jetty.http2.server.HTTP2CServerConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class JavalinMain {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.jetty.server(JavalinMain::createHttp2Server);
        }).start();

        app.get("/", ctx -> ctx.result("Hello World"));

    }

    private static Server createHttp2Server() {
        Server server = new Server();

        // HTTP Configuration
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSendServerVersion(false);
        HttpConnectionFactory http1 = new HttpConnectionFactory( httpConfig );

        // h2c Configuration
        HttpConfiguration http2Config = new HttpConfiguration();
        HTTP2CServerConnectionFactory h2c = new HTTP2CServerConnectionFactory(http2Config);

        // HTTP 1/2 Connector
        ServerConnector http2Connector = new ServerConnector(server, http1, h2c);
        http2Connector.setPort(8080);
        server.addConnector(http2Connector);

        return server;
    }
}
