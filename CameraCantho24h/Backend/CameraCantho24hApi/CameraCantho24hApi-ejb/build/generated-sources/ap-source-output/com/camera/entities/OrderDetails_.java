package com.camera.entities;

import com.camera.entities.OrderDetailsPK;
import com.camera.entities.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-13T22:05:37")
@StaticMetamodel(OrderDetails.class)
public class OrderDetails_ { 

    public static volatile SingularAttribute<OrderDetails, Double> unitPrice;
    public static volatile SingularAttribute<OrderDetails, OrderDetailsPK> orderDetailsPK;
    public static volatile SingularAttribute<OrderDetails, Integer> quantity;
    public static volatile SingularAttribute<OrderDetails, Date> feedBackAt;
    public static volatile SingularAttribute<OrderDetails, Integer> rateStar;
    public static volatile SingularAttribute<OrderDetails, String> content;
    public static volatile SingularAttribute<OrderDetails, Products> products;

}