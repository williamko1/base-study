中介者(mediator)模式

场景： 
 1. 三个模块
 2. 模块1要调用模块2和3，模块2要调用模块1和3，模块3调用模块1和2


这个模式就是要让各个系统之间彻底解耦，不要相互强耦合在一起，互相调用过多，调用关系紊乱。互相调用通过一个中间组件来解耦

在实际的企业开发中，不是这么玩的，很少有封装一个所谓的中介者，让各个模块之间解耦

最常见的一个方式，系统与系统之间，不走接口，而是基于mq来解耦。


模块A将消息发送到一个内存队列中，其他的模块去内存中消费自己感兴趣的消息，来执行对应的操作，用队列地替代了中介者，让各个模块解耦