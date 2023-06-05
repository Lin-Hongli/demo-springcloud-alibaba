package com.xxxcloud.generator.test;

public class Test {
    private static String country="China";
    private String name;

    {

    }

    public static void main(String[] args) {

        Test test1=new Test();
        System.out.println(test1.country);
        Test test2=new Test();
        test1.country="AA";
        System.out.println(test2.country);


    }


}
