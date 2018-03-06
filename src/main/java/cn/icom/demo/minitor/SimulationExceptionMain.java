package cn.icom.demo.minitor;

import cn.icom.demo.common.MainTemplate;
import cn.icom.demo.common.generic.GenericEvent;
import cn.icom.demo.common.generic.GenericExceptionEventHandler;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.dsl.Disruptor;

public class SimulationExceptionMain extends MainTemplate {
    @SuppressWarnings("unchecked")
    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        disruptor.handleEventsWith(new GenericExceptionEventHandler<String>(
                "handler-1"));
        // 必须设置一个默认的异常处理handler不然，disruptor会停止！！！
        disruptor.setDefaultExceptionHandler(new SimpleExceptionHandler());
    }

    public static void main(String[] args) {
        new SimulationExceptionMain().run();
    }

    @SuppressWarnings("rawtypes")
    private static class SimpleExceptionHandler implements ExceptionHandler {
        public void handleEventException(Throwable ex, long sequence,
                Object event) {
            System.err.println("处理发生异常:" + event);
        }

        public void handleOnStartException(Throwable ex) {
            System.err.println("启动发生异常:" + ex.getMessage());
            ex.printStackTrace();
        }

        public void handleOnShutdownException(Throwable ex) {
            System.err.println("停止发生异常:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
