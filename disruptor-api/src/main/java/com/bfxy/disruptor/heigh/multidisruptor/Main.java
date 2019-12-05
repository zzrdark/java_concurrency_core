package com.bfxy.disruptor.heigh.multidisruptor;


import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @ClassName Main
 * @Author zzrdark
 * @Date 2019-12-05 15:10
 * @Description TODO
 **/
public class Main {
    public static void main(String[] args) {


        Disruptor disruptor = new Disruptor(new EventFactory() {
            @Override
            public Object newInstance() {
                return new Order();
            }
        },
                4
                , Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
                , ProducerType.SINGLE
                ,new YieldingWaitStrategy()
                );

        Consumer[] consumers = new Consumer[6];
        for(int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("C" + i);
        }

        disruptor.handleEventsWithWorkerPool(consumers);
        disruptor.start();

        final CountDownLatch latch = new CountDownLatch(1);

        for(int i = 0; i < 1; i++) {
            final Producer producer = new Producer(disruptor.getRingBuffer());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for(int j = 0; j<21; j++) {
                        producer.sendData(UUID.randomUUID().toString());
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("----------线程创建完毕，开始生产数据----------");
        latch.countDown();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("任务总数:" + consumers[2].getCount());

    }
}
