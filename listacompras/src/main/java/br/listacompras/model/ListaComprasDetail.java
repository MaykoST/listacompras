/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.listacompras.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mayko
 */
@Entity
@Table(name = "listacompras2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaComprasDetail.findAll", query = "SELECT l FROM ListaComprasDetail l"),
    @NamedQuery(name = "ListaComprasDetail.findById", query = "SELECT l FROM ListaComprasDetail l WHERE l.listaComprasDetailPK.id = :id"),
    @NamedQuery(name = "ListaComprasDetail.findByIdItem", query = "SELECT l FROM ListaComprasDetail l WHERE l.listaComprasDetailPK.idItem = :idItem"),
    @NamedQuery(name = "ListaComprasDetail.findByDescricaoItem", query = "SELECT l FROM ListaComprasDetail l WHERE l.descricaoItem = :descricaoItem"),
    @NamedQuery(name = "ListaComprasDetail.findByValorItem", query = "SELECT l FROM ListaComprasDetail l WHERE l.valorItem = :valorItem")})
public class ListaComprasDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ListaComprasDetailPK listaComprasDetailPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descricao_item")
    private String descricaoItem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_item")
    private BigDecimal valorItem;
    @Lob
    @Column(name = "image_item")
    private byte[] imageItem;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ListaComprasMaster listaComprasMaster;

    public ListaComprasDetail() {
    }

    public ListaComprasDetail(ListaComprasDetailPK listaComprasDetailPK) {
        this.listaComprasDetailPK = listaComprasDetailPK;
    }

    public ListaComprasDetail(ListaComprasDetailPK listaComprasDetailPK, String descricaoItem) {
        this.listaComprasDetailPK = listaComprasDetailPK;
        this.descricaoItem = descricaoItem;
    }

    public ListaComprasDetail(int id, int idItem) {
        this.listaComprasDetailPK = new ListaComprasDetailPK(id, idItem);
    }

    public ListaComprasDetailPK getListaComprasDetailPK() {
        return listaComprasDetailPK;
    }

    public void setListaComprasDetailPK(ListaComprasDetailPK listaComprasDetailPK) {
        this.listaComprasDetailPK = listaComprasDetailPK;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public BigDecimal getValorItem() {
        return valorItem;
    }

    public void setValorItem(BigDecimal valorItem) {
        this.valorItem = valorItem;
    }

    public byte[] getImageItem() {
        return imageItem;
    }

    public void setImageItem(byte[] imageItem) {
        this.imageItem = imageItem;
    }

    public ListaComprasMaster getListaComprasMaster() {
        return listaComprasMaster;
    }

    public void setListaComprasMaster(ListaComprasMaster listaComprasMaster) {
        this.listaComprasMaster = listaComprasMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listaComprasDetailPK != null ? listaComprasDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaComprasDetail)) {
            return false;
        }
        ListaComprasDetail other = (ListaComprasDetail) object;
        if ((this.listaComprasDetailPK == null && other.listaComprasDetailPK != null) || (this.listaComprasDetailPK != null && !this.listaComprasDetailPK.equals(other.listaComprasDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.listacompras.ListaComprasDetail[ listaComprasDetailPK=" + listaComprasDetailPK + " ]";
    }
    
}
