package com.arrays;

import java.util.HashMap;
import java.util.Set;

public class DuplicateElememtsInArrayAlternativeApproach {
public static void main(String[]args)
{
    int[] arr = {2, 4, 6,10,10,23,56,17,17,17, 2, 8, 4};
    HashMap<Integer,Integer> intMap=new HashMap<>();
    for(int i=0;i<arr.length;i++)
    {
        if(!intMap.containsKey(arr[i]))
        {
            intMap.put(arr[i],1);

        }
        else {
            intMap.put(arr[i],  intMap.get(arr[i])+1);

        }

    }
    Set<Integer> intKeys=intMap.keySet();
    for(int key:intKeys )
    {
        if(intMap.get(key)>1)
        System.out.println("duplicate  " +key + " repeated for "+intMap.get(key) +" times");
    }

}

}
