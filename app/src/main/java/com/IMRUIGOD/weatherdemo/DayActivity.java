package com.IMRUIGOD.weatherdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.ParseException;

@ContentView(R.layout.activity_day)
public class DayActivity extends AppCompatActivity {
    //绑定导航栏组件
    @ViewInject(R.id.meau_imgBtn_back) private ImageButton imgBtn_back;
    @ViewInject(R.id.meau_txt_title) private TextView title;
    //绑定界面控件
    @ViewInject(R.id.day_txt_city) private TextView txt_city;
    @ViewInject(R.id.day_txt_temp) private TextView txt_temp;
    @ViewInject(R.id.day_txt_temp_max) private TextView txt_temp_max;
    @ViewInject(R.id.day_txt_temp_min) private TextView txt_temp_min;
    @ViewInject(R.id.day_txt_weather) private TextView txt_weather;
    @ViewInject(R.id.day_txt_airSize) private TextView txt_air;
    @ViewInject(R.id.day_txt_date) private TextView txt_date;
    @ViewInject(R.id.day_img_weather) private ImageView img_weather;
    @ViewInject(R.id.day_view_colordian) private View view_colordian;

    @ViewInject(R.id.day_txt_wind_speed) private TextView txt_wind_speed;
    @ViewInject(R.id.day_txt_wind_orientation) private TextView txt_wind_orientation;
    @ViewInject(R.id.day_txt_sunrise) private TextView txt_sunrise;
    @ViewInject(R.id.day_txt_sunset) private TextView txt_sunset;

    //绑定单独组件
    @ViewInject(R.id.day_inclue_air) private View inclue_air;
    @ViewInject(R.id.day_txt_pm2_5) private TextView txt_pm2_5;
    @ViewInject(R.id.day_txt_pm10) private TextView txt_pm10;

    //创建字节工具对象
    private ByteUitl bu = new ByteUitl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //传输的数据取出
        Intent intent = this.getIntent();
        String json = intent.getStringExtra("dayWeather");
        int pos = intent.getIntExtra("pos",0);

        //设置状态栏颜色
        setBarColor();

        //设置控件数据
        setViewInit(json, pos);

    }

    // 设置状态栏背景颜色
    private void setBarColor() {
        //拿到window
        Window window = getWindow();
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置状态栏背景色
            window.setStatusBarColor(getResources().getColor(R.color.bai));
            //设置状态栏字体图标颜色
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    //设置控件数据
    private void setViewInit(String json,int pos) {
        //将json里面的东西传到天气实体类中
        Weather weather = JSON.parseObject(json,Weather.class);
        //利用pos获取当前选择的天数
        Weather.DataBean.DayBean dayBean = weather.getData().getForecast().get(pos);

        //判断是否是今天
        if (pos == 0){
            //如果为今天
            //显示空气组件
            inclue_air.setVisibility(View.VISIBLE);
            txt_temp.setText(weather.getData().getWendu());
            title.setText(weather.getCityInfo().getCity() + R.string.weather);
            //往组件里传数据
            txt_pm2_5.setText(getText(R.string.PM25).toString() + " " + weather.getData().getPm25());
            txt_pm10.setText(getText(R.string.PM10).toString() + " " + weather.getData().getPm10());

        }else{
            //不为今天
            //不显示控件
            title.setText(weather.getCityInfo().getCity() + "单日预报详情");
            txt_temp.setText(bu.getMath(weather.getData().getForecast().get(pos).getHigh()).toString());
        }
        //设置返回按钮
        imgBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DayActivity.this,MainActivity.class);
                DayActivity.this.startActivity(intent);
            }
        });


        txt_city.setText(weather.getCityInfo().getCity());

        txt_temp_max.setText(bu.getMath(weather.getData().getForecast().get(pos).getHigh()) + " " + getText(R.string.temp));
        txt_temp_min.setText(bu.getMath(weather.getData().getForecast().get(pos).getLow()) + " " + getText(R.string.temp));

        try {
            img_weather.setImageResource(bu.setWeatherImg(weather.getData().getForecast().get(pos)));
            txt_date.setText(bu.setDate(weather.getData().getForecast().get(pos).getYmd(),1)+ weather.getData().getForecast().get(pos).getWeek());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int aqi = weather.getData().getForecast().get(pos).getAqi();
        txt_air.setText(getText(R.string.airSize) + " " + aqi + " " + bu.setAirSize(aqi)+" ");
        view_colordian.setBackground(getResources().getDrawable(bu.setKQ(aqi)));
        txt_weather.setText(weather.getData().getForecast().get(pos).getType());
        txt_wind_speed.setText(getText(R.string.fl) + weather.getData().getForecast().get(pos).getFl());
        txt_wind_orientation.setText(getText(R.string.fx) + weather.getData().getForecast().get(pos).getFx());
        txt_sunrise.setText(getText(R.string.sunrise) + weather.getData().getForecast().get(pos).getSunrise());
        txt_sunset.setText(getText(R.string.sunset) + weather.getData().getForecast().get(pos).getSunset());
    }
}
