package singleton;

/**
 * 描述：     双重检查（推荐面试使用）
 */
public class Singleton6 {

    private  static Singleton6 instance;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                    System.out.println("创建成功");
                }
            }
        }
        System.out.println("返回成功");
        return instance;
    }

    public static void main(String[] args) {

        for(int i = 0 ;i<10;i++){

            Thread t1 = new Thread(()->{
                Singleton6.getInstance();
            });
            t1.start();
        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
