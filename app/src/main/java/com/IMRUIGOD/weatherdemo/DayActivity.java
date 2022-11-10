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
    @ViewInject(R.id.imgbtn_fh) private ImageButton imgbtn;
    @ViewInject(R.id.txt_meau_title) private TextView title;
    //绑定界面控件
    @ViewInject(R.id.day_txt_dz) private TextView txt_dz;
    @ViewInject(R.id.day_txt_wd) private TextView txt_wd;
    @ViewInject(R.id.day_txt_wd_zg) private TextView txt_wd_zg;
    @ViewInject(R.id.day_txt_wd_zd) private TextView txt_wd_zd;
    @ViewInject(R.id.day_txt_tq) private TextView txt_tq;
    @ViewInject(R.id.day_txt_kqzl) private TextView txt_kq;
    @ViewInject(R.id.day_txt_riqi) private TextView txt_riqi;
    @ViewInject(R.id.day_img_tq) private ImageView img_tq;
    @ViewInject(R.id.day_view_colordian) private View view_colordian;

    @ViewInject(R.id.day_txt_fl) private TextView txt_fl;
    @ViewInject(R.id.day_txt_fx) private TextView txt_fx;
    @ViewInject(R.id.day_txt_sunrise) private TextView txt_sunrise;
    @ViewInject(R.id.day_txt_sunset) private TextView txt_sunset;

    //绑定单独组件
    @ViewInject(R.id.inclue_kq) private View inclue_kq;
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
            inclue_kq.setVisibility(View.VISIBLE);
            txt_wd.setText(weather.getData().getWendu());
            title.setText(weather.getCityInfo().getCity()+"天气");
            //往组件里传数据
            txt_pm2_5.setText("PM 2.5: "+weather.getData().getPm25());
            txt_pm10.setText("PM 10: "+weather.getData().getPm10());

        }else{
            //不为今天
            //不显示控件
            title.setText(weather.getCityInfo().getCity()+"单日预报详情");
            txt_wd.setText(bu.getMath(weather.getData().getForecast().get(pos).getHigh())+"");
        }
        //设置返回按钮
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DayActivity.this,MainActivity.class);
                DayActivity.this.startActivity(intent);
            }
        });


        txt_dz.setText(weather.getCityInfo().getCity());

        txt_wd_zg.setText(bu.getMath(weather.getData().getForecast().get(pos).getHigh())+" ℃");
        txt_wd_zd.setText(bu.getMath(weather.getData().getForecast().get(pos).getLow())+" ℃");

        try {
            img_tq.setImageResource(bu.setWeatherImg(weather.getData().getForecast().get(pos)));
            txt_riqi.setText(bu.setriqi(weather.getData().getForecast().get(pos).getYmd(),1)+ weather.getData().getForecast().get(pos).getWeek());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int aqi = weather.getData().getForecast().get(pos).getAqi();
        txt_kq.setText("空气质量："+aqi+" "+bu.setAirSize(aqi)+" ");
        view_colordian.setBackground(getResources().getDrawable(bu.setKQ(aqi)));
        txt_tq.setText(weather.getData().getForecast().get(pos).getType());
        txt_fl.setText("风力："+weather.getData().getForecast().get(pos).getFl());
        txt_fx.setText("风向："+weather.getData().getForecast().get(pos).getFx());
        txt_sunrise.setText("日出时间："+weather.getData().getForecast().get(pos).getSunrise());
        txt_sunset.setText("日落时间："+weather.getData().getForecast().get(pos).getSunset());
    }
}
