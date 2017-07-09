# 图片轮滑使用

* [引用](#引用)
* [SliderLayout控件](#sliderlayout控件)
* [TextSliderView](#textsliderview)
* [PagerIndicator](#pagerindicator)
* [参考地址](#参考地址)


## 引用
build.gradle中添加：
```
dependencies {
    compile 'com.squareup.picasso:picasso:2.3.2'
    compile 'com.nineoldandroids:library:2.4.0'
    compile 'com.daimajia.slider:library:1.1.5@aar'
}
```
AndroidManifest.xml中添加权限：
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```


## SliderLayout控件
xml引用的轮播图主要是SliderLayout控件。
```
<com.daimajia.slider.library.SliderLayout
    android:id="@+id/slider_layout"
    android:layout_width="match_parent"
    android:layout_height="150dp">
```
SliderLayout继承RelativeLayout，所以可以在里面嵌套PagerIndicator，并且适当调整显示位置
java代码中添加SliderLayout数据：
```
mSliderLayout.addSlider(textSliderView);
```
注：SliderLayout添加的数据是TextSliderView类型

SliderLayout添加PagerIndicator
```
mSliderLayout.setCustomIndicator(mPagerIndicator);
```
SliderLayout添加轮播间隔时间：
```
mSliderLayout.setDuration(4000);//设置轮滑间隔时间,默认4秒
```
SliderLayout设置描述的动画：
```
mSliderLayout.setCustomAnimation(new DescriptionAnimation());//设置描述的动画，默认没有动画
```
SliderLayout设置图片显示的动画
```
mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);//设置图片显示的动画
```


## TextSliderView
TextSliderView是SliderLayout控件的数据
TextSliderView的image方法添加图片，description方法添加图片描述，当然还有error方法添加错误时显示图片
和empty方法添加没加载图片之前显示的图片
TextSliderView的setOnSliderClickListener方法设置点击事件
其中图片地址可以设置资源下图片，网络图片地址，assets下图片，sd卡下图片
```
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
//          Toast.makeText(ImageSliderActivity.this, descriptions.get(position), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ImageSliderActivity.this, Html5Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("url", jumpUrls.get(position));
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }
    });
    mSliderLayout.addSlider(textSliderView);
}
```

注：认为事件写在for循环中不是很好，不过没有发现其他方式，所以暂时用这种方式


## PagerIndicator
PagerIndicator是SliderLayout显示当前页码的指示标识
```
<com.daimajia.slider.library.Indicators.PagerIndicator
    android:id="@+id/pager_indicator"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"
    android:layout_centerHorizontal="true"
    android:layout_marginBottom="10dp"/>
```
因为SliderLayout是继承相对布局，所以可以嵌套在SliderLayout里面，并且相对调整位置

PagerIndicator的属性：
* shape   形状oval | rect
* visibility  显示否visible | invisible
* selected_drawable  当前轮滑到位置的页面指示标识的图片
* unselected_drawable 没有轮滑到位置的页面指示标识的图片
* selected_color 当前轮滑到位置的页面指示标识的颜色
* unselected_color 没有轮滑到位置的页面指示标识的颜色
* selected_width 当前轮滑到位置的页面指示标识的宽度
* unselected_width 没有轮滑到位置的页面指示标识的宽度
* selected_height 当前轮滑到位置的页面指示标识的高度
* unselected_height 没有轮滑到位置的页面指示标识的高度
* selected_padding_left,unselected_padding_left,selected_padding_right,unselected_padding_right,
selected_padding_top,unselected_padding_top,selected_padding_bottom,unselected_padding_bottom
指示标识对应的padding值


## 参考地址

[https://github.com/daimajia/AndroidImageSlider](https://github.com/daimajia/AndroidImageSlider "参考地址")



