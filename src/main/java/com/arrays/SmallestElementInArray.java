package com.arrays;

public class SmallestElementInArray {
    public static void main (String [] args)
    {
        int arr[] = new int[] {5,17,3,25,0};
        int smallest=arr[0];
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]<smallest)
            {
                smallest=arr[i];
            }
        }
        System.out.println("smallest is " + smallest);
    }
}
