package jmm;

/**
 * 描述：     演示可见性带来的问题
 */
public class FieldVisibilityABCD {

    int a = 1;
    int b = 2;
    int c = 2;
    int d = 2;

    private void change() {
        a = 3;
        b = 4;
        c = 5;
        synchronized (this) {
            d = 6;
        }
    }


    private void print() {
        int aa;
        synchronized (this) {
            aa = a;
        }
        int bb = b;
        int cc = c;
        int dd = d;

        System.out.println("aa=" + aa + ";bb=" + bb + ";cc=" + cc + ";dd=" + dd);
    }

    public static void main(String[] args) {
            FieldVisibilityABCD test = new FieldVisibilityABCD();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();

    }


}
