package cn.icom.demo.basic;

import cn.icom.demo.common.MainTemplate;
import cn.icom.demo.common.generic.GenericEvent;
import cn.icom.demo.common.generic.GenericEventModHandler;

import com.lmax.disruptor.dsl.Disruptor;

public class HandleOnce extends MainTemplate {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        // 批量构建一批handler
        int handlerCounts = 10;
        GenericEventModHandler[] handlers = new GenericEventModHandler[handlerCounts];
        for (int i = 0; i < handlerCounts; i++) {
            handlers[i] = new GenericEventModHandler("[handler-" + i + "]", i,
                    handlerCounts);
        }
        disruptor.handleEventsWith(handlers);
    }

    public static void main(String[] args) {
        new HandleOnce().run();
    }
}
