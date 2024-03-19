package Domain;

public class Machine {
    private String name;
    private String capacity;
    private int cooldown;

    public Machine(String name, String capacity, int cooldown) {
        this.name = name;
        this.capacity = capacity;
        this.cooldown = cooldown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", cooldown=" + cooldown +"\n"+
                '}';
    }
}