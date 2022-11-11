package com.IMRUIGOD.weatherdemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    //绑定组件
    @ViewInject(R.id.main) private LinearLayout main;
    //顶部功能栏
    @ViewInject(R.id.meau_txt_title) private TextView txt_city;
    @ViewInject(R.id.meau_imgBtn_back) private ImageButton meau_imageBtn;
    //头部数据栏
    @ViewInject(R.id.main_txt_wd) private TextView txt_temp;
    @ViewInject(R.id.txt_wd_zg) private TextView txt_temp_max;
    @ViewInject(R.id.txt_wd_zd) private TextView txt_temp_min;
    @ViewInject(R.id.txt_tq) private TextView txt_weather;
    @ViewInject(R.id.txt_kq) private TextView txt_air;
    @ViewInject(R.id.notice) private TextView notice;
    @ViewInject(R.id.updateTime) private TextView updateTime;
    //中间天气栏
    @ViewInject( R.id.item_meitian_text_date) private TextView txt_date;
    @ViewInject(R.id.item_meitian_img_weather) private ImageView img_weather;
    @ViewInject(R.id.item_meitian_txt_temp_max) private TextView temp_max;
    @ViewInject(R.id.item_meitian_txt_temp_min) private TextView temp_min;
    //尾部更多天气
    @ViewInject(R.id.btn_more) private Button btn_more;

    private List list;
    private ByteUitl bu = new ByteUitl();
    private ListView lv;
    private SimpleAdapter adapter;
    private Map map1;
    private String url ="http://t.weather.sojson.com/api/weather/city/101060101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //判断白天黑天
        booleanDayOrNight();

        //将访问的网页放到RequestParams中
        RequestParams params = new RequestParams(url);
        //使用xUitl3工具get方法访问网页
        x.http().get(params, new Callback.CommonCallback<String>() {
            private Weather weather;

            @Override
            public void onSuccess(final String json) {
                //接收天气json，解析成对象；
                System.out.println(json);
                weather = JSON.parseObject(json, Weather.class);



                //输入到文本框中
                setViewInit();

                //接收对象进行封装，并传输到ListView中
                addData(json);

                //跳转15天天气
                btn_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                        intent.putExtra("Weather", json);
                        MainActivity.this.startActivity(intent);
                    }
                });
            }

            //设置控件数据
            private void setViewInit() {
                txt_city.setText(weather.getCityInfo().getCity());
                txt_city.setTextColor(getColor(R.color.white));
                txt_city.setTextSize(20);
                txt_city.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                txt_temp.setText(weather.getData().getWendu());
                txt_temp_max.setText(bu.getMath(weather.getData().getForecast().get(0).getHigh()) + " " + getText(R.string.temp));
                txt_temp_min.setText(bu.getMath(weather.getData().getForecast().get(0).getLow()) + " " + getText(R.string.temp));
                txt_weather.setText(weather.getData().getForecast().get(0).getType()+"  ");
                txt_air.setText("空气 "+ weather.getData().getQuality());
                notice.setText(weather.getData().getForecast().get(0).getNotice());
                updateTime.setText("上次更新时间：  "+ weather.getCityInfo().getUpdateTime());
                temp_min.setText(bu.getMath(weather.getData().getYesterday().getLow()) + " " + getText(R.string.temp));
                temp_max.setText(bu.getMath(weather.getData().getYesterday().getHigh()) + " " + getText(R.string.temp));
                try {
                    txt_date.setText(bu.setDate(weather.getData().getYesterday().getYmd(),1)+getText(R.string.yesterday));
                    img_weather.setImageResource(bu.setWeatherImg(weather.getData().getYesterday()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            //设置ListView里面的数据
            private void addData(String json) {
                list = new ArrayList<>();
                try {
                    //循环填入为来七天的数据
                    for (int i = 0; i < 7; i++){
                        map1 = new HashMap();
                        switch (i){
                            case 0:
                                map1.put("ymd",bu.setDate(weather.getData().getForecast().get(i).getYmd(),1) + getText(R.string.today));
                                break;
                            case 1:
                                map1.put("ymd",bu.setDate(weather.getData().getForecast().get(i).getYmd(),1) + getText(R.string.tomorrow));
                                break;
                            default:
                                map1.put("ymd",bu.setDate(weather.getData().getForecast().get(i).getYmd(),1)+ weather.getData().getForecast().get(i).getWeek());
                                break;
                        }

                        map1.put("img",bu.setWeatherImg(weather.getData().getForecast().get(i)));
                        map1.put("max",bu.getMath(weather.getData().getForecast().get(i).getHigh()) + " " + getText(R.string.temp));
                        map1.put("min",bu.getMath(weather.getData().getForecast().get(i).getLow()) + " " + getText(R.string.temp));
                        list.add(map1);
                    }
                    //将装填好的数据传入listview中
                    setListView(list,json);


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //网络连接失败
                System.out.println(404);
                notice.setText("404");
                notice.setTextColor(Color.BLACK);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        meau_imageBtn.setBackgroundResource(R.drawable.add);
        meau_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CityActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    //设置ListView
    private void setListView(List list, final String json) {
        lv = findViewById(R.id.lv_main);
        adapter = new SimpleAdapter(
                this,
                list,R.layout.item_meitian,
                new String[]{"ymd", "img", "max", "min"},
                new int[]{R.id.item_more_txt_date,R.id.item_meitian_img_weather,R.id.item_meitian_txt_temp_max,R.id.item_meitian_txt_temp_min});
        lv.setAdapter(adapter);

        //每一个item的单击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                //跳转页面
                Intent intent = new Intent(MainActivity.this, DayActivity.class);
                //向跳转的页面传输数据
                intent.putExtra("dayWeather", json);
                intent.putExtra("pos", pos);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    //判断白天黑天
    private void booleanDayOrNight() {
        //拿到window
        Window window = getWindow();
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        try {
            if (bu.ifDayOrNight() == true) {
                //白天
                //设置白天渐变背景
                main.setBackground(getResources().getDrawable(R.drawable.bg_daytime));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //设置状态栏背景色
                    window.setStatusBarColor(getResources().getColor(R.color.bar_daytime));
                }
            } else{
                //黑天
                //设置黑天渐变背景
                main.setBackground(getResources().getDrawable(R.drawable.bg_night));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //设置状态栏背景色
                    window.setStatusBarColor(getResources().getColor(R.color.bar_night));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
