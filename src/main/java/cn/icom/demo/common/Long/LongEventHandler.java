package cn.icom.demo.common.Long;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    private String handlerName;

    public LongEventHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
            throws Exception {
        System.out.println("消费者Event(" + handlerName + "):"
            + Thread.currentThread().getName() + " " + event.hashCode() + ":"
            + event.getValue());
    }
}
