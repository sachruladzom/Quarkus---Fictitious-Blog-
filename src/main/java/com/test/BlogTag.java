package com.test;

import com.test.model.Post;
import com.test.model.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tag")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class BlogTag {

    @Inject
    EntityManager entityManager;

    @GET
    public List<Tag> get() {
        return Tag.listAll();
    }
    @GET
    @Path("{id}")
    public Tag getByid(@PathParam Long id){
        Tag entity = Tag.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Tag with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Tag tag){
        entityManager.persist(tag);

        return Response.ok(tag).status(200).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Tag update(@PathParam Long id, Tag tag){
        if (tag.getLabel() == null) {
            throw new WebApplicationException("Tag Name was not set on request.", 422);
        }

        Tag entity = Tag.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Tag with id of " + id + " does not exist.", 404);
        }

        entity.setLabel(tag.getLabel());

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        Tag entity = Tag.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Tag with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }
}