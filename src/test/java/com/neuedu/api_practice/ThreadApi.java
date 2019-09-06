package com.neuedu.api_practice;

public class ThreadApi extends Thread {

    int num = 0;


    public static void main(String[] args) {




        for (int i=0;i<1000;i++){

            System.out.println("====main()===="+i);

            //线程的创建，new ThreadApi();
            //ThreadApi threadApi = new ThreadApi(i);
            //线程的就绪状态 threadApi.start();
            //threadApi.start();
            //线程运行  jvm调度线程时 线程运行 运行的是run()方法; run()方法结束 线程终止
            //阻塞状态 Thread.sleep(1000ms),调用对应的阻塞方法，如sleep();
            //解除阻塞状态后又回到就绪状态，等待jvm的调度
            //最后线程终止
        }

        ThreadApi threadApi = new ThreadApi();
        threadApi.start();

    }

    public ThreadApi(){

    }

    public ThreadApi(int num){
        this.num = num;
    }




    @Override
    public void run() {
        for (int i=0;i<1000;i++){

            System.out.println("===run()===="+i);

            if (i%10==0){
                try {
                    //设置阻塞状态
                    Thread.sleep(2000);
//                    System.out.println("===run()===="+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }




        }

    }
}
