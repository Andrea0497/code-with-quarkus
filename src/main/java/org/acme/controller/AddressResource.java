package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.acme.controller.api.AddressDTO;
import org.acme.service.AddressService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.net.URI;

//TODO - VALUTARE @CONSUMES E @PRODUCES
@Path("/Anagrafica")
@Slf4j
public class AddressResource {
    @Inject
    AddressService addressService;
    @POST
    @Path("/{personId}/addresses")
    //...(MediaType.APPLICATION_JSON) = ...("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation
    public Response persist(@PathParam("personId") Long personId, AddressDTO addressDTO) {
        log.info("AddressResource -> persist({}, {})", personId, addressDTO);
        addressDTO.setPersonId(personId);
        addressService.persist(addressDTO);
        /*return Response.status(Response.Status.OK).
                entity("Indirizzo salvato con successo!").
                build();*/
        return Response.created(URI.create("/addresses/" + addressDTO.getId()))
                .entity("Indirizzo salvato con successo!")
                .build();
    }
    @DELETE
    @Path("/addresses/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation
    public Response deleteById(@PathParam("id") Long id) {
        log.info("AddressResource -> deleteById({})", id);
        addressService.deleteById(id);
        //return Response.noContent().build();
        return Response.status(Response.Status.OK).
                entity("Indirizzo eliminato con successo!").
                build();
    }
}