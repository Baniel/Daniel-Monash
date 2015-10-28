package fit5042.assignment.repository;

import fit5042.assignment.entities.Car;
import java.util.List;
import javax.ejb.Remote;
import static org.eclipse.persistence.sessions.SessionProfiler.Remote;

/**
 *
 * @author daniel
 */
@Remote
public interface CarSalesRepository {

    public void setVIN(String VIN);

    public String getVIN();

    public void setModelNO(String modelNO);

    public String getModelNo();

    public void setModelName(String modelName);

    public String getModelName();

    public String getMake();

    public void setMake(String make);

    public String getType();

    public void setType(String type);
    
    public void setThumbnial(String thumbnial);
    
    public String getThumbnial();
    
    public void setDescription(String description);
    
    public String getDescription();
            
    public void setColour(String colour);
    
    public String getColour();
    
    public void setInstock(String instock);
    
    public String getInstock();
            
            
            
            
            
            

    public void addCar(Car car) throws Exception;

    public Car searchCarByVIN(String vin) throws Exception;

    //Search Function
    public List<Car> SearchCarByMake(String make) throws Exception;

    public String SearchCarByMake() throws Exception;

    public List<Car> SearchCarByModelNo(String modelNo) throws Exception;

    public List<Car> SearchCarByModelName(String modelName) throws Exception;

    public Car SearchSingleCarByModelName(String modelName) throws Exception;

    public List<Car> SearchCarByType(String type) throws Exception;

    public List<Car> getAllCars() throws Exception;

    public void removeCar(String vin) throws Exception;

    public void editCar(Car car) throws Exception;

    public String toResult() throws Exception;

}
