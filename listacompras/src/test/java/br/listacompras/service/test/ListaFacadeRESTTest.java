/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.listacompras.service.test;

import br.listacompras.model.Lista;
import br.listacompras.service.ListaFacadeREST;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Mayko
 */
@RunWith(Arquillian.class)
public class ListaFacadeRESTTest {

    @EJB
    ListaFacadeREST servico;

    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").
                addPackage(ListaFacadeREST.class.getPackage()).
                addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").
                addAsManifestResource("test-persistence.xml",
                        ArchivePaths.create("persistence.xml"));
    }

    private Lista criaLista() {
        Lista lista = new Lista();
        lista.setDescItem("Item Teste");
        lista.setTagsItem("teste,teste2");
        lista.setValorItem(new BigDecimal("10.2"));

        return lista;
    }

    @Test
    public void TestURI() {
        List<Lista> result = servico.findAll();

        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void createLista() {
        Lista result = servico.create(criaLista());

        Assert.assertTrue(result.getId() > 0);
    }

    @Test
    public void editLista() {
        createLista();

        List<Lista> result = servico.findByDescItem("Item Teste");

        try {
            for (Lista editar : result) {
                editar.setValorItem(new BigDecimal("13.4"));
                servico.edit(editar.getId(), editar);
            }
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void findByDescItem() {
        createLista();

        List<Lista> result = servico.findByDescItem("Item Teste");

        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void deleteLastLista() {
        createLista();

        List<Lista> result = servico.findByDescItem("Item Teste");

        try {
            for (Lista deletar : result) {
                servico.remove(deletar.getId());
            }
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
   
    private Client getClient() {
        return ClientBuilder.newClient().register(MoxyJsonFeature.class);
    }
    
    @Test
    @RunAsClient
    public void getTest(@ArquillianResource URL baseUrl) {
        Client client = getClient();
        WebTarget target = client.target(baseUrl.toString()).path("rest").path("lista");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Assert.assertEquals(200, response.getStatus());
    }
    
    @Test
    @RunAsClient
    public void getTestDescItem(@ArquillianResource URL baseUrl) {
        //Garante que exista o item na tabela
        postTest(baseUrl);
        
        Client client = getClient();
        WebTarget target = client.target(baseUrl.toString())
                .path("rest").path("lista").path("descitem").path("Item Teste");
        
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Assert.assertEquals(200, response.getStatus());
        
        List<Lista> result = response.readEntity(new GenericType<List<Lista>>() {});                
        
        Assert.assertTrue(result.size() > 0);
                
        for(Lista lista : result) {
            Assert.assertEquals("Item Teste", lista.getDescItem());
        }        
    }

    @Test
    @RunAsClient
    public void postTest(@ArquillianResource URL baseUrl) {
        Client client = getClient();
        WebTarget target = client.target(baseUrl.toString()).path("rest").path("lista");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.accept(MediaType.APPLICATION_JSON).post(Entity.entity(criaLista(), MediaType.APPLICATION_JSON));
        Assert.assertEquals(200, response.getStatus());
    }
}
