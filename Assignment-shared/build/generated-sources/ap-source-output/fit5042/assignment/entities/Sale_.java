package fit5042.assignment.entities;

import fit5042.assignment.entities.Car;
import fit5042.assignment.entities.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T15:35:13")
@StaticMetamodel(Sale.class)
public class Sale_ { 

    public static volatile SingularAttribute<Sale, String> date;
    public static volatile SingularAttribute<Sale, Integer> saleid;
    public static volatile SingularAttribute<Sale, String> bill;
    public static volatile SingularAttribute<Sale, Car> vin;
    public static volatile SingularAttribute<Sale, Users> userid;

}