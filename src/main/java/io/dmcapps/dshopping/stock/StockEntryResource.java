package io.dmcapps.dshopping.stock;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
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
public class StockEntryResource {

    private static final Logger LOGGER = Logger.getLogger(StockEntryResource.class);

    @Inject
    StockEntryService service;

    @Operation(summary = "Returns all the stock-entries from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = StockEntry.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No stockEntries")
    @GET
    @Path("/{id}/stock-entries")
    public Response getAllStockEntries() {
        List<StockEntry> stockEntries = service.findAllStockEntries();
        LOGGER.debug("Total number of stockEntries " + stockEntries);
        return Response.ok(stockEntries).build();
    }

    @Operation(summary = "Returns a stock-entry for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = StockEntry.class)))
    @APIResponse(responseCode = "204", description = "The stockEntry is not found for a given identifier")
    @GET
    @Path("/stock-entries/{id}")
    public Response getStockEntry(
        @Parameter(description = "stock-entry identifier", required = true)
        @PathParam("id") Long id) {
        StockEntry stockEntry = service.findStockEntryById(id);
        if (stockEntry != null) {
            LOGGER.debug("Found stockEntry " + stockEntry);
            return Response.ok(stockEntry).build();
        } else {
            LOGGER.debug("No stockEntry found with id " + id);
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Returns a stock-entry for a given store and product identifiers")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = StockEntry.class)))
    @APIResponse(responseCode = "204", description = "stock-entry was not found for a given store and product identifier")
    @GET
    @Path("stores/{storeId}/products/{productId}/stock-entries/")
    public Response getStoreProductStockEntry(
        @Parameter(description = "store identifier", required = true)
        @PathParam("storeId") String storeId,
        @Parameter(description = "product identifier", required = true)
        @PathParam("productId") Long productId) {
        List<StockEntry>  stockEntries = service.findStockEntryByStoreProduct(storeId, productId);
        if (stockEntries != null) {
            LOGGER.debug("Found stock-entries " + stockEntries);
            return Response.ok(stockEntries).build();
        } else {
            LOGGER.debug("No stock-entries found with storeId " + storeId + "and productId " + productId);
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Creates a valid stock-entry")
    @APIResponse(responseCode = "201", description = "The URI of the created stock-entry", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    @POST
    @Path("/stock-entries")
    public Response createStockEntry(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = StockEntry.class)))
        @Valid StockEntry stockEntry, @Context UriInfo uriInfo) {
        stockEntry = service.persistStockEntry(stockEntry);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(stockEntry.id));
        LOGGER.debug("New stock-entry created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/stock-entries")
    public Response updateStockEntry(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Stock.class)))
        @Valid StockEntry stockEntry) {
        stockEntry = service.updateStockEntry(stockEntry);
        LOGGER.debug("stock-entry updated with new valued " + stockEntry);
        return Response.ok(stockEntry).build();
    }

    @Operation(summary = "Deletes an exiting stockEntry")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/stock-entries/{id}")
    public Response deleteStockEntry(
        
        @Parameter(description = "stock-entry identifier", required = true)
        @PathParam("id") Long id) {
        service.deleteStockEntry(id);
        LOGGER.debug("stock-entry deleted with " + id);
        return Response.noContent().build();
    }

}