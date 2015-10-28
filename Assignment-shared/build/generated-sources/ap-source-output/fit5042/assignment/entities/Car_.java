package fit5042.assignment.entities;

import fit5042.assignment.entities.Sale;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T15:35:13")
@StaticMetamodel(Car.class)
public class Car_ { 

    public static volatile SingularAttribute<Car, String> thumbnail;
    public static volatile SingularAttribute<Car, String> colour;
    public static volatile SingularAttribute<Car, String> previewurl;
    public static volatile SingularAttribute<Car, String> price;
    public static volatile SingularAttribute<Car, String> modelname;
    public static volatile SingularAttribute<Car, String> description;
    public static volatile SingularAttribute<Car, String> vin;
    public static volatile SingularAttribute<Car, String> modelno;
    public static volatile SingularAttribute<Car, String> type;
    public static volatile SingularAttribute<Car, String> make;
    public static volatile SingularAttribute<Car, String> instock;
    public static volatile CollectionAttribute<Car, Sale> saleCollection;

}