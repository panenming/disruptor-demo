package cn.icom.demo.common.generic;

import com.lmax.disruptor.EventHandler;

public class GenericEventHandler<T> implements EventHandler<GenericEvent<T>> {
    String name;

    public GenericEventHandler() {
    }

    public GenericEventHandler(String name) {
        this.name = name;
    }

    @Override
    public void
            onEvent(GenericEvent<T> event, long sequence, boolean endOfBatch)
                    throws Exception {
        System.out.println("消费者EventHandler(" + name + ")" + ":" + event.get()
            + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }
}
