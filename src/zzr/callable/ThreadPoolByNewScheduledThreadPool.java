package zzr.callable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolByNewScheduledThreadPool
 * @Author zzrdark
 * @Date 2019-11-29 18:41
 * @Description TODO
 **/
public class ThreadPoolByNewScheduledThreadPool {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 延迟3秒");
            }
        });

        /**
         * 定长线程池，支持定时及周期性任务执行
         */
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        //延迟3s后运行
        scheduledThreadPool.schedule(thread, 3, TimeUnit.SECONDS);

        //首次执行延迟1s，每次间隔3秒
        //scheduledThreadPool.scheduleAtFixedRate(thread, 1, 3, TimeUnit.SECONDS);

        //每次执行结束，已固定时延开启下次执行
        //scheduledThreadPool.scheduleWithFixedDelay(thread, 1, 3, TimeUnit.SECONDS);

        System.out.println(Thread.currentThread().getName() + " : main thread");
        scheduledThreadPool.shutdown();
//		try {
//			Thread.sleep(12000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		scheduledThreadPool.shutdownNow();
    }

}
