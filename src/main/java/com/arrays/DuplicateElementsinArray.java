package com.arrays;

public class DuplicateElementsinArray {

    public static void main(String [] args)
    {
        int[] arr = {2, 4, 6,10,10,23,56,17,17, 2, 8, 4};
        System.out.println("Duplicates:");
        for(int i=0;i<arr.length;i++)
        {
            for(int j=i+1;j<arr.length;j++)
            {
                if(arr[i]==arr[j])
                {
                    System.out.println(arr[j]);
                }
            }
        }
    }
}
