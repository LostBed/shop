package com.tools;

import java.io.UnsupportedEncodingException;

public class ChStr {
    public String chStr(String str){
        if (str==null){
            str="";
        }else {
            try {
                str=(new String(str.getBytes("iso-8859-1"),"UTF-8")).trim();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    return str;
    }
}
