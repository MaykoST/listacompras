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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

/**
 *
 * @author Mayko
 */
@Entity
@Table(name = "lista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lista.findAll", query = "SELECT l FROM Lista l"),
    @NamedQuery(name = "Lista.findById", query = "SELECT l FROM Lista l WHERE l.id = :id"),
    @NamedQuery(name = "Lista.findByDescItem", query = "SELECT l FROM Lista l WHERE l.descItem = :descItem"),
    @NamedQuery(name = "Lista.findByUserId", query = "SELECT l FROM Lista l WHERE l.userId = :userId"),
    @NamedQuery(name = "Lista.findByValorItem", query = "SELECT l FROM Lista l WHERE l.valorItem = :valorItem")})
public class Lista implements Serializable {
    @Column(name = "comprado")
    private Boolean comprado;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "img_item")
    private String imgItem;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "desc_item")
    private String descItem;
    @Column(name = "user_id")
    private Integer userId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_item")
    private BigDecimal valorItem;
    @Basic(optional = true)    
    @Size(max = 1000)
    @Column(name = "tags_item")
    private String tagsItem;

    
    public Lista() {
    }

    public Lista(Integer id) {
        this.id = id;
    }

    public Lista(Integer id, String descItem) {
        this.id = id;
        this.descItem = descItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescItem() {
        return descItem;
    }

    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getValorItem() {
        return valorItem;
    }

    public void setValorItem(BigDecimal valorItem) {
        this.valorItem = valorItem;
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
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.listacompras.model.Lista[ id=" + id + " ]";
    }

    public Boolean getComprado() {
        return comprado;
    }

    public void setComprado(Boolean comprado) {
        this.comprado = comprado;
    }

    public String getImgItem() {
        return imgItem;
    }

    public void setImgItem(String imgItem) {
        this.imgItem = imgItem;
    }

    public String getTagsItem() {
        return tagsItem;
    }

    public void setTagsItem(String tagsItem) {
        this.tagsItem = tagsItem;
    }            
}
