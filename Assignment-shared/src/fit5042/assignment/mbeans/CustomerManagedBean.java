package fit5042.assignment.mbeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fit5042.assignment.entities.Car;
import fit5042.assignment.entities.Sale;
import fit5042.assignment.entities.Users;
import fit5042.assignment.repository.CarSalesRepository;
import fit5042.assignment.repository.CustomerRepository;
import fit5042.assignment.repository.SaleRepository;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped
public class CustomerManagedBean implements Serializable {

    private String userid;
    private String lastname;
    private String firstname;
    private String email;
    private String type;
    private String address;
    private String phone;
    private String username;
    private String password;
    
    
    private int saleid;
    private String date;
    private String bill;
    private String vin;

    @EJB
    private CarSalesRepository carSalesRepository;
    
    @EJB
    private CustomerRepository customerRepository;
    
   
     @EJB
    private SaleRepository saleRepository;

    /**
     * Creates a new instance of CustomerManagedBean
     */
    public CustomerManagedBean() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSaleid() {
        
       List<Sale> sales = new ArrayList<Sale>();
        
        try {
            sales = saleRepository.getAllSale();
        } catch (Exception ex) {
            return saleid = 1;
        }
        
           if ((sales.size()-1) < 0 )
        {
                return saleid = 1;
            
        }
        
        if ((sales.size()-1) >= 0 )
        {
             saleid = sales.get(sales.size()-1).getSaleid() + 1;
            
        }
       
        return  saleid;
       
    }
    
    
      public int setUseridIntoDatabase() {
        
       List<Users> users = new ArrayList<Users>();
       
       int userID = 0;
       
        try {
            users = customerRepository.getAllUsers();
        } catch (Exception ex) {
            
            
            return userID = 1;
        }
        
           if ((users.size()-1) <= 0 )
        {
               
                 userID= 1;
                  this.userid = String.valueOf(userID);
            
        }
        
        if ((users.size()-1) >= 0 )
        {
             
             userID = users.get(users.size()-1).getUserid() + 1;
            this.userid = String.valueOf(userID);
        }
       
        
        
        return  userID ;
       
    }
    
    
    
    

    public void setSaleid(int saleid) {
        this.saleid = saleid;
    }

    public String getDate() {
     Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String datetime = format.format(date);
        return datetime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
    
    
    
    
    public Users searchCustomerByCombination() {

        Users customer = new Users();
        
        int userID = Integer.parseInt(this.userid);
        
        
        try{
               customer = customerRepository.searchCustomerByCombination(userID, lastname, firstname, type, email);
        
        }catch (Exception e)
        {
            
            
            
            customer.setUserid(0000000);
            customer.setLastname("N/A");
            customer.setFirstname("N/A");
            customer.setType("N/A");
            customer.setPhone("N/A");
            customer.setEmail("N/A");
            customer.setAddress("N/A");
            return customer;
            
        }
     
      
        
        return customer;

    }
    
    
    public int getUserIDByUsername()
    {
        
        int userID = 0;
        
            try{
               userID = customerRepository.getByUserID(this.username);
               
        }catch (Exception e)
        {
            
            this.userid = String.valueOf(userID);
            
            return userID;
            
        }
            
        this.userid = String.valueOf(userID);
        
        return userID;
    }
    
    
    public String addSaleIntoDatabase()
    {
       
        Sale sale = new Sale();
        Users user = new Users();
        Car car = new Car();
        
        
        
        int UserID = Integer.parseInt(this.userid);
        
        System.out.println("VIN :" + vin);
        try {
            car = carSalesRepository.searchCarByVIN(vin);
           
        } catch (Exception ex) {
            return "Sorry not found  car!!!";
        }
        
           try {
            user = customerRepository.getUserByUserID(UserID);
        } catch (Exception ex) {
            return "Sorry not found  user!!!";
        }
        
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String datetime = format.format(date);
        
    
       
        
        sale.setSaleid(saleid);
        
        
        sale.setBill(bill);
        
        
        sale.setDate(datetime);
        
        sale.setVin(car);
        sale.setUserid(user);
        
        
        
        Sale saleData = new Sale(sale.getSaleid(),sale.getDate(),sale.getBill(),sale.getVin(),sale.getUserid());
        
       
        
        
        
        try {
            
            
            if(car.getInstock().equals("0"))
            {
                return "Attentino ,the car stock is 0,therefore you maybe can't buy it.Sorry ,Customer,we will offer the kind of car As soon as possible.Your smile is our responsiblity.";
        
                
            }else{
            saleRepository.addSale(saleData);
            car.setInstock("0");
            carSalesRepository.editCar(car);
             return "You hava add a car sucessfully";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "Sorry , we can't add the Car into our Awesome Database!!!";
          
        }
        
       
    }
    
    
    public void clearData()
    {
        this.userid = null;
        this.lastname = null;
        this.firstname = null;
        this.type = null;
        this.email = null;
        
    }
    
    public Users getUserDetail()
    {
        Users user = new Users();
        
        user = customerRepository.getUserByUserName(username);
         
        if (user == null)
        {
            System.out.println("No user!!!");
            
            
        }
        
        return user; 
    }
    
   
    
    public String getUserName()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        
      
        
        String userName = request.getRemoteUser();
        
       this.username = userName ;
        
        System.out.println(username);
        
        return userName;
        
       
    }
    
    
    public String logout()
    {
       
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        
        String userName = request.getRemoteUser();
        
        
        
        try {
            System.out.println(userName + " is logout");
            request.logout();
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
    
       return "../index.xhtml";
    }

    
    
       public String updateUserDetail() {

       
        
        int userID = Integer.parseInt(this.userid);
        try{
            
            System.out.println("Initial user");
            Users user = customerRepository.getUserByUserID(userID);
            
            System.out.println("create user");
            user.setLastname(lastname);
            user.setFirstname(firstname);
            user.setEmail(email);
//            user.setType(type);
//            user.setAddress(address);
//            user.setPhone(phone);
//            user.setUsername(username);
//            user.setPassword(password);
            
            System.out.println("Initial edit");
            customerRepository.editUser(user);
            System.out.println("finish edit");
            return "You have changed the User Detail Successfully";
        }catch (Exception ex)
        {
            return "Sorry,update is failed!!!";
        }

    }
       
       
        public List<String> returnUserType()
    {
        List<String> userType = new ArrayList<String>();

        userType.add("customer");
        userType.add("salesperson");
        
        return userType;
    }
       
 
  
       
       public String deleteUser() 
       {
           int userID = Integer.parseInt(this.userid);
           
           try{
               Users user = customerRepository.getUserByUserID(userID);
               
               if (user != null)
               {
                   customerRepository.removerUser(userID);
               }
               
               return "You have Delete car successfully!!!";
               
           }catch (Exception ex)
           {
            return "Something worng with delete car!!!"   ;
           }
       }
    
    public String addUserBySalesperson()
    {
        try{
            Users user = new Users();
            System.out.println("create user");
            
            int userID = Integer.parseInt(this.userid);
            System.out.println(userID);
            
            user.setUserid(userID);
            System.out.println("UserID :" + userID );
            
            user.setLastname(lastname);
             System.out.println();
            user.setFirstname(firstname);
             System.out.println();
            user.setEmail(email);
             System.out.println();
            user.setType(type);
             System.out.println();
            user.setAddress(address);
             System.out.println();
            user.setPhone(phone);
             System.out.println();
            user.setUsername(username);
             System.out.println();
             
             
             
           String EncrptPassword =  SHA256Encrypt.bin2hex(password).toLowerCase();
             
             
             
            user.setPassword(EncrptPassword);
             System.out.println();
            customerRepository.addUser(user);
            
            return "You hava add a user Successfully";
            
        }catch(Exception ex)
        {
            return "Sorry,add a user is falied!!!";
        }
    }
}




