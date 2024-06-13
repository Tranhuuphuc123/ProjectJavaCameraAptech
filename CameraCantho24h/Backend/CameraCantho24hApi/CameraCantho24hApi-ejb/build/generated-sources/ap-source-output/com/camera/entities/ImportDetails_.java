package com.camera.entities;

import com.camera.entities.Import;
import com.camera.entities.ImportDetailsPK;
import com.camera.entities.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-13T22:05:37")
@StaticMetamodel(ImportDetails.class)
public class ImportDetails_ { 

    public static volatile SingularAttribute<ImportDetails, Double> unitPrice;
    public static volatile SingularAttribute<ImportDetails, Import> import1;
    public static volatile SingularAttribute<ImportDetails, Integer> quantity;
    public static volatile SingularAttribute<ImportDetails, ImportDetailsPK> importDetailsPK;
    public static volatile SingularAttribute<ImportDetails, Products> products;

}