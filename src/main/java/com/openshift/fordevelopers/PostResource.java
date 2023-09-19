
package com.openshift.fordevelopers;



import io.quarkus.hibernate.orm.panache.Panache;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {
  public PostResource() {
  }

  @GET
  public Response list() {
    return Response.ok(Post.listAll()).build();
  }

  @POST
  @Transactional
  public Response add(Post post) {
    post.persist();
    return Response.ok(Post.listAll()).build();
  }

  @DELETE
  @Transactional
  public Response delete(Post post) {
    EntityManager em = Panache.getEntityManager();
    em.remove(em.contains(post) ? post : em.merge(post));
    return Response.ok().build();
  }
}
