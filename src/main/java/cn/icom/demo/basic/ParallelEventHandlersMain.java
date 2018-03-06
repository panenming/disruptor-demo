package cn.icom.demo.basic;

import cn.icom.demo.common.MainTemplate;
import cn.icom.demo.common.generic.GenericEvent;
import cn.icom.demo.common.generic.GenericEventHandler;

import com.lmax.disruptor.dsl.Disruptor;

public class ParallelEventHandlersMain extends MainTemplate{
    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        disruptor.handleEventsWith(new GenericEventHandler<String>("step1"),
                new GenericEventHandler<String>("step2")
                , new GenericEventHandler<String>("step3"));
    }

    public static void main(String[] args) {
        new ParallelEventHandlersMain().run();
}
}
