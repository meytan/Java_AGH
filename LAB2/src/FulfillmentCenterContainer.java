import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.text.TabableView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



class FulfillmentCenterContainer{
    /* TODO addCenter() removeCenter() findEmpty()   summary() */
    Map<String,FulfillmentCenter> fulfillmentCenters = new HashMap<String,FulfillmentCenter>();

    void addCenter(String name,double size){
        if(fulfillmentCenters.containsKey(name)) throw new KeyAlreadyExistsException("This fulfillment center already exists!");
        fulfillmentCenters.put(name,new FulfillmentCenter(name,size));
    }

    void removeCenter(String name){
        if(!fulfillmentCenters.containsKey(name)) throw new IllegalArgumentException("This fulfilment center doesn't exists");
        fulfillmentCenters.remove(name);
    }

    List<FulfillmentCenter> findEmpty(){
        List<FulfillmentCenter> emptyCenters = new ArrayList<FulfillmentCenter>();

        fulfillmentCenters.forEach((k,v) -> {
            if(v.getCurrentMass()==0){
                emptyCenters.add(v);
            }
        });

        return emptyCenters;

    }

    void summary(){
        fulfillmentCenters.forEach((k,v) -> {
            System.out.println("Name: " + k + "\tFulfillment:" + v.getCurrentMass()/v.getMaxMass()*100+"%");
        });
    }
    interface a extends TabableView {
        double dosmt();
    }



}
