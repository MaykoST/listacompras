/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.listacompras.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mayko
 */
@Embeddable
public class ListaComprasDetailPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "id_item")
    private int idItem;

    public ListaComprasDetailPK() {
    }

    public ListaComprasDetailPK(int id, int idItem) {
        this.id = id;
        this.idItem = idItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaComprasDetailPK)) {
            return false;
        }
        ListaComprasDetailPK other = (ListaComprasDetailPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idItem != other.idItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.listacompras.ListaComprasDetailPK[ id=" + id + ", idItem=" + idItem + " ]";
    }
    
}
