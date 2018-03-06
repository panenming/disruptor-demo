package cn.icom.demo.minitor;

import java.util.Timer;
import java.util.TimerTask;

import cn.icom.demo.common.MainTemplate;
import cn.icom.demo.common.generic.GenericEvent;
import cn.icom.demo.common.generic.GenericSleepEventHandler;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class MonitorDisruptorMain extends MainTemplate {
    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        disruptor.handleEventsWith(new GenericSleepEventHandler<String>(
                "handler-1"));
    }

    @Override
    protected void doAfterDisruptorStart(
            final RingBuffer<GenericEvent<String>> ringBuffer) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("剩余坑位:" + ringBuffer.remainingCapacity());
            }
        }, 1000, 2000);
    }

    public static void main(String[] args) {
        new MonitorDisruptorMain().run();
    }
}
