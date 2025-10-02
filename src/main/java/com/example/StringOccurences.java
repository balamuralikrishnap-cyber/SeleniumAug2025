package com.example;

import java.util.HashMap;

public class StringOccurences {

    public static void main(String [] args)
    {
        String str="abc xyz abc xyz zzz";
        String [] strArray=str.split(" ");
        HashMap<String,Integer> strMap=new HashMap<String,Integer>(0);

        for(String str1:strArray)
        {
            if(strMap.containsKey(str1))
            strMap.put(str1,strMap.get(str1)+1);
            else
                strMap.put(str1,1);

        }
        System.out.println(strMap);

        for (String key : strMap.keySet()) {
            System.out.println(key + " -> " + strMap.get(key));
        }
    }

}
