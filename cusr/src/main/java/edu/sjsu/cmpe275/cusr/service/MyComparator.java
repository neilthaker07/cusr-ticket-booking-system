package edu.sjsu.cmpe275.cusr.service;

import java.util.Comparator;
import java.util.Map;

public class MyComparator implements Comparator<Map<String, Object>>
{
    private final String key;

    public MyComparator(String key)
    {
        this.key = key;
    }

    public int compare(Map<String, Object> first,
                       Map<String, Object> second)
    {
        // TODO: Null checking, both for maps and values
        int firstValue = Integer.valueOf(String.valueOf(first.get(key)));
        int secondValue = Integer.valueOf(String.valueOf(second.get(key)));

        
        if(firstValue < secondValue)
        {
        		return -1; 
        }
        else if(firstValue > secondValue)
        {
        		return 1;
        }
        else
        {
        		return 0;
        }
    }
}
