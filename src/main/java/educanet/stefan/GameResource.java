package cz.educanet.stefan;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("games")
public class GameResource {

    @Inject
    private GameManager manager;


    @GET
    public Response getAll() {
        return Response.ok(manager.getGames()).build();
    }

    @GET
    @Path("{id}")
    public Response getGame(@PathParam("id") int id) {
        return  Response.ok(manager.getGame(id)).build();
    }

    @POST
    public Response createGame(Game game){
        if(!manager.create(game))
            return Response.status(400).build();

        return Response.ok(game).build();
    }

    @PUT
    @Path("{id}")
    public Response editGame(@PathParam("id") int id, Game game) {
        if(!manager.editGame(id, game)) {
            return Response.ok().build();
        }
         else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteGame(@PathParam("id") int id) {
        if(manager.removeGame(id)){
            return Response.ok("Game removed").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
