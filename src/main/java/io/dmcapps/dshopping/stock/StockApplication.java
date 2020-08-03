package io.dmcapps.dshopping.stock;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(title = "Stock API",
        description = "This API allows CRUD operations on a stock",
        version = "1.0",
        contact = @Contact(name = "Daniel Machado", url = "https://github.com/danielmachado86")),
    servers = {
        @Server(url = "http://localhost:8085")
    },
    externalDocs = @ExternalDocumentation(url = "https://github.com/danielmachado86/rest-stock", description = "Stock Rest API"),
    tags = {
        @Tag(name = "api", description = "Public that can be used by anybody"),
        @Tag(name = "stocks", description = "Anybody interested in products stock")
    }
)
public class StockApplication extends Application {
}