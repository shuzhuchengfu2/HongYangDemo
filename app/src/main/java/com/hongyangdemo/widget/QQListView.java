package com.hongyangdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hongyangdemo.R;

/**
 * author： xiongdejin
 * date: 2017/7/31
 * describe:
 */

public class QQListView extends ListView {
    private static final String TAG = "QQlistView";

    /**
     * 用户滑动的最小距离
     */
    private int touchSlop;

    /**
     * 是否响应滑动
     */
    private boolean isSliding;

    /**
     * 手指按下时X坐标
     */
    private int xDown;

    /**
     * 手指按下时Y坐标
     */
    private int yDown;

    /**
     * 手指移动时X坐标
     */
    private int xMove;

    /**
     * 手指移动时Y坐标
     */
    private int yMove;

    private LayoutInflater mInflater;

    private PopupWindow mPopupWindow;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;

    private Button mDelBtn;
    /**
     * 为删除按钮提供一个回调接口
     */
    private DelButtonClickListener mListener;

    /**
     * 当前手指触摸的View
     */
    private View mCurrentView;

    /**
     * 当前手指触摸的位置
     */
    private int mCurrentViewPos;


    public QQListView(Context context) {
        this(context,null);
    }

    public QQListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        mInflater = LayoutInflater.from(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        View view = mInflater.inflate(R.layout.delete_btn, null);
        mDelBtn = (Button) view.findViewById(R.id.id_item_btn);
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        /**
         * 先调用下measure,否则拿不到宽和高
         */
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindowHeight = mPopupWindow.getContentView().getMeasuredHeight();
        mPopupWindowWidth = mPopupWindow.getContentView().getMeasuredWidth();
        Log.d(TAG,"mPopupWindowHeight:"+mPopupWindowHeight+",mPopupWindowWidth:"+mPopupWindowWidth);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int x = (int)ev.getX();
        int y = (int)ev.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                if(mPopupWindow.isShowing()){
                    dismissPopWindow();
                    return false;
                }
                //获取当前手指按下的item位置
                mCurrentViewPos = pointToPosition(xDown,yDown);
                View view = getChildAt(mCurrentViewPos - getFirstVisiblePosition());
                mCurrentView = view;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                //判断是否是从右到左滑动
                Log.d(TAG,"xDown:"+xDown+",xMove:"+xMove+",touchSlop:"+touchSlop+",dx:"+dx+",dy:"+dy);
                if(xMove<xDown && Math.abs(dx) > touchSlop && Math.abs(dy)<touchSlop){
                    isSliding = true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        //从右向左才响应
        if(isSliding){
            Log.d(TAG,"有响应了！");
            switch (action){
                case MotionEvent.ACTION_MOVE:
                    int [] location = new int [2];
                    if(mCurrentView == null || mPopupWindow == null) break;
                    mCurrentView.getLocationOnScreen(location);
                    //设置动画
                    mPopupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
                    mPopupWindow.update();
                    mPopupWindow.showAtLocation(mCurrentView, Gravity.LEFT|Gravity.TOP,
                            location[0]+mCurrentView.getWidth(),
                            location[1]+mCurrentView.getHeight()-mPopupWindowHeight/2);
                    Log.d(TAG,"x:"+(location[0]+mCurrentView.getWidth())+",y:"+(location[1]+mCurrentView.getHeight()-mPopupWindowHeight/2));
                    mDelBtn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(mListener != null){
                                mListener.clickHappend(mCurrentViewPos);
                                dismissPopWindow();
                            }
                        }
                    });
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    isSliding = false;
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 隐藏popupWindow
     */
    private void dismissPopWindow(){
        if (mPopupWindow != null && mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
        }
    }

    ////////////////////////删除按钮的回调///////////////////////////////////////////////////////

    public void setDelButtonClickListener(DelButtonClickListener listener){
        mListener = listener;
    }

    public interface DelButtonClickListener{
        public void clickHappend(int position);
    }
}
