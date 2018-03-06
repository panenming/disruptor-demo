package cn.icom.demo.basic;

import cn.icom.demo.common.MainTemplate;
import cn.icom.demo.common.generic.GenericEvent;
import cn.icom.demo.common.generic.GenericEventHandler;

import com.lmax.disruptor.dsl.Disruptor;
/**
 * 链式
 * @author panenming
 * @date 2018年3月6日
 * @version 1.0
 */
public class DependenciesChainMain extends MainTemplate {
    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        disruptor.handleEventsWith(new GenericEventHandler<String>("step1"))
                .then(new GenericEventHandler<String>("step2"))
                .then(new GenericEventHandler<String>("step3"));
    }

    public static void main(String[] args) {
        new DependenciesChainMain().run();
    }
}
