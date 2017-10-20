package com.crossent.monitoring.portal.test;

public class LambdaTest {

    public static void main(String[] args){


        new Thread(()->{
            System.out .println("thread start");

        }).start();

    }
}
