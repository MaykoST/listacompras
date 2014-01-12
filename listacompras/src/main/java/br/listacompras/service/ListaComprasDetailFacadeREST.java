/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.listacompras.service;

import br.listacompras.model.ListaComprasDetail;
import br.listacompras.model.ListaComprasDetailPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Mayko
 */
@Stateless
@Path("listacomprasdetail")
public class ListaComprasDetailFacadeREST extends AbstractFacade<ListaComprasDetail> {
    @PersistenceContext(unitName = "br.listacompras_listacompras_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private ListaComprasDetailPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;id=idValue;idItem=idItemValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        br.listacompras.model.ListaComprasDetailPK key = new br.listacompras.model.ListaComprasDetailPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> id = map.get("id");
        if (id != null && !id.isEmpty()) {
            key.setId(new java.lang.Integer(id.get(0)));
        }
        java.util.List<String> idItem = map.get("idItem");
        if (idItem != null && !idItem.isEmpty()) {
            key.setIdItem(new java.lang.Integer(idItem.get(0)));
        }
        return key;
    }

    public ListaComprasDetailFacadeREST() {
        super(ListaComprasDetail.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ListaComprasDetail entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, ListaComprasDetail entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        br.listacompras.model.ListaComprasDetailPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ListaComprasDetail find(@PathParam("id") PathSegment id) {
        br.listacompras.model.ListaComprasDetailPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<ListaComprasDetail> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<ListaComprasDetail> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
