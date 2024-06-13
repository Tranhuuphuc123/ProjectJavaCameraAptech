package com.camera.entities;

import com.camera.entities.Orders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-13T22:05:37")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile SingularAttribute<Users, String> gender;
    public static volatile SingularAttribute<Users, String> fullName;
    public static volatile SingularAttribute<Users, String> avatar;
    public static volatile CollectionAttribute<Users, Orders> ordersCollection;
    public static volatile SingularAttribute<Users, Integer> userID;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile SingularAttribute<Users, String> forgotPasswordCode;
    public static volatile SingularAttribute<Users, String> emailVerifyCode;
    public static volatile SingularAttribute<Users, Date> emailVerifyAt;
    public static volatile SingularAttribute<Users, String> email;

}