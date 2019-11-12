package thread;

public class SellTicket implements Runnable {
    private byte[] lock = new byte[0]; // 特殊的instance变量
    private int totalTickets = 100;
    @Override
    public void run() {
        while (totalTickets > 0){

            synchronized (this) {
                notify();
                if (totalTickets <= 0) {
                    break;
                }
                totalTickets--;
                System.out.println(Thread.currentThread().getName() + "卖出了一张票,还剩下:" + totalTickets + "张");
                if (totalTickets >0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //region Description 这是一段写着试试的代码
        SellTicket sellTicket = new SellTicket();
        Thread thread1 = new Thread(sellTicket,"窗口1");
        Thread thread2 = new Thread(sellTicket,"窗口2");
        Thread thread3 = new Thread(sellTicket,"窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
        //endregion

    }
}
