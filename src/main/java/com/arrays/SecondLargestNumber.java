package com.arrays;

import java.util.ArrayList;

public class SecondLargestNumber {
public static void main(String [] args)
{
    int arr[]={5,7,8,8,7,9,10,9};
    ArrayList<Integer> intArray=new ArrayList<Integer>();
    for(int num:arr)
    {
        if(!intArray.contains(num))
        {
            intArray.add(num);
        }
    }
    int temp;
    for(int i=0;i<intArray.size();i++)
    {
        if(intArray.get(i)>intArray.get(i+1))
        {
            temp=intArray.get(i);
            intArray.set(i+1,intArray.get(i));
            intArray.set(i,temp);



        }
    }
    System.out.println(intArray);

}


}
