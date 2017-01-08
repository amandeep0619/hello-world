import java.util.Comparator;


public class Item implements Comparable<Item>{

    private long index;
    private double weight;
    private double cost;
    
    
    public Item(long index, double weight, double cost)
    {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }
    public long getIndex() {
        return index;
    }



    public void setIndex(long index) {
        this.index = index;
    }



    public double getWeight() {
        return weight;
    }



    public void setWeight(double weight) {
        this.weight = weight;
    }



    public double getCost() {
        return cost;
    }



    public void setCost(double cost) {
        this.cost = cost;
    }
    public static Comparator<Item> ItemCostCompartor = new Comparator<Item>() {

        @Override
        public int compare(Item o1, Item o2) {
            // TODO Auto-generated method stub
            return Double.compare(o1.cost, o2.cost);
        }
        
    };
    
    public static Comparator<Item> ItemWeightCompartor = new Comparator<Item>() {

        @Override
        public int compare(Item o1, Item o2) {
            // TODO Auto-generated method stub
            return Double.compare(o1.weight, o2.weight);
        }
        
    };
    
    @Override
    public int compareTo(Item o) {
        // TODO Auto-generated method stub
        return Double.compare(this.weight, o.weight);
    }

}
