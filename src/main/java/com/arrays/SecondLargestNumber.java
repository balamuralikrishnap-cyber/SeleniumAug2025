package com.arrays;

import java.util.ArrayList;

public class SecondLargestNumber {
public static void main(String [] args)
{
    int arr[]={5,7,8,8,7,9,10,9};
    ArrayList<Integer> intArray=new ArrayList<Integer>();
    int largestNumber=0;
    int  secondLargestNumber=0;
    for(int num:arr)
    {
        if(num>largestNumber)
        {
            secondLargestNumber=largestNumber;
            largestNumber=num;

        }
        else if(num>largestNumber && num<secondLargestNumber)
        {
            secondLargestNumber = num;
        }

        }
    System.out.println("Second Largest: " + secondLargestNumber);
    }




}
