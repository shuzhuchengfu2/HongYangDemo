package com.hongyangdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author： xiongdejin
 * date: 2017/9/7
 * describe:
 */

public class Game2048Layout extends RelativeLayout {
    public interface OnGame2048Listener {
        /**
         * 游戏结束
         */
        void onGameOver();

        /**
         * 加分
         *
         * @param score
         */
        void onScoreChange(int score);

    }

    public void setOnGame2048Listener(OnGame2048Listener mGame2048Listener) {
        this.mGame2048Listener = mGame2048Listener;
    }


    /**
     * 设置Item的数量n*n；默认为4
     */
    private int mColumn = 4;
    /**
     * 存放所有的Item
     */
    private Game2048Item[] mGame2048Items;

    /**
     * Item横向与纵向的边距
     */
    private int mMargin = 10;
    /**
     * 面板的padding
     */
    private int mPadding;
    /**
     * 检测用户滑动的手势
     */
    private GestureDetector mGestureDetector;

    private OnGame2048Listener mGame2048Listener;

    // 用于确认是否需要生成一个新的值
    /**
     * 是否可以可并数字 true可以
     */
    private boolean isMergeHappen = true;
    /**
     * 是否可以移动 true 可以
     */
    private boolean isMoveHappen = true;
    private boolean once;
    /**
     * 记录分数
     */
    private int mScore;

    /**
     * 运动方向的枚举
     *
     * @author zhy
     */
    private enum ACTION {
        LEFT, RIGHT, UP, DOWM
    }

    public Game2048Layout(Context context) {
        this(context,null);
    }

    public Game2048Layout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Game2048Layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                mMargin, getResources().getDisplayMetrics());
        // 设置Layout的内边距，四边一致，设置为四内边距中的最小值
        mPadding = min(getPaddingLeft(), getPaddingTop(), getPaddingRight(),
                getPaddingBottom());
        mGestureDetector = new GestureDetector(context, new MyGestureDetector());
    }

    /**
     * 得到多值中的最小值
     *
     * @param params
     * @return
     */
    private int min(int... params) {
        int min = params[0];
        for (int param : params) {
            if (min > param) {
                min = param;
            }
        }
        return min;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mGestureDetector == null) return super.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        final int FLING_MIN_DISTANCE = 50;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            if (x > FLING_MIN_DISTANCE && Math.abs(velocityX) > Math.abs(velocityY)) {
                action(ACTION.RIGHT);
            }
            else if (x < -FLING_MIN_DISTANCE && Math.abs(velocityX) > Math.abs(velocityY)) {
                action(ACTION.LEFT);
            }
            else if (y > FLING_MIN_DISTANCE && Math.abs(velocityX) < Math.abs(velocityY)) {
                action(ACTION.DOWM);
            }
            else if (y < -FLING_MIN_DISTANCE && Math.abs(velocityX) < Math.abs(velocityY)) {
                action(ACTION.UP);
            }
            return true;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得正方形的边长
        int length = Math.min(getMeasuredHeight(), getMeasuredWidth());
        // 获得Item的宽度
        int childWidth = (length - mPadding * 2 - mMargin * (mColumn - 1)) / mColumn;
        if (!once) {
            if (mGame2048Items == null) {
                mGame2048Items = new Game2048Item[mColumn * mColumn];
            }
            //放置item
            for (int i = 0; i < mGame2048Items.length; i++) {
                Game2048Item item = new Game2048Item(getContext());
                mGame2048Items[i] = item;
                item.setId(i + 1);
                RelativeLayout.LayoutParams lp = new LayoutParams(childWidth, childWidth);
                // 设置横向边距,不是最后一列
                if ((i + 1) % mColumn != 0) {
                    lp.rightMargin = mMargin;
                }

                // 如果不是第一列
                if (i % mColumn != 0) {
                    lp.addRule(RelativeLayout.RIGHT_OF, mGame2048Items[i - 1].getId());
                }

                // 如果不是第一行，//设置纵向边距，非最后一行
                if ((i + 1) > mColumn) {
                    lp.topMargin = mMargin;
                    lp.addRule(RelativeLayout.BELOW, mGame2048Items[i - mColumn].getId());
                }
                addView(item, lp);
            }
            generateNum();
        }
        once = true;
        setMeasuredDimension(length, length);
    }

    /**
     * 是否填满数字
     *
     * @return
     */
    private boolean isFull() {
        // 检测是否所有位置都有数字
        for (int i = 0; i < mGame2048Items.length; i++) {
            if (mGame2048Items[i].getNumber() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查是否已经填满了数字，并且相邻的没有相同的数字
     *
     * @return
     */
    private boolean checkOver() {
        //是否所有都填满数字
        if (!isFull()) {
            return false;
        }

        for (int i = 0; i < mColumn; i++) {
            for (int j = 0; j < mColumn; j++) {
                int index = i * mColumn + j;
                //当前的Item
                Game2048Item item = mGame2048Items[index];
                //右边
                if ((index + 1) % mColumn != 0) {
                    Log.e("TAG", "RIGHT");
                    // 右边的Item
                    Game2048Item itemRight = mGame2048Items[index + 1];
                    if (item.getNumber() == itemRight.getNumber())
                        return false;
                }

                //下边
                if (index + mColumn < mColumn * mColumn) {
                    Log.e("TAG", "DOWN");
                    Game2048Item itemBottom = mGame2048Items[index + mColumn];
                    if (item.getNumber() == itemBottom.getNumber())
                        return false;
                }

                //左边
                if (index % mColumn != 0) {
                    Log.e("TAG", "LEFT");
                    Game2048Item itemLeft = mGame2048Items[index - 1];
                    if (itemLeft.getNumber() == item.getNumber())
                        return false;
                }

                //上面
                if (index + 1 > mColumn) {
                    Log.e("TAG", "UP");
                    Game2048Item itemTop = mGame2048Items[index - mColumn];
                    if (item.getNumber() == itemTop.getNumber())
                        return false;
                }
            }
        }
        return true;
    }


    /**
     * 生成一个数字
     */
    private void generateNum() {
        if (checkOver()) {
            Log.e("TAG", "GAME OVER");
            if (mGame2048Listener != null) {
                mGame2048Listener.onGameOver();
            }
            return;
        }

        if (!isFull()) {
            if (isMoveHappen || isMergeHappen) {
                Random random = new Random();
                int next = random.nextInt(16);
                Game2048Item item = mGame2048Items[next];

                while (item.getNumber() != 0) { //随机选中没有数字的item
                    next = random.nextInt(16);
                    item = mGame2048Items[next];
                }

                item.setNumber(Math.random() > 0.75 ? 4 : 2);
                isMergeHappen = isMoveHappen = false;
            }
        }
    }


    /**
     * 合并相同的item
     *
     * @param row
     */
    private void mergeItem(List<Game2048Item> row) {
        if (row.size() < 2) {
            return;
        }

        for (int j = 0; j < row.size() - 1; j++) {
            Game2048Item item1 = row.get(j);
            Game2048Item item2 = row.get(j + 1);

            if (item1.getNumber() == item2.getNumber()) {
                isMergeHappen = true;

                int val = item1.getNumber() + item2.getNumber();
                item1.setNumber(val);

                // 加分
                mScore += val;
                if (mGame2048Listener != null) {
                    mGame2048Listener.onScoreChange(mScore);
                }

                // 向前移动
                for (int k = j + 1; k < row.size() - 1; k++) {
                    row.get(k).setNumber(row.get(k + 1).getNumber());
                }

                row.get(row.size() - 1).setNumber(0);
                return;
            }
        }
    }

    private void action(ACTION action) {

        for (int i = 0; i < mColumn; i++) {
            List<Game2048Item> row = new ArrayList<>();
            for (int j = 0; j < mColumn; j++) {
                int index = getIndexByAction(action, i, j);
                Log.d("TAG","index:"+index+",i:"+i+",j:"+j);
                Game2048Item item = mGame2048Items[index];
                // 记录不为0的数字
                if (item.getNumber() != 0) {
                    row.add(item);
                }
            }


            for (int j = 0; j < mColumn && j < row.size(); j++) {
                int index = getIndexByAction(action, i, j);
                Game2048Item item = mGame2048Items[index];
                if (item.getNumber() != row.get(j).getNumber()) {
                    //判断是否有空格
                    isMoveHappen = true;
                }
            }

            mergeItem(row);

            // 设置合并后的值
            for (int j = 0; j < mColumn; j++) {
                int index = getIndexByAction(action, i, j);
                if (row.size() > j) {
                    mGame2048Items[index].setNumber(row.get(j).getNumber());
                }
                else {
                    mGame2048Items[index].setNumber(0);
                }
            }
        }
        //生成数字
        generateNum();
    }

    /**
     * 根据Action和i,j得到下标
     *
     * 左滑 第一行左起，
     * 右滑 第一行右起，
     * 上滑 第一列上起，
     * 下滑 第四列上起，
     *
     * @param action
     * @param i 行
     * @param j 列
     * @return
     */
    private int getIndexByAction(ACTION action, int i, int j) {
        int index = -1;
        switch (action) {
            case LEFT:
                index = i * mColumn + j;
                break;
            case RIGHT:
                index = i * mColumn + mColumn - j - 1;
                break;
            case UP:
                index = i + j * mColumn;
                break;
            case DOWM:
                index = i + (mColumn - 1 - j) * mColumn;
                break;
        }
        return index;
    }

    /**
     * 重新开始游戏
     */
    public void restart() {
        for (Game2048Item item : mGame2048Items) {
            item.setNumber(0);
        }
        mScore = 0;
        if (mGame2048Listener != null) {
            mGame2048Listener.onScoreChange(mScore);
        }
        isMoveHappen = isMergeHappen = true;
        generateNum();
    }
}
