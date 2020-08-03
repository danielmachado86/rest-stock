package io.dmcapps.dshopping.stock;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api")
@Produces(APPLICATION_JSON)
public class StockResource {

    private static final Logger LOGGER = Logger.getLogger(StockResource.class);

    @Inject
    StockService service;

    @Operation(summary = "Returns all the stocks from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Stock.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No stocks")
    @GET
    public Response getAllProductStocks() {
        List<Stock> stocks = service.findAllStocks();
        LOGGER.debug("Total number of stocks " + stocks);
        return Response.ok(stocks).build();
    }

    @Operation(summary = "Returns all the stocks from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Stock.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No stocks")
    @GET
    public Response getAllStoreStocks() {
        List<Stock> stocks = service.findAllStocks();
        LOGGER.debug("Total number of stocks " + stocks);
        return Response.ok(stocks).build();
    }

    @Operation(summary = "Returns a stock for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Stock.class)))
    @APIResponse(responseCode = "204", description = "The stock is not found for a given identifier")
    @GET
    @Path("/stocks")
    public Response searchStock(
        @Parameter(description = "Store identifier", required = true)
        @QueryParam("location-lon") Double lon,
        @Parameter(description = "Store identifier", required = true)
        @QueryParam("location-lat") Double lat,
        @Parameter(description = "Product identifier", required = true)
        @QueryParam("product-search") String searchString) {
        
        String storeId = null;
        String productId = null; 
        Stock stock = service.findStockById(storeId, productId);
        if (stock != null) {
            LOGGER.debug("Found stock " + stock);
            return Response.ok(stock).build();
        } else {
            LOGGER.debug("No stock found with StoreId " + storeId + "and productId " + productId);
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Returns a stock for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Stock.class)))
    @APIResponse(responseCode = "204", description = "The stock is not found for a given identifier")
    @GET
    @Path("/stores/{storeId}/products/{productId}/stocks")
    public Response getStock(
        @Parameter(description = "Store identifier", required = true)
        @PathParam("storeId") String storeId,
        @Parameter(description = "Product identifier", required = true)
        @PathParam("productId") String productId) {
        Stock stock = service.findStockById(storeId, productId);
        if (stock != null) {
            LOGGER.debug("Found stock " + stock);
            return Response.ok(stock).build();
        } else {
            LOGGER.debug("No stock found with StoreId " + storeId + "and productId " + productId);
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Creates a valid stock")
    @APIResponse(responseCode = "201", description = "The URI of the created stock", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    @POST
    public Response createStock(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Stock.class)))
        @Valid Stock stock, @Context UriInfo uriInfo) {
        stock = service.persistStock(stock);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path("/stores").path(stock.storeId).path("/products").path(stock.productId);
        LOGGER.debug("New stock created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    @Operation(summary = "Updates an exiting stock")
    @APIResponse(responseCode = "200", description = "The updated stock", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Stock.class)))
    @PUT
    public Response updateStock(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Stock.class)))
        @Valid Stock stock) {
        stock = service.updateStock(stock);
        LOGGER.debug("Stock updated with new valued " + stock);
        return Response.ok(stock).build();
    }

    @Operation(summary = "Deletes an exiting stock")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteStock(
        @Parameter(description = "Stock identifier", required = true)
        @PathParam("id") Long id) {
        service.deleteStock(id);
        LOGGER.debug("Stock deleted with " + id);
        return Response.noContent().build();
    }


}