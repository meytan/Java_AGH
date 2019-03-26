import java.util.*;

class FulfillmentCenter {
    private String name;
    private List<Item> products = new ArrayList<Item>();
    private double maxMass;
    private double currentMass;



    FulfillmentCenter(String name, double maxMass) {
        this.name = name;
        this.maxMass = maxMass;
        this.currentMass=0;
    }

    private Item findItem(Item searchedItem){
        //TODO change find to search and start to use compareTo instead of stream

        //TODO add also searchPartial


         if(products.stream().filter(item -> item.name.equals(searchedItem.name)).anyMatch(item -> item.state.equals(searchedItem.state))){
             return products.stream().filter(item -> item.name.equals(searchedItem.name) && item.state.equals(searchedItem.state)).findFirst().get();
         }
         else {
             return null;
         }

    }

    void addProduct(Item item){
        if(currentMass+item.getMass()*item.getCount() > maxMass) throw new IllegalArgumentException("There is no free space in this Fulfillment Center.");

        currentMass+=(item.getMass()*item.getCount());
        if(findItem(item)!=null){
            findItem(item).addCount(item.getCount());
        }
        else {
            try {
                products.add((Item) item.clone());
            }
            catch (CloneNotSupportedException e){

            }
        }

    }
    void getProduct(Item item){
        if(findItem(item)==null) throw new IllegalArgumentException("This Fulfillment Center has not such an item.");

        currentMass-=findItem(item).getMass();

        if(findItem(item).getCount()==1){
            products.remove(findItem(item));
        }
        else {
            findItem(item).addCount(-1);
        }


    }
    void removeProduct(Item item){
        if(findItem(item)==null) throw new IllegalArgumentException("This Fulfillment Center has not such an item.");
        else {
            currentMass-=findItem(item).getMass()*findItem(item).getCount();
            products.remove(findItem(item));
        }


    }

    void summary(){
        System.out.println(name +"\t Current mass: "+currentMass+"\t Max mass: "+maxMass);
        for(Item item : products){
            System.out.println("###############################################################################");
            item.print();
        }
    }

    //TODO countByCondition(ItemCondition) summary() sortByName() sortByAmount() max()

    Item search(String name){
        for (Item item : products){
            if(item.name.compareTo(name)==0){
                return item;
            }
        }
        throw new IllegalArgumentException("There is no such an item in this fulfillment center");
    }

    List<Item> searchPartial(String name){
        List<Item> list = new ArrayList<Item>();
        for (Item item : products){
            if(item.name.contains(name)){
                list.add(item);
            }
        }
        if(list.isEmpty() )throw new IllegalArgumentException("There is no any item that contains this string  in this fulfillment center");
        return list;
    }

    int countByCondition(ItemCondition itemCondition){
        int itemsCount=0;
        for (Item item : products){
            if(item.state==itemCondition){
                itemsCount+=item.getCount();
            }
        }

        //if(itemsCount==0) throw new IllegalArgumentException("There is no item in such state");
        return itemsCount;
    }

    List<Item> sortByName(){

        List<Item> items = new ArrayList<Item>(products);

        Collections.sort(items);

        return items;
    }

    List<Item> sortByAmount(){

        List<Item> items = new ArrayList<Item>(products);

        Collections.sort(items,(o1, o2) -> {
            return Double.compare(o2.count*o2.getMass(), o1.count*o1.getMass());});

        return items;
    }

    Item max(){

        return Collections.max(products, Comparator.comparingDouble(o -> o.count * o.getMass())
        );

    }

    double getCurrentMass() {
        return currentMass;
    }

    double getMaxMass() {
        return maxMass;
    }
}
