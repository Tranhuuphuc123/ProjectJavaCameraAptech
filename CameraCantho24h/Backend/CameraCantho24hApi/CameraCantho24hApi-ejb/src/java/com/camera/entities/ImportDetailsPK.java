/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camera.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tran Huu Phuc
 */
@Embeddable
public class ImportDetailsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "importID")
    private int importID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productID")
    private int productID;

    public ImportDetailsPK() {
    }

    public ImportDetailsPK(int importID, int productID) {
        this.importID = importID;
        this.productID = productID;
    }

    public int getImportID() {
        return importID;
    }

    public void setImportID(int importID) {
        this.importID = importID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) importID;
        hash += (int) productID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImportDetailsPK)) {
            return false;
        }
        ImportDetailsPK other = (ImportDetailsPK) object;
        if (this.importID != other.importID) {
            return false;
        }
        if (this.productID != other.productID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.camera.entities.ImportDetailsPK[ importID=" + importID + ", productID=" + productID + " ]";
    }
    
}
