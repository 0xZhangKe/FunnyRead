package com.zhangke.funnyread.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ZhangKe at 2016/12/11
 */
public class DateUtils {

    /**
     * 获取系统当前日期，格式为：yyyyMMdd
     * @return
     */
    public static String getNowDate(){
        Date dNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dNow);//把当前时间赋给日历
        Date nowDate  = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        return sdf.format(nowDate);
    }

    /**
     * 根据传入的日期，获取前一天日期。
     * @param sDate 格式yyyyMMdd
     * @return 格式yyyyMMdd
     */
    public static String getBeforeDate(String sDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dNow = new Date();
        try {
            dNow = simpleDateFormat.parse(sDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
        Date nowDate  = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        return sdf.format(nowDate);
    }

    /**
     * 根据传入的日期，获取前一天日期。
     * @param sDate 格式yyyyMMdd
     * @return 格式yyyyMMdd
     */
    public static String getNextDate(String sDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dNow = new Date();
        try {
            dNow = simpleDateFormat.parse(sDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, 1);  //设置为下一天
        Date nowDate  = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        return sdf.format(nowDate);
    }

    public static String formatDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dNow = new Date();
        try {
            dNow = simpleDateFormat.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(dNow);
    }
}
