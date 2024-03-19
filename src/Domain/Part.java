package Domain;

public class Part {
    private String name;
    private int quantity;

    public Part(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Part{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +"\n"+
                '}';
    }
}
