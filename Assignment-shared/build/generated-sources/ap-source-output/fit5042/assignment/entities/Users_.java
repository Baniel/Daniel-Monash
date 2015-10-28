package fit5042.assignment.entities;

import fit5042.assignment.entities.Sale;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T15:35:13")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> firstname;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile SingularAttribute<Users, String> type;
    public static volatile SingularAttribute<Users, Integer> userid;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> lastname;
    public static volatile SingularAttribute<Users, String> username;
    public static volatile CollectionAttribute<Users, Sale> saleCollection;

}