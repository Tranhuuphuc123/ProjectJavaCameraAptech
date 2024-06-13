/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camera.entities;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tran Huu Phuc
 */
@Entity
@Table(name = "Import")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Import.findAll", query = "SELECT i FROM Import i"),
    @NamedQuery(name = "Import.findByImportID", query = "SELECT i FROM Import i WHERE i.importID = :importID"),
    @NamedQuery(name = "Import.findByImportDate", query = "SELECT i FROM Import i WHERE i.importDate = :importDate")})
public class Import implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "importID")
    private Integer importID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date importDate;
    @JoinColumn(name = "supplierID", referencedColumnName = "supplierID")
    @ManyToOne(optional = false)
    private Suppliers supplierID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "import1")
    private Collection<ImportDetails> importDetailsCollection;

    public Import() {
    }

    public Import(Integer importID) {
        this.importID = importID;
    }

    public Import(Integer importID, Date importDate) {
        this.importID = importID;
        this.importDate = importDate;
    }

    public Integer getImportID() {
        return importID;
    }

    public void setImportID(Integer importID) {
        this.importID = importID;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Suppliers getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Suppliers supplierID) {
        this.supplierID = supplierID;
    }

    @XmlTransient
    public Collection<ImportDetails> getImportDetailsCollection() {
        return importDetailsCollection;
    }

    public void setImportDetailsCollection(Collection<ImportDetails> importDetailsCollection) {
        this.importDetailsCollection = importDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (importID != null ? importID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Import)) {
            return false;
        }
        Import other = (Import) object;
        if ((this.importID == null && other.importID != null) || (this.importID != null && !this.importID.equals(other.importID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.camera.entities.Import[ importID=" + importID + " ]";
    }
    
}
