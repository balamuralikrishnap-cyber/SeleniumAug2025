package com.arrays;

public class SumAndAverageofArrays {

    public static void main(String [] args)
    {
        int [] arr = new int[]{5, 9, 8, 6, 2};
        int sum=0;
        float avg=0;
        for(int i=0;i<arr.length;i++)
        {
           sum=sum+arr[i];
        }

        System.out.println("Sum is+ " + sum);
        avg=sum/arr.length;
        System.out.println("avg is+ " + avg);
 }
}
