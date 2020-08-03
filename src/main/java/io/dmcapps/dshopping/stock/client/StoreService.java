package io.dmcapps.dshopping.stock.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/stores")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface StoreService {

    @GET
    Store getNearByStores(
        @QueryParam("lon") double lon,
        @QueryParam("lat") double lat,
        @QueryParam("range") int range);

}