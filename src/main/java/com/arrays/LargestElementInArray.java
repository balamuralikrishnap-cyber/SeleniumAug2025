package com.arrays;

public class LargestElementInArray {
    public static void main (String [] args)
    {
        int arr[] = new int[] {5,17,3,25,0};
        int largest=arr[0];
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]>largest)
            {
                largest=arr[i];
            }
        }
        System.out.println("largest is " + largest);
    }
}
