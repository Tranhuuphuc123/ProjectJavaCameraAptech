/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camera.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tran Huu Phuc
 */
@Entity
@Table(name = "OrderDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetails.findAll", query = "SELECT o FROM OrderDetails o"),
    @NamedQuery(name = "OrderDetails.findByOrderID", query = "SELECT o FROM OrderDetails o WHERE o.orderDetailsPK.orderID = :orderID"),
    @NamedQuery(name = "OrderDetails.findByProductID", query = "SELECT o FROM OrderDetails o WHERE o.orderDetailsPK.productID = :productID"),
    @NamedQuery(name = "OrderDetails.findByUnitPrice", query = "SELECT o FROM OrderDetails o WHERE o.unitPrice = :unitPrice"),
    @NamedQuery(name = "OrderDetails.findByFeedBackAt", query = "SELECT o FROM OrderDetails o WHERE o.feedBackAt = :feedBackAt"),
    @NamedQuery(name = "OrderDetails.findByContent", query = "SELECT o FROM OrderDetails o WHERE o.content = :content"),
    @NamedQuery(name = "OrderDetails.findByRateStar", query = "SELECT o FROM OrderDetails o WHERE o.rateStar = :rateStar"),
    @NamedQuery(name = "OrderDetails.findByQuantity", query = "SELECT o FROM OrderDetails o WHERE o.quantity = :quantity")})
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderDetailsPK orderDetailsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unitPrice")
    private double unitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feedBackAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feedBackAt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "content")
    private String content;
    @Column(name = "rateStar")
    private Integer rateStar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "productID", referencedColumnName = "productID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Products products;

    public OrderDetails() {
    }

    public OrderDetails(OrderDetailsPK orderDetailsPK) {
        this.orderDetailsPK = orderDetailsPK;
    }

    public OrderDetails(OrderDetailsPK orderDetailsPK, double unitPrice, Date feedBackAt, String content, int quantity) {
        this.orderDetailsPK = orderDetailsPK;
        this.unitPrice = unitPrice;
        this.feedBackAt = feedBackAt;
        this.content = content;
        this.quantity = quantity;
    }

    public OrderDetails(int orderID, int productID) {
        this.orderDetailsPK = new OrderDetailsPK(orderID, productID);
    }

    public OrderDetailsPK getOrderDetailsPK() {
        return orderDetailsPK;
    }

    public void setOrderDetailsPK(OrderDetailsPK orderDetailsPK) {
        this.orderDetailsPK = orderDetailsPK;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getFeedBackAt() {
        return feedBackAt;
    }

    public void setFeedBackAt(Date feedBackAt) {
        this.feedBackAt = feedBackAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRateStar() {
        return rateStar;
    }

    public void setRateStar(Integer rateStar) {
        this.rateStar = rateStar;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailsPK != null ? orderDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetails)) {
            return false;
        }
        OrderDetails other = (OrderDetails) object;
        if ((this.orderDetailsPK == null && other.orderDetailsPK != null) || (this.orderDetailsPK != null && !this.orderDetailsPK.equals(other.orderDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.camera.entities.OrderDetails[ orderDetailsPK=" + orderDetailsPK + " ]";
    }
    
}
