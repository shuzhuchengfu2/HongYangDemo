package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.widget.MyImageActivity;

/**
 * 自定义控件
 */
public class CustomControlsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_controls);
    }

    /**
     * 自定义控件一
     * @param view
     */
    public void customControlTotal(View view){
        Intent intent = new Intent(this,CustomTitleViewActivity.class);
        startActivity(intent);
    }

    /**
     * 自定义控件二
     * @param view
     */
    public void customControlTotal2(View view){
        Intent intent = new Intent(this,CustomImageViewActivity.class);
        startActivity(intent);
    }


    /**
     * ListView 的滑动删除
     * @param view
     */
    public void listViewDelete(View view){
        Intent intent = new Intent(this,ListViewDeleteActivity.class);
        startActivity(intent);
    }

    /**
     * 仿Win8的metro的UI界面
     * @param view
     */
    public void myImageView(View view){
        Intent intent = new Intent(this,MyImageActivity.class);
        startActivity(intent);
    }

    /**
     * 圆环交替 等待效果
     * @param view
     */
    public void customProgressBar(View view){
        Intent intent = new Intent(this,CustomProgressBarActivity.class);
        startActivity(intent);
    }

    /**
     * 视频音量调控
     * @param view
     */
    public void customVolumControlBar(View view){
        Intent intent = new Intent(this,CustomVolumControlBarActivity.class);
        startActivity(intent);
    }

    /**
     * Android 完美实现图片圆角和圆形（对实现进行分析）
     * @param view
     */
    public void circleImageView(View view){
        Intent intent = new Intent(this,CircleImageViewActivity.class);
        startActivity(intent);
    }

    /**
     * ScrollView反弹效果 仿小米私密短信效果
     * @param view
     */
    public void bounceScrollView(View view){
        Intent intent = new Intent(this,BounceScrollViewActivity.class);
        startActivity(intent);
    }

    /**
     * 手势密码
     * @param view
     */
    public void gestureLock(View view){
        Intent intent = new Intent(this,GestureLockActivity.class);
        startActivity(intent);
    }

    /**
     * AcrMenu
     * @param view
     */
    public void acrMenu(View view){
        Intent intent = new Intent(this, ArcMenuActivity.class);
        startActivity(intent);
    }

    /**
     * 选择按钮 和viewpager一起使用
     * @param view
     */
    public void switchButton(View view){
        Intent intent = new Intent(this, SwitchButtonActivity.class);
        startActivity(intent);
    }

    /**
     * Android 自定义 ViewPager 打造千变万化的图片切换效果
     * @param view
     */
    public void myJazzyViewPager(View view){
        Intent intent = new Intent(this, MyJazzyViewPagerActivity.class);
        startActivity(intent);
    }

    /**
     * Android 自定义 HorizontalScrollView 打造再多图片（控件）也不怕 OOM 的横向滑动效果
     * @param view
     */
    public void myHorizontalScrollView(View view){
        Intent intent = new Intent(this,MyHorizontalScrollViewActivity.class);
        startActivity(intent);
    }

    /**
     *  Android 自定义RecyclerView 实现真正的Gallery效果
     * @param view
     */
    public void recyclerViewGallery(View view){
        Intent intent = new Intent(this,RecyclerViewActivity.class);
        startActivity(intent);
    }

    /**
     * 自定义ViewGroup
     * @param view
     */
    public void customViewGroup(View view){
        Intent intent = new Intent(this,CustomViewGroupActivity.class);
        startActivity(intent);
    }

    /**
     * 自定义FlowLayout
     * @param view
     */
    public void flowLayout(View view){
        Intent intent = new Intent(this,FlowLayoutActivity.class);
        startActivity(intent);
    }

    /**
     * 自定义ScrollView实现循环Item拖动
     * @param view
     */
    public void singleItemScrollView(View view){
        Intent intent = new Intent(this,SingleItemScrollViewActivity.class);
        startActivity(intent);
    }

    /**
     * 手势图片缩放
     * @param view
     */
    public void zoomImageView(View view){
        Intent intent = new Intent(this,ZoomImageViewActivity.class);
        startActivity(intent);
    }

    /**
     * 头像剪切
     * @param view
     */
    public void clipImageBorderView(View view){
        Intent intent = new Intent(this,ClipImageBorderViewActivity.class);
        startActivity(intent);
    }

    /**
     * 2048游戏
     * @param view
     */
    public void game2048(View view){
        Intent intent = new Intent(this,Game2048Activity.class);
        startActivity(intent);
    }
}
