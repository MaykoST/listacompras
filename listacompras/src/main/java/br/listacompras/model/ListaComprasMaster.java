/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.listacompras.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mayko
 */
@Entity
@Table(name = "listacompras1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaComprasMaster.findAll", query = "SELECT l FROM ListaComprasMaster l"),
    @NamedQuery(name = "ListaComprasMaster.findById", query = "SELECT l FROM ListaComprasMaster l WHERE l.id = :id"),
    @NamedQuery(name = "ListaComprasMaster.findByDescricao", query = "SELECT l FROM ListaComprasMaster l WHERE l.descricao = :descricao"),
    @NamedQuery(name = "ListaComprasMaster.findByData", query = "SELECT l FROM ListaComprasMaster l WHERE l.data = :data")})
public class ListaComprasMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaComprasMaster")
    private Collection<ListaComprasDetail> listaComprasDetailCollection;

    public ListaComprasMaster() {
    }

    public ListaComprasMaster(Integer id) {
        this.id = id;
    }

    public ListaComprasMaster(Integer id, String descricao, Date data) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @XmlTransient
    public Collection<ListaComprasDetail> getListaComprasDetailCollection() {
        return listaComprasDetailCollection;
    }

    public void setListaComprasDetailCollection(Collection<ListaComprasDetail> listaComprasDetailCollection) {
        this.listaComprasDetailCollection = listaComprasDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaComprasMaster)) {
            return false;
        }
        ListaComprasMaster other = (ListaComprasMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.listacompras.ListaComprasMaster[ id=" + id + " ]";
    }
    
}
