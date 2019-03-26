public class Item implements Comparable<Item>,Cloneable{
    String name;
    ItemCondition state;
    private double mass;
    int count;



    Item(String name, ItemCondition state, double mass, int count){

        this.name=name;
        this.state=state;
        this.mass=mass;
        this.count=count;

    }

    void print(){
        System.out.println("Name:\t"+name);
        System.out.println("State:\t"+state);
        System.out.println("Mass:\t"+mass);
        System.out.println("Count:\t"+count);
    }

    double getMass() {
        return mass;
    }

    void setMass(double mass) {
        this.mass = mass;
    }

    void addMass(double mass) {
        this.mass += mass;
    }

    int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }

    void addCount(int count) {
        this.count += count;
    }

    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return  super.clone();
    }

}
