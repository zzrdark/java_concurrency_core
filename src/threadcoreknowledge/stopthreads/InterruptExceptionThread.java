package threadcoreknowledge.stopthreads;

/**
 * @ClassName InterruptThread
 * @Author zzrdark
 * @Date 2019-12-02 16:46
 * @Description TODO
 **/
public class InterruptExceptionThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("进入线程");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程退出");
        }
    }


    public static void main(String[] args) {
        InterruptExceptionThread interruptThread = new InterruptExceptionThread();

        Thread thread = new Thread(interruptThread);
        thread.start();
        thread.interrupt();

    }
}
