/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.listacompras.service;

import br.listacompras.model.Lista;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Mayko
 */
@Stateless
@Path("lista")
public class ListaFacadeREST extends AbstractFacade<Lista> {
    @PersistenceContext(unitName = "br.listacompras_listacompras_war_1.0-SNAPSHOTPU")
    public EntityManager em;

    public ListaFacadeREST() {
        super(Lista.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Lista create(Lista entity) {
        super.create(entity);
        
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Lista entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Lista find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("descitem/{descitem}")
    @Produces({"application/xml", "application/json"})
    public List<Lista> findByDescItem(@PathParam("descitem") String descItem) {
        Query q = getEntityManager().createNamedQuery("Lista.findByDescItem");
        q.setParameter("descItem", descItem);                
        return q.getResultList();
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Lista> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Lista> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
