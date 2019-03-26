import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.KeyException;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
       try{
           FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();
           fulfillmentCenterContainer.addCenter("Lidl",200);
           fulfillmentCenterContainer.addCenter("Biedronka",200);
           fulfillmentCenterContainer.addCenter("Tesco",200);
           fulfillmentCenterContainer.addCenter("Auchan",200);

           //fulfillmentCenterContainer.removeCenter("center1");
           Item item = new Item("elo",ItemCondition.NEW,1,1);
           Item item3 = new Item("elo",ItemCondition.REFURBISHED,1,30);
           Item item4 = new Item("elo",ItemCondition.REFURBISHED,1,3);
           Item item2 = new Item("aelo1",ItemCondition.NEW,3,12);
            
           fulfillmentCenterContainer.fulfillmentCenters.get("Lidl").addProduct(item3);
           fulfillmentCenterContainer.fulfillmentCenters.get("Lidl").addProduct(item);
           fulfillmentCenterContainer.fulfillmentCenters.get("Lidl").addProduct(item);
           fulfillmentCenterContainer.fulfillmentCenters.get("Lidl").getProduct(item4);
           fulfillmentCenterContainer.fulfillmentCenters.get("Lidl").addProduct(item2);
           fulfillmentCenterContainer.fulfillmentCenters.get("Tesco").addProduct(item2);


           //show(fulfillmentCenterContainer.fulfillmentCenters.get("center2").sortByAmount());
           //fulfillmentCenterContainer.fulfillmentCenters.get("center2").max().print();
           fulfillmentCenterContainer.summary();
           fulfillmentCenterContainer.fulfillmentCenters.get("Lidl").summary();

       }
       catch (IllegalArgumentException e){
           System.out.println(e.getMessage());
       }



    }

    static void show(List<Item> items){
        for(Item i :items){
            System.out.println("###############################################################################");
            i.print();
        }
    }
}
