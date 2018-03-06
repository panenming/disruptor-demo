package cn.icom.demo.common.Long;

public class LongEvent {
    public LongEvent() {
        System.out.println("build longevent..");
    }

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
