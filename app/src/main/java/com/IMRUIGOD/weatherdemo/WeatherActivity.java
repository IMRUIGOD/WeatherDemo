package com.IMRUIGOD.weatherdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_weather)
public class WeatherActivity extends AppCompatActivity {

    @ViewInject(R.id.meau_imgBtn_back) private ImageButton imgBtn;
    @ViewInject(R.id.meau_txt_title) private TextView title;

    @ViewInject(R.id.weather_rv) private RecyclerView rv;

    private List<Weather.DataBean.DayBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        x.view().inject(this);

        //设置状态栏颜色
        setBarColor();

        //传输的数据取出
        Intent intent = this.getIntent();
        String json = intent.getStringExtra("Weather");
        Log.i("json" , json);
        Weather weather = JSON.parseObject(json,Weather.class);
        //创建装载天气的List
        list = new ArrayList<>();
        //装载昨天的天气
        list.add(weather.getData().getYesterday());
        for (int i = 0; i<weather.getData().getForecast().size();i++){
            list.add(weather.getData().getForecast().get(i));
        }

        //设置为横向布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);

        //设置适配器
        Day_Adapter adapter = new Day_Adapter(this,list);
        rv.setAdapter(adapter);

        //设置导航栏标题和返回键
        title.setText(weather.getCityInfo().getCity()+"15日预报");
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this,MainActivity.class);
                WeatherActivity.this.startActivity(intent);
            }
        });
    }

    //设置状态栏颜色
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
}
