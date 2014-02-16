/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.listacompras.service.test;

import br.listacompras.model.Lista;
import br.listacompras.service.ListaFacadeREST;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
    
    @Test
    public void TestURI() {
        List<Lista> result = servico.findAll();
        
        Assert.assertTrue(result.size() > 0);
    }
    
    @Test
    public void createLista() {
        Lista lista = new Lista();
        lista.setDescItem("Item Teste");
        lista.setTagsItem("teste,teste2");
        lista.setValorItem(new BigDecimal("10.2"));
        
        Lista result = servico.create(lista);
        
        Assert.assertTrue(result.getId() > 0);
    }        
}
