package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.acme.controller.api.AddressDTO;
import org.acme.service.AddressService;

@Path("/Anagrafica/Address")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class AddressResource {
    @Inject
    AddressService addressService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persist(AddressDTO addressDTO) {
        log.info("AddressResource -> persist({})", addressDTO);
        addressService.persist(addressDTO);
        return Response.status(Response.Status.OK).
                entity("Indirizzo salvato con successo!").
                build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        log.info("AddressResource -> deleteById({})", id);
        addressService.deleteById(id);
        //return Response.noContent().build();
        return Response.status(Response.Status.OK).
                entity("Indirizzo eliminato con successo!").
                build();
    }
}