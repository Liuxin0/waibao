package com.example.rental.Login;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用来检测输入的数据是否符合格式
 * Created by 1111 on 2016/7/30.
 */
public class CheckString {
    public static boolean compare(String name, Context context) {
        String regEx = "[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        if (m.find()) {
            Toast.makeText(context, "不允许输入特殊符号！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
