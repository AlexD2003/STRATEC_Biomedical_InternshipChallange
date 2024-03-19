package Domain;

public class PartOperation {
    private String machineName;
    private int processingTime;
    private int index;

    public PartOperation(int index,String machineName, int processingTime) {
        this.index = index;
        this.machineName = machineName;
        this.processingTime = processingTime;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "PartOperation{" +
                "machineName='" + machineName + '\'' +
                ", processingTime=" + processingTime +
                ", index=" + index +"\n"+
                '}';
    }
}
