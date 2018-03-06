package cn.icom.demo.basic;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.icom.demo.common.Long.LongEvent;
import cn.icom.demo.common.Long.LongEventFactory;
import cn.icom.demo.common.Long.LongEventHandler;
import cn.icom.demo.common.Long.LongEventProducer;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class BasicMain {
    public static void main(String[] args) {
        // 生产event的工厂，在disruptor启动的时候用来在ring buffer环中占坑
        LongEventFactory factory = new LongEventFactory();
        // 指定ring buffer环的大小
        int bufferSize = 8;
        // 创建disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,
                bufferSize, Executors.defaultThreadFactory());
        // 注册事件处理器，用来消费事件
        disruptor.handleEventsWith(new LongEventHandler("Basic"));
        // 启动disruptor
        disruptor.start();
        // 构建生产者，用来生产事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        final LongEventProducer producer = new LongEventProducer(ringBuffer);
        ExecutorService es = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            es.submit(new Runnable() {
                public void run() {
                    ByteBuffer bb = ByteBuffer.allocate(8);
                    for (long l = 0; l < 20; l++) {
                        bb.putLong(0, l);
                        producer.onData(bb);
                    }
                }
            });
        }
        System.out.println("end.....");
    }
}
