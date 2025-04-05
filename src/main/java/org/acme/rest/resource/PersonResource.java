package org.acme.rest.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.rest.dto.PersonDTO;
import org.acme.service.PersonService;

import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    @Inject
    PersonService personService;

    @GET
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        PersonDTO personDTO = personService.findById(id);
        if (personDTO != null) {
            return Response.ok(personDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response persist(PersonDTO personDTO) {
        PersonDTO newPersonDTO = personService.persist(personDTO);
        return Response.status(Response.Status.CREATED).entity(newPersonDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PersonDTO personDTO) {
        PersonDTO updatedPersonDTO = personService.update(id, personDTO);
        if (updatedPersonDTO != null) {
            return Response.ok(updatedPersonDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        if (personService.delete(id)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}