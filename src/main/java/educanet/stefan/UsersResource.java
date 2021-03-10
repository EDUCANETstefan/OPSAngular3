package cz.educanet.stefan;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    private UsersManager manager;

    public static List<User> names = new ArrayList<User>();


    @POST
    @Path("register")
    public Response createUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        User user = new User(username, password);
        if(user != null) {
            return Response.ok("This username already exists").build();
        }
        names.add(user);
        return Response.ok("This username:" + user + "is ok").build();
    }

    @POST
    @Path("login")
    public Response loginUser(@FormParam("username") String username, @FormParam("password") String password) {
        for(int x = 0; x < names.size(); x++) {
            User user = names.get(x);
            if (user.username.equals(username) & user.password.equals(password)) {
                manager.user = user;
                return Response.ok("User is logged").build();
            }

        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getLoggedUser() {
        return  Response.ok(manager.user).build();
    }

}
