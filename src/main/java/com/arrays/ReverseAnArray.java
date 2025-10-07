package com.arrays;

import java.util.Arrays;

public class ReverseAnArray {
    public static void main(String [] args)
    {
        int[] arr={3,6,8,1,2};
        int n=arr.length;
        for(int i=0;i<n/2;i++)
        {
            int temp=arr[i];
            arr[i]=arr[n-1-i];
            arr[n-1-i]=temp;
        }
        System.out.println(Arrays.toString(arr));
    }

}
