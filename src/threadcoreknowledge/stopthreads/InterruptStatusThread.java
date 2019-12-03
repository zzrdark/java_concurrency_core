package threadcoreknowledge.stopthreads;

/**
 * @ClassName InterruptStatusThread
 * @Author zzrdark
 * @Date 2019-12-02 16:59
 * @Description TODO
 **/
public class InterruptStatusThread implements Runnable{


    @Override
    public void run() {
        for(;;){
            System.out.println("进入线程");
            if (Thread.interrupted()){
                System.out.println("进入中断，退出");
                break;
            }

        }
    }

    public static void main(String[] args) {
        InterruptStatusThread interruptStatusThread = new InterruptStatusThread();

        Thread thread = new Thread(interruptStatusThread);
        thread.start();
        thread.interrupt();
    }
}
