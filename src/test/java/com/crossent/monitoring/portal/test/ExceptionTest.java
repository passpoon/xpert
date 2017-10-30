package com.crossent.monitoring.portal.test;

public class ExceptionTest {

    public static void main(String[] args){





        try{
            testException();
        }catch(Exception ex){
             System.out.println("ddddddddddddddddddd");
            ex.printStackTrace();
        }



    }


    public static int testException() throws Exception{

        String a = null;
        int length = 0;
        try {
            length = a.length();
        }catch(Exception ex){
            throw ex;

        }
        return length;

    }
}
