package zzr.callable;

import java.util.concurrent.*;

/**
 * @ClassName FutureTaskTest
 * @Author zzrdark
 * @Date 2019-11-29 17:58
 * @Description TODO
 **/
public class FutureTaskTest {
    Callable<String> shanxian = new Callable<String>() {
        @Override
        public String call() throws Exception {
            for (int i = 0; i < 2; i++) {
                System.out.println("闪现技能释放中......");
                Thread.sleep(2000);
            }
            return "闪现技能释放完成";
        }
    };

    Callable<String> yinran = new Callable<String>() {
        @Override
        public String call() throws Exception {
            for (int i = 0; i < 4; i++) {
                System.out.println("引燃技能释放中......");
                Thread.sleep(2000);
            }
            return "引燃技能释放完成";
        }
    };

    public static void main(String[] args) {
        FutureTaskTest futureTaskTest = new FutureTaskTest();
//        ExecutorService es = Executors.newCachedThreadPool();
        ExecutorService es = Executors.newScheduledThreadPool(2);

        FutureTask shanxianTask = new FutureTask<String>(futureTaskTest.shanxian);
        FutureTask yinranTask = new FutureTask<String>(futureTaskTest.yinran);

        es.submit(shanxianTask);
        es.submit(yinranTask);

        try {
            System.out.println(shanxianTask.get());//一直阻塞直到任务完成，即FutureTask中state为normal
            System.out.println(yinranTask.get());//一直阻塞直到任务完成，即FutureTask中state为normal
            System.out.println("击杀对方英雄！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
