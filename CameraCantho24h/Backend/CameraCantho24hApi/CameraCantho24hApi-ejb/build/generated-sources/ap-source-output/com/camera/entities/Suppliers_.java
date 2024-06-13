package com.camera.entities;

import com.camera.entities.Import;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-13T22:05:37")
@StaticMetamodel(Suppliers.class)
public class Suppliers_ { 

    public static volatile SingularAttribute<Suppliers, String> supplierName;
    public static volatile SingularAttribute<Suppliers, Integer> supplierID;
    public static volatile SingularAttribute<Suppliers, String> phone;
    public static volatile SingularAttribute<Suppliers, String> description;
    public static volatile SingularAttribute<Suppliers, String> email;
    public static volatile CollectionAttribute<Suppliers, Import> importCollection;

}