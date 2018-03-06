package cn.icom.demo.common.Long;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

/**
 * 发布事件
 * 
 * @author panenming
 * @date 2018年3月6日
 * @version 1.0
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long seq = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(seq);
            event.setValue(bb.getLong(0));
        } finally {
            ringBuffer.publish(seq);
        }
    }
}
