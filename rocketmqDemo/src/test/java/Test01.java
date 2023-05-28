import cn.hutool.core.lang.Console;
import com.junius.Application;
import com.junius.constant.RocketMq;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author junius
 * @date 2023/05/11 14:20
 * @project codeHub
 *
 **/
@SpringBootTest(classes = Application.class)
public class Test01 {
    /*
    生产者代码案例
     */
    @Test
    public void test1() throws Exception {
        //创建生产者，并分配组名
        DefaultMQProducer producer = new DefaultMQProducer("test-producer-group1");
        producer.setRetryTimesWhenSendFailed(2);
        //连接nameserver
        producer.setNamesrvAddr("1.13.197.192:9876");
        //启动
        producer.start();
        //创建消息
        Message message = new Message("Test1Topic","878226936".getBytes());
        //发送消息
        SendResult send = producer.send(message,10000);
        Console.log(send);
        //关闭生产者
        producer.shutdown();
    }

    /*
    消费者代码案例
     */
    @Test
    public void test2() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer-group1");
        consumer.setNamesrvAddr("1.13.197.192:9876");
        consumer.subscribe("Test1Topic","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                Console.log(list);
                Console.log(new String(list.get(0).getBody()));
                Console.log(consumeConcurrentlyContext);
                //成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        //挂起当前的jvm
        System.in.read();
    }

}
