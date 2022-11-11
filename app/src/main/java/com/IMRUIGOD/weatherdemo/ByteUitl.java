package com.IMRUIGOD.weatherdemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 字节工具类
 * 用于处理各种字符串、判断时间、日期、以及天气图片
 */
public class ByteUitl {
    //取数字         将温度中的数字取出加温度符号 列如：26℃
    public Integer getMath(String str) {
        String temp = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    temp += str.charAt(i);
                }
            }
        }
        return Integer.parseInt(temp);
    }

    //判断白天黑夜
    public boolean ifDayOrNight() throws ParseException {
        Calendar cal = Calendar.getInstance();
        int hh = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println(hh);
        
        if (hh>=6 && hh<=20){
            //白天
            return true;
        }else {
            //黑天
            return false;
        }
    }

    //转换日期格式       将日期转换 例如：06月11日  或者  06/11
    public String setDate(String riqi, int i) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date ymd = df.parse(riqi);
        Calendar cal = Calendar.getInstance();
        cal.setTime(ymd);
        String md = "";
        if (i == 1){
            //第一种格式：06月11日
            md = (cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DATE)+"日";
        }else {
            //第二种格式：06/11
            md = (cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE);
        }
        //返回日期
        return md;
    }

    //发送对应的天气图片
    public Integer setWeatherImg(Weather.DataBean.DayBean day) throws ParseException {
            switch (day.getType()){
                case "晴":
                    if (ifDayOrNight() == true) {
                        return R.drawable.clear_daytime;
                    } else{
                        return R.drawable.clear_night;
                    }
                case "多云":
                    if (ifDayOrNight() == true) {
                        return R.drawable.cloudy_daytime;
                    } else{
                        return R.drawable.cloudy_night;
                    }
                case "小雨":
                    if (ifDayOrNight() == true) {
                        return R.drawable.light_rain_daytime;
                    } else{
                        return R.drawable.light_rain_night;
                    }
                case "小雪":
                    if (ifDayOrNight() == true) {
                        return R.drawable.light_snow_daytime;
                    } else{
                        return R.drawable.light_snow_night;
                    }
                case "中雨":
                    return R.drawable.moderate_rain;
                case "中雪":
                    return R.drawable.moderate_snow;
                case "大雪":
                    return R.drawable.heavy_snow;
                case "大雨":
                    return R.drawable.heavy_rain;
                case "暴雪":
                    return R.drawable.heavy_snowfall;
                case "暴雨":
                    return R.drawable.heavy_rainfall;
                case "阴":
                    return R.drawable.overcast_sky;
                case "雨夹雪":
                    return R.drawable.sleet;
                case "雷阵雨":
                    return R.drawable.thundershower;
                case "霾":
                    return R.drawable.haze;
                default:
                    return R.mipmap.ic_launcher;
            }
    }

    //发送对应的颜色布局
    public Integer setKQ(int aqi){
        if (aqi<=50 && aqi>=0){
            return R.drawable.circle_green;
        }else {
            if (aqi<=100 && aqi>=51){
                return R.drawable.circle_yellow;
            }else {
                if (aqi<=101 && aqi>=150){
                    return R.drawable.circle_orange;
                }else {
                    if (aqi<=151 && aqi>=200){
                        return R.drawable.circle_red;
                    }else {
                        if (aqi<=201 && aqi>=300){
                            return R.drawable.circle_purple;
                        }else {
                            return R.drawable.circle_maroon;
                        }
                    }
                }
            }
        }
    }

    //发送对应的空气指数
    public String setAirSize(int aqi){
        if (aqi<=50 && aqi>=0){
            return "优";
        }else {
            if (aqi<=100 && aqi>=51){
                return "良";
            }else {
                if (aqi<=101 && aqi>=150){
                    return "轻度";
                }else {
                    if (aqi<=151 && aqi>=200){
                        return "中度";
                    }else {
                        if (aqi<=201 && aqi>=300){
                            return "重度";
                        }else {
                            return "严重";
                        }
                    }
                }
            }
        }
    }



}
