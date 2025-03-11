package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.acme.controller.api.PersonDTO;
import org.acme.service.PersonService;
import org.eclipse.microprofile.openapi.annotations.Operation;

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
    @Operation
    public List<PersonDTO> findByString(@QueryParam("string") String string) {
        log.info("PersonResource -> findByString({})", string);
        return personService.findByString(string);
    }
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation
    public PersonDTO findById(@PathParam("id") Long id) {
        log.info("PersonResource -> findById({})", id);
        return personService.findById(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation
    public Response persist(PersonDTO personDTO) {
        log.info("PersonResource -> persist({})", personDTO);
        personService.persist(personDTO);
        return Response.created(URI.create("/person/" + personDTO.getId()))
                .entity("Persona aggiunta con successo!")
                .build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation
    public Response deleteById(@PathParam("id") Long id) {
        log.info("PersonResource -> deleteById({})", id);
        personService.deleteById(id);
        String message = "Persona cancellata con successo!";
        return Response.status(Response.Status.OK).
                entity("Persona cancellata con successo!").
                build();
    }
}