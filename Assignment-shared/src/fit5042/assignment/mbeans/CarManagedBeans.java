/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.mbeans;

import com.restful.CarRestfulService;
import fit5042.assignment.entities.Car;
import fit5042.assignment.repository.CarSalesRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped
public class CarManagedBeans implements Serializable {

    private String VIN;
    private String modelNo;
    private String modelName;
    private String make;
    private String type;
    private String thumbnail;
    private String description;
    private String previewURL;
    private String colour;
    private String instock;
    private String price;

    CarRestfulService restful;

    @EJB
    private CarSalesRepository carSalesRepository;

    /**
     * Creates a new instance of CarManagedBeans
     */
    public CarManagedBeans() {
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getInstock() {
        return instock;
    }

    public void setInstock(String instock) {
        this.instock = instock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Car searchCarByVIN() {
        try {
            Car car = carSalesRepository.searchCarByVIN(this.VIN);
            return car;
        } catch (Exception ex) {
            Logger.getLogger(CarManagedBeans.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Car> searchCar() throws Exception {

        List<Car> cars = new ArrayList<Car>();

        if (!this.make.trim().isEmpty() && this.make != "") {
            cars = carSalesRepository.SearchCarByMake(this.make);
            return cars;
        }

        if (!this.modelNo.trim().isEmpty() && this.modelNo != "") {
            cars = carSalesRepository.SearchCarByModelNo(this.modelNo);
            return cars;
        }

        if (!this.modelName.trim().isEmpty() && this.modelName != "") {
            cars = carSalesRepository.SearchCarByModelName(this.modelName);
            return cars;
        }

        if (!this.modelName.trim().isEmpty() && this.type != "") {
            cars = carSalesRepository.SearchCarByType(this.type);
            return cars;
        }

        return carSalesRepository.getAllCars();

    }

    public List<String> searchCarVINString() throws Exception {

        List<Car> cars = new ArrayList<Car>();
        List<String> carVIN = new ArrayList<String>();

        if (!this.make.trim().isEmpty() && this.make != "") {
            cars = carSalesRepository.SearchCarByMake(this.make);

            for (int i = 0; i < cars.size(); i++) {
                carVIN.add(cars.get(i).getVin());
            }

            return carVIN;

        }

        if (!this.modelNo.trim().isEmpty() && this.modelNo != "") {
            cars = carSalesRepository.SearchCarByModelNo(this.modelNo);

            for (int i = 0; i < cars.size(); i++) {
                carVIN.add(cars.get(i).getVin());
            }

            return carVIN;

        }

        if (!this.modelName.trim().isEmpty() && this.modelName != "") {
            cars = carSalesRepository.SearchCarByModelName(this.modelName);

            for (int i = 0; i < cars.size(); i++) {
                carVIN.add(cars.get(i).getVin());
            }

            return carVIN;

        }

        if (!this.modelName.trim().isEmpty() && this.type != "") {
            cars = carSalesRepository.SearchCarByType(this.type);

            for (int i = 0; i < cars.size(); i++) {
                carVIN.add(cars.get(i).getVin());
            }

            return carVIN;
        }

        return null;

    }

    public List<Car> searchCarFullDetails() throws Exception {
        List<Car> cars = new ArrayList<Car>();

        if (!this.make.trim().isEmpty() && this.make != "") {
            cars = carSalesRepository.SearchCarByMake(this.make);
            return cars;
        }

        if (!this.modelName.trim().isEmpty() && this.modelName != "") {
            cars = carSalesRepository.SearchCarByModelName(this.modelName);
            return cars;
        }

        return carSalesRepository.getAllCars();
    }

    public List<Car> searchCarByMake() throws Exception {
        List<Car> cars = new ArrayList<Car>();

        try {
            cars = carSalesRepository.SearchCarByMake(this.make);
        } catch (Exception ex) {

            return null;
        }

        return cars;

    }

    public List<Car> viewCar() throws Exception {

        return carSalesRepository.getAllCars();

    }

    public String addCarBySalesperson() {
        try {
            Car car = new Car();

            car.setVin(this.VIN);
            car.setModelno(this.modelNo);
            car.setModelname(this.modelName);
            car.setMake(this.make);
            car.setType(this.type);
            car.setThumbnail(this.thumbnail);
            car.setDescription(this.description);
            car.setColour(this.colour);

            carSalesRepository.addCar(car);
            

            return "You have add a car successfully";

        } catch (Exception ex) {
            return "Sorry,add a car is failed!!!";

        }
    }

    public String deleteCarBySalesperson() throws Exception {
        Car car = carSalesRepository.searchCarByVIN(this.VIN);

        if (car != null) {
            carSalesRepository.removeCar(this.VIN);
            return " You have deleted car sucessfully";
        }

        return "Sorry, we have not found your car!!!";
    }

    public String updatePrice() {

        try {

            Car car = carSalesRepository.searchCarByVIN(this.VIN);
            car.setPrice(this.price);
            carSalesRepository.editCar(car);
            return "You have chabed a new price";

        } catch (Exception ex) {
            return "Sorry,update is failed!!!!";
        }

    }

    public List<Car> getCarByRestful() {
        restful = new CarRestfulService();

        List<Car> cars = new ArrayList<Car>();

        cars = restful.getCarByMake(this.make);

        if (cars == null) {
            return null;
        }

        return cars;
    }

    public List<String> getMakeStringByRestful() {
        restful = new CarRestfulService();

        List<Car> cars = new ArrayList<Car>();
        List<String> carMakes = new ArrayList<String>();

        cars = restful.getAllCarByRestful();

        
        int carCount =  cars.size();
        
        for (int i = 0; i < cars.size(); i++) {
           
            if (i == 0) {
                carMakes.add(cars.get(0).getMake());
            }
            
            if (i > 0)
            {
                for ( int j = 0 ; j <  carMakes.size() ; j ++)
                {
                    
                    if ( !cars.get(i).getMake().toString().trim().equals(carMakes.get(j).toString().trim()))  
                    {
                            carMakes.add(cars.get(i).getMake());
                    }
                    
                    
                }
            }    

        }

        return carMakes;
    }

    public List<String> getVINStringByLocatDatabase() {
        List<Car> cars = new ArrayList<Car>();
        List<String> carVIN = new ArrayList<String>();

        try {
            cars = carSalesRepository.getAllCars();
        } catch (Exception ex) {
            Logger.getLogger(CarManagedBeans.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < cars.size(); i++) {
            carVIN.add(cars.get(i).getVin());
        }

        return carVIN;
    }

    public List<String> getVINStringByRestful() {
        restful = new CarRestfulService();

        List<Car> cars = new ArrayList<Car>();
        List<String> carVIN = new ArrayList<String>();

        cars = restful.getCarByMake(this.make);

        for (int i = 0; i < cars.size(); i++) {
            carVIN.add(cars.get(i).getVin());
        }

        return carVIN;
    }

    public String addCarIntoDatabase() {
        restful = new CarRestfulService();
        Car car = new Car();

        car = restful.getCarByVIN(this.VIN);

        try {
            System.out.println("init insertj car");
            carSalesRepository.addCar(car);
            System.out.println("Finish car");
            return "You have add a car successfully";
        } catch (Exception ex) {
            return "Sorry , The car has already in database!!!";
        }

    }

    public static void clear() throws Exception {
        CarManagedBeans carBean = new CarManagedBeans();

        carBean.setVIN("");
        carBean.setModelNo("");
        carBean.setModelName("");
        carBean.setMake("");;
        carBean.setType("");
        carBean.setThumbnail("");
        carBean.setDescription("");
        carBean.setColour("");
        carBean.setPreviewURL("");
        carBean.setInstock("");
    }
    
    public List<String> returnCarType()
    {
        List<String> carType = new ArrayList<String>();

        carType.add("Sedan");
        carType.add("4 Wheel drive");
        carType.add("Truck");
        
        return carType;
    }
    
    
    

}
