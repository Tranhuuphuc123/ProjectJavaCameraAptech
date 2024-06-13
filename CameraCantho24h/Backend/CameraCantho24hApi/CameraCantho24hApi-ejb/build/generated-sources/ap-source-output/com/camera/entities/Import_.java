package com.camera.entities;

import com.camera.entities.ImportDetails;
import com.camera.entities.Suppliers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-13T22:05:37")
@StaticMetamodel(Import.class)
public class Import_ { 

    public static volatile SingularAttribute<Import, Integer> importID;
    public static volatile SingularAttribute<Import, Suppliers> supplierID;
    public static volatile SingularAttribute<Import, Date> importDate;
    public static volatile CollectionAttribute<Import, ImportDetails> importDetailsCollection;

}