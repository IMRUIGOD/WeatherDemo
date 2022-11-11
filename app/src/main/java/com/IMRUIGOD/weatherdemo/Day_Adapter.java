package com.IMRUIGOD.weatherdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 最近十五天天气适配器
 */
public class Day_Adapter extends RecyclerView.Adapter<Day_Adapter.DA> {

    private static final String TAG = "Day_Adapter";
    private Context mContext;
    private List<Weather.DataBean.DayBean> list = new ArrayList<>();
    private ByteUitl bu = new ByteUitl();
    //最小值
    private int minValue;
    //最大值
    private int maxValue;

    //页面构造方法
    public Day_Adapter(Context context,List<Weather.DataBean.DayBean> data){
        this.list.addAll(data);
        //创建最高温度列表
        List<Integer> list_zg = new ArrayList<>();
        //创建最低温度列表
        List<Integer> list_zd = new ArrayList<>();
        //使用for循环装填数据
        for (int i = 1;i<this.list.size();i++){
            //装填最高温度
            list_zg.add(bu.getMath(this.list.get(i).getHigh()));
            //装填最低温度
            list_zd.add(bu.getMath(this.list.get(i).getLow()));
        }

        //将不规则的数据组从小到大排序
        Collections.sort(list_zg);
        Collections.sort(list_zd);
        minValue = list_zd.get(0);
        maxValue = list_zg.get(list_zg.size()-1);
        mContext = context;
    }

    @NonNull
    //设置ItemView样式
    @Override
    public DA onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取item样式
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_day, viewGroup,false);
        //返回item样式
        return new DA(view);
    }

    //设置数据
    @Override
    public void onBindViewHolder(@NonNull DA da, int i) {
        //如果是第一个
        if(i==0){
            //设置第一个不画左边的线
            da.mTemperatureView.setDrawLeftLine(false);
        }
        //除第一个以外
        else{
            da.mTemperatureView.setDrawLeftLine(true);
            da.mTemperatureView.setZGLastValue(bu.getMath(list.get(i-1).getHigh()));
            da.mTemperatureView.setZDLastValue(bu.getMath(list.get(i-1).getLow()));
        }

        //如果是最后一个
        if(i == list.size()-1){
            //设置最后一个不画右边的线
            da.mTemperatureView.setDrawRightLine(false);
        }
        //除最后一个以外
        else{
            da.mTemperatureView.setDrawRightLine(true);
            da.mTemperatureView.setZGNextValue(bu.getMath(list.get(i+1).getHigh()));
            da.mTemperatureView.setZDNextValue(bu.getMath(list.get(i+1).getLow()));
        }

        //设置点的值
        da.mTemperatureView.setZGCurrentValue(bu.getMath(list.get(i).getHigh()));
        da.mTemperatureView.setZDCurrentValue(bu.getMath(list.get(i).getLow()));

        //判断输出昨天、今天、明天、星期几
        switch (i){
            case 0:
                da.txt_week.setText(R.string.yesterday);
                break;
            case 1:
                da.txt_week.setText(R.string.today);
                break;
            case 2:
                da.txt_week.setText(R.string.tomorrow);
                break;
            default:
                da.txt_week.setText(list.get(i).getWeek());
                break;
        }
        //设置日期  格式为6/11
        try {
            da.txt_date.setText(bu.setDate(list.get(i).getYmd(),2));
            da.img_weather.setImageResource(bu.setWeatherImg(list.get(i)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        da.txt_weather.setText(list.get(i).getType());
        da.txt_airSize.setText(bu.setAirSize(list.get(i).getAqi())+" ");
        da.view_colordian.setBackground(mContext.getResources().getDrawable(bu.setKQ(list.get(i).getAqi())));
        da.txt_wind_speed.setText(list.get(i).getFl());
        da.txt_wind_orientation.setText(list.get(i).getFx());
    }

    //输出的长度
    @Override
    public int getItemCount() {
        //返回list长度    list.size==16天    16=昨天+今天往后15天
        return list.size();
    }

    //绑定页面组件
    class DA extends RecyclerView.ViewHolder{

        private TemperatureView mTemperatureView;
        private TextView txt_week;
        private TextView txt_date;
        private TextView txt_wind_speed;
        private TextView txt_wind_orientation;
        private TextView txt_airSize;
        private TextView txt_weather;
        private ImageView img_weather;
        private View view_colordian;

        public DA(@NonNull View itemView) {
            super(itemView);
            mTemperatureView = itemView.findViewById(R.id.item_day_temp_view);
            txt_week = itemView.findViewById(R.id.item_day_txt_week);
            txt_date = itemView.findViewById(R.id.item_day_txt_date);
            txt_wind_speed = itemView.findViewById(R.id.item_day_txt_wind_speed);
            txt_wind_orientation = itemView.findViewById(R.id.item_day_txt_wind_orientation);
            txt_weather = itemView.findViewById(R.id.item_day_txt_weather);
            txt_airSize = itemView.findViewById(R.id.item_day_txt_airSize);
            img_weather = itemView.findViewById(R.id.item_day_img_weather);
            view_colordian = itemView.findViewById(R.id.item_day_view_colordian);
            mTemperatureView.setMinValue(minValue);
            mTemperatureView.setMaxValue(maxValue);
        }
    }

}