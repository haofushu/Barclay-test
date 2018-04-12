public class Inventory {
    private String name;
    private double bought_at;
    private double sold_at;
    private int qty;
    private double value;


    public Inventory(String name, double bought_at,double sold_at){
        this.name = name;
        this.bought_at = bought_at;
        this.sold_at = sold_at;
    }

    public String getName() {
        return name;
    }

    public double getBought_at() {
        return bought_at;
    }

    public double getSold_at() {
        return sold_at;
    }

    public void setSold_at(double sold_at) {
        this.sold_at = sold_at;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getValue() {
        setValue();
        return value;
    }

    private void setValue( ) {
        this.value = this.qty * this.bought_at;
    }
}
