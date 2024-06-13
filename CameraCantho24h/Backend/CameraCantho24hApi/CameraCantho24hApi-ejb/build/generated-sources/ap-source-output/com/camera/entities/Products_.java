package com.camera.entities;

import com.camera.entities.Categories;
import com.camera.entities.ImportDetails;
import com.camera.entities.OrderDetails;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-13T22:05:37")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, Double> unitPrice;
    public static volatile SingularAttribute<Products, Integer> quantity;
    public static volatile SingularAttribute<Products, Integer> productID;
    public static volatile SingularAttribute<Products, Categories> cateID;
    public static volatile CollectionAttribute<Products, ImportDetails> importDetailsCollection;
    public static volatile CollectionAttribute<Products, OrderDetails> orderDetailsCollection;
    public static volatile SingularAttribute<Products, String> description;
    public static volatile SingularAttribute<Products, Double> discount;
    public static volatile SingularAttribute<Products, String> warranty;
    public static volatile SingularAttribute<Products, String> avatar;
    public static volatile SingularAttribute<Products, String> productName;

}