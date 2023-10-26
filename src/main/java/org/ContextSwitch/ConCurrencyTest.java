package org.ContextSwitch;

public class ConCurrencyTest {
    private static final long count = 100001;

//    这段代码用于比较并发执行（在新线程中）和串行执行（在主线程中）对程序执行时间和结果的影响。
//    通常情况下，由于并发执行允许多个任务同时执行，所以它在性能上可能更高效，但也需要处理并发问题。

    public static void main(String[] args) throws InterruptedException{
//        这一行记录了程序的开始时间
        long start = System.currentTimeMillis();

//        这里创建了一个新线程，其中定义了一个匿名的 Runnable 接口实现。这个线程将并行执行后续的操作。
//        在新线程中，有一个循环，它将 a 的值增加 5，循环的次数等于 count 的值
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a+=5;
                }
            }
        });
        thread.start(); //这一行启动了新线程
        int b = 0; //在主线程中，定义了一个整数变量 b，并初始化为 0
        for (int i = 0; i < count; i++) {
            //主线程中有一个循环，它将 b 的值减小，循环的次数等于 count 的值
            b--;
        }
        thread.join();//这一行让主线程等待新线程执行完成，以确保在继续执行主线程之前，新线程的工作已经完成。
        long time =System.currentTimeMillis() -start;//这一行计算了整个程序的执行时间
        System.out.println("concurrency:" + time +"ms,b="+b);//这一行打印出并发执行的时间以及变量 b 的值
        serial();//这一行调用了 serial 方法，该方法执行一系列与并发执行类似的操作，但是在单线程中执行。
    }
    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a+=5;
        }
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() -start;
        System.out.println("serial:"+ time +"ms,b="+b+",a="+a);
    }
}
