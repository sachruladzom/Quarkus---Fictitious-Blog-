package com.test;

import com.test.model.Post;
import com.test.model.PostHasTag;
import com.test.model.Tag;
import com.test.repository.PostRepository;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/post")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class BlogPost {

    @GET
    public List<Post> get() {
        return Post.listAll();
    }

    @GET
    @Path("{id}")
    public Post getByid(@PathParam Long id){
        Post entity = Post.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Post with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Post post){

        post.persist();
        List<PostHasTag> postHasTags = post.getTag();
        for(int i=0;i<postHasTags.size();i++){
            Tag tgs = postHasTags.get(i).getTag();
            Tag findTags = Tag.find("label",tgs.getLabel()).firstResult();
            PostHasTag postHasTag = new PostHasTag();
            if(findTags != null){
                postHasTag.setPost_id(post.getId());
                postHasTag.setTag_id(tgs.getId());
                postHasTag.persist();
            }


        }

        return Response.ok(post).status(200).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Post update(@PathParam Long id, Post post){
        if (post.getTitle() == null) {
            throw new WebApplicationException("Post title was not set on request.", 422);
        }
        if (post.getContent() == null) {
            throw new WebApplicationException("Post Content was not set on request.", 422);
        }

        Post entity = Post.findById(id);

        if (entity == null) {
            throw new WebApplicationException("post with id of " + id + " does not exist.", 404);
        }

        entity.setTitle(post.getTitle());
        entity.setContent(post.getContent());

        PostHasTag.delete("post_id",id);
        List<PostHasTag> postHasTags = post.getTag();
        for(int i=0;i<postHasTags.size();i++){
            Tag tgs = postHasTags.get(i).getTag();
            Tag findTags = Tag.find("label",tgs.getLabel()).firstResult();
            PostHasTag postHasTag = new PostHasTag();
            if(findTags != null){
                postHasTag.setPost_id(post.getId());
                postHasTag.setTag_id(tgs.getId());
                postHasTag.persist();
            }


        }

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        Post entity = Post.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Tag with id of " + id + " does not exist.", 404);
        }

        entity.delete();
        PostHasTag.delete("post_id",id);
        return Response.status(204).build();
    }
}