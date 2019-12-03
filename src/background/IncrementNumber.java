package background;

/**
 * @ClassName IncrementNumber
 * @Author zzrdark
 * @Date 2019-12-03 20:30
 * @Description TODO
 **/
public class IncrementNumber implements Runnable {

    private volatile Integer number;

    public IncrementNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0;i<1000;i++){
            number++;
        }

    }

    public Integer getNumber() {
        return number;
    }

    public static void main(String[] args) {
        IncrementNumber incrementNumber = new IncrementNumber(0);

        Thread thread1 =  new Thread(incrementNumber);
        Thread thread2 =  new Thread(incrementNumber);
        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(incrementNumber.getNumber());


    }
}
