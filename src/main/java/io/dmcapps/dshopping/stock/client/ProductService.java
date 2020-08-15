package io.dmcapps.dshopping.stock.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface ProductService {

    @GET
    @Path("/search")
    List<Product> searchProduct(@QueryParam("q") String  search);
}