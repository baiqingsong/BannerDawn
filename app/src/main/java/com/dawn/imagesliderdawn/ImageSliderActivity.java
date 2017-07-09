package com.dawn.imagesliderdawn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90449 on 2017/7/9.
 */

public class ImageSliderActivity extends AppCompatActivity {
    private SliderLayout mSliderLayout;
    private PagerIndicator mPagerIndicator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        mSliderLayout = (SliderLayout) findViewById(R.id.slider_layout);
        mPagerIndicator = (PagerIndicator) findViewById(R.id.pager_indicator);
        initSlider();
    }
    private void initSlider(){
        //准备好要显示的数据
        List<Object> imageUrls = new ArrayList<>();
        final List<String> descriptions = new ArrayList<>();
        final List<String> jumpUrls = new ArrayList<>();
        imageUrls.add(R.drawable.bd_logo);
        imageUrls.add("http://www.baidu.com/img/bd_logo1.png");
        imageUrls.add("file:///android_asset/bd_logo.png");
        imageUrls.add("file:///sdcard/bd_logo.png");
        descriptions.add("资源下图片");
        descriptions.add("网络请求");
        descriptions.add("assets下图片");
        descriptions.add("sd卡下图片");
        jumpUrls.add("https://github.com/");
        jumpUrls.add("https://www.baidu.com");
        jumpUrls.add("https://www.taobao.com");
        jumpUrls.add("https://juejin.im/timeline");

        for (int i = 0; i < imageUrls.size(); i++) {
            //新建三个展示View，并且添加到SliderLayout
            final TextSliderView textSliderView = new TextSliderView(this);
            if(imageUrls.get(i) instanceof String){
                textSliderView.image((String) imageUrls.get(i));
            }else if(imageUrls.get(i) instanceof Integer){
                textSliderView.image((Integer) imageUrls.get(i));
            }
            textSliderView.description(descriptions.get(i)).empty(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher);
            final int position = i;
            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
//                    Toast.makeText(ImageSliderActivity.this, descriptions.get(position), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ImageSliderActivity.this, Html5Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", jumpUrls.get(position));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            });
            mSliderLayout.addSlider(textSliderView);
        }

        //对SliderLayout进行一些自定义的配置
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());//设置描述的动画，默认没有动画
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);//设置图片显示的动画
        mSliderLayout.setDuration(4000);//设置轮滑间隔时间,默认4秒
        mSliderLayout.setCustomIndicator(mPagerIndicator);

    }
}
