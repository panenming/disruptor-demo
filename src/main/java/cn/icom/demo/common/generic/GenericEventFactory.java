package cn.icom.demo.common.generic;

import com.lmax.disruptor.EventFactory;

public class GenericEventFactory<T> implements EventFactory<GenericEvent<T>> {
    public GenericEvent<T> newInstance() {
        return new GenericEvent<T>();
    }
}
