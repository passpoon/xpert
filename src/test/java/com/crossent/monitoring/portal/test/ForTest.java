package com.crossent.monitoring.portal.test;

import java.util.ArrayList;
import java.util.List;

public class ForTest {

    public static void main(String[] args){


        for(int i=0; i < 10; i++){
            System.out.println(i);
        }


        List<String> strings = new ArrayList<String>();
        strings.add("a1");
        strings.add("a2");
        strings.add("a3");
        strings.add("a4");
        strings.add("a5");
        strings.add("a6");

        for(String str : strings){
            System.out.println(str);
        }

     }
}
