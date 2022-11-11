package com.IMRUIGOD.weatherdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_city)
public class CityActivity extends AppCompatActivity {
    //导航栏控件
    @ViewInject(R.id.meau_imgBtn_back) private ImageButton imgBtn_back;
    @ViewInject(R.id.meau_txt_title) private TextView txt_title;
    @ViewInject(R.id.meau_imgBtn_add) private ImageButton imgBtn_add;
    @ViewInject(R.id.meau_imgBtn_util) private ImageButton imgBtn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //设置状态栏颜色
        setViewInit();
        setBarColor();
    }

    // 设置状态栏背景颜色
    private void setBarColor() {
        //拿到window
        Window window = getWindow();
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置状态栏背景色
            window.setStatusBarColor(getColor(R.color.white));
            //设置状态栏字体图标颜色
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void setViewInit() {
        imgBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CityActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        txt_title.setText("城市管理");
        imgBtn_add.setVisibility(View.VISIBLE);
        imgBtn_update.setVisibility(View.VISIBLE);
    }
}