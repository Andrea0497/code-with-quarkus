package org.acme.rest.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.rest.dto.AddressDTO;
import org.acme.service.AddressService;

import java.util.List;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {
    @Inject
    AddressService addressService;

    @GET
    public List<AddressDTO> listAll() {
        return addressService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        AddressDTO addressDTO = addressService.findById(id);
        if (addressDTO != null) {
            return Response.ok(addressDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{personId}")
    public Response persist(@PathParam("personId") Long personId, AddressDTO addressDTO) {
        AddressDTO persistedAddressDTO = addressService.persist(personId, addressDTO);
        return Response.status(Response.Status.CREATED).entity(persistedAddressDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, AddressDTO addressDTO) {
        AddressDTO updatedAddressDTO = addressService.update(id, addressDTO);
        if (updatedAddressDTO != null) {
            return Response.ok(updatedAddressDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") Long id) {
        if (addressService.delete(id)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}