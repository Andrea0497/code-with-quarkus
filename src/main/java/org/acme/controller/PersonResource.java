package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.acme.controller.api.PersonDTO;
import org.acme.service.PersonService;

import java.net.URI;
import java.util.List;

//TODO - VALUTARE @CONSUMES E @PRODUCES
@Path("/Anagrafica")
@Slf4j
public class PersonResource {
    @Inject
    PersonService personService;
    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonDTO> findByString(String string) {
        return personService.findByString(string);
    }
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonDTO details(@PathParam("id") Long id) {
        return personService.findById(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(PersonDTO personDTO) {
        personService.persist(personDTO);
        return Response.created(URI.create("/person/" + personDTO.getId()))
                .entity("Persona aggiunta con successo!")
                .build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        personService.deleteById(id);
        String message = "Persona eliminata con successo!";
        return Response.status(Response.Status.OK).
                entity("Persona eliminata con successo!").
                build();
    }
}