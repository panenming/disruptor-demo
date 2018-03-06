## disruptor可以简单的看做是内存MQ(内存消息队列)
## 没有顺序，并行执行
## 有先后依赖顺序执行
## 菱形顺序
## 链式依赖顺序
## 异常需要设置setDefaultExceptionHandler，要不然处理器抛出异常后，就不会再处理事件了。
## 监控disruptor的负载
	调用ringBuffer.remainingCapacity()查看还有多少没使用