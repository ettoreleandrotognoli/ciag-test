/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciag.service;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.ciag.infra.Manager;
import org.ciag.model.User;

/**
 *
 * @author ettore
 */
@Path("users/")
public class UserService {

    private Manager<User> userManager;

    @Inject
    public UserService(Manager<User> userManager) throws Exception {
        this.userManager = userManager;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> list() throws Exception {
        return userManager.list();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User save(User user) throws Exception {
        userManager.begin();
        user.setId(null);
        userManager.save(user);
        userManager.commit();
        return user;
    }

    @GET
    @Path("{id}/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User get(@PathParam("id") Long id) throws Exception {
        return userManager.load(id);
    }

    @PUT
    @Path("{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User edit(User user, @PathParam("id") Long id) throws Exception {
        userManager.begin();
        User old = get(id);
        user.setId(old.getId());
        userManager.update(user);
        userManager.commit();
        return user;
    }

    @DELETE
    @Path("{id}/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User delete(@PathParam("id") Long id) throws Exception {
        userManager.begin();
        User user = userManager.load(id);
        userManager.delete(user);
        userManager.commit();
        return user;
    }

}
