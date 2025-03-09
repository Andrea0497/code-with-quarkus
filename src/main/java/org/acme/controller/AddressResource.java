package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.acme.controller.api.AddressDTO;
import org.acme.service.AddressService;

import java.net.URI;

//TODO - EVALUATE @CONSUMES AND @PRODUCES
@Path("/Anagrafica")
@Slf4j
public class AddressResource {
    @Inject
    AddressService addressService;
    @POST
    @Path("/{personId}/addresses")
    @Consumes(MediaType.APPLICATION_JSON)
    // -> = @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(@PathParam("personId") Long personId, AddressDTO addressDTO) {
        log.info("AddressResource -> persist({}, {})", personId, addressDTO);
        addressDTO.setPersonId(personId);
        addressService.persist(addressDTO);
        /*return Response.status(Response.Status.OK).
                entity("Address added successfully!").
                build();*/
        return Response.created(URI.create("/addresses/" + addressDTO.getId()))
                .entity("Address added successfully!")
                .build();
    }
    @DELETE
    @Path("/addresses/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        log.info("AddressResource -> deleteById({})", id);
        addressService.deleteById(id);
        //return Response.noContent().build();
        return Response.status(Response.Status.OK).
                entity("Address deleted successfully!").
                build();
    }
}