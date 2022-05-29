package com.ctf.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConvertorALSM {

    //map转数组
    public static String[] Map2Array(Map<String,String> originMap){
        Set<String> mapValuesSet = new HashSet<String>(originMap.values());
        String[] arr = new String[mapValuesSet.size()];
        //Set-->数组
        String[] strings = mapValuesSet.toArray(arr);
        return strings;
    }


}