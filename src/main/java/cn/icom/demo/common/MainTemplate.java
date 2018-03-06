package cn.icom.demo.common;

import java.util.Scanner;
import java.util.concurrent.Executors;

import cn.icom.demo.common.generic.GenericEvent;
import cn.icom.demo.common.generic.GenericEventFactory;
import cn.icom.demo.common.generic.GenericEventProducer;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * 提供公用的模板，用于控制台消息输入
 * 
 * @author panenming
 * @date 2018年3月6日
 * @version 1.0
 */
public abstract class MainTemplate {
    @SuppressWarnings({ "unchecked", "resource", "rawtypes" })
    public void run() {
        GenericEventFactory<GenericEvent<String>> eventFactory = new GenericEventFactory<GenericEvent<String>>();
        int bufferSize = 4;
        Disruptor<GenericEvent<String>> disruptor = new Disruptor(eventFactory,
                bufferSize, Executors.defaultThreadFactory());
        addHandler(disruptor);
        disruptor.start();
        RingBuffer<GenericEvent<String>> ringBuffer = disruptor.getRingBuffer();
        doAfterDisruptorStart(ringBuffer);
        GenericEventProducer<String> producer = new GenericEventProducer(
                ringBuffer);
        for (;;) {
            Scanner scan = new Scanner(System.in);
            String msg = scan.nextLine();
            if ("exit".equals(msg)) {
                System.exit(0);
            }
            producer.onData(msg);
        }
    }

    protected void doAfterDisruptorStart(
            final RingBuffer<GenericEvent<String>> ringBuffer) {
    }

    public abstract void addHandler(Disruptor<GenericEvent<String>> disruptor);
}
