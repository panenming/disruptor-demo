package cn.icom.demo.common.generic;

import com.lmax.disruptor.WorkHandler;

public class GenericWorkHandler<T> implements WorkHandler<GenericEvent<T>> {
    private String name;

    public GenericWorkHandler(String name) {
        this.name = name;
    }

    public void onEvent(GenericEvent<T> event) throws Exception {
        System.out.println("消费者workHandler(" + name + ")" + ":" + event.get()
            + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }
}
