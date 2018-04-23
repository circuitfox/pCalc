package com.example.chris.pcalc;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.TextView;

public class MultiButtonView extends ConstraintLayout
        implements GestureDetector.OnGestureListener {
    private GestureDetectorCompat gestureDetector;

    private TextView topButtonText;
    private TextView bottomButtonText;
    private TextView leftButtonText;
    private TextView rightButtonText;
    private TextView centerButtonText;

    private String lastClicked;

    public MultiButtonView(Context context) {
        this(context, null);
    }

    public MultiButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.multiButtonViewStyle);
    }

    public MultiButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        lastClicked = "";
        gestureDetector = new GestureDetectorCompat(getContext(), this);
        gestureDetector.setIsLongpressEnabled(false);

        TypedArray attr = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.MultiButtonView,
                defStyleAttr,
                0
        );
        float smallTextSize = attr.getDimension(R.styleable.MultiButtonView_smallTextSize, 14);
        float textSize = attr.getDimension(R.styleable.MultiButtonView_textSize, 20);
        String topButtonTextStr = attr.getString(R.styleable.MultiButtonView_topButtonText);
        String bottomButtonTextStr = attr.getString(R.styleable.MultiButtonView_bottomButtonText);
        String leftButtonTextStr = attr.getString(R.styleable.MultiButtonView_leftButtonText);
        String rightButtonTextStr = attr.getString(R.styleable.MultiButtonView_rightButtonText);
        String centerButtonTextStr = attr.getString(R.styleable.MultiButtonView_android_text);
        attr.recycle();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.multi_button_view, this, true);

        topButtonText = findViewById(R.id.topValue);
        bottomButtonText = findViewById(R.id.bottomValue);
        leftButtonText = findViewById(R.id.leftValue);
        rightButtonText = findViewById(R.id.rightValue);
        centerButtonText = findViewById(R.id.centerValue);

        topButtonText.setTextSize(smallTextSize);
        topButtonText.setText(topButtonTextStr);

        bottomButtonText.setTextSize(smallTextSize);
        bottomButtonText.setText(bottomButtonTextStr);

        leftButtonText.setTextSize(smallTextSize);
        leftButtonText.setText(leftButtonTextStr);

        rightButtonText.setTextSize(smallTextSize);
        rightButtonText.setText(rightButtonTextStr);

        centerButtonText.setTextSize(textSize);
        centerButtonText.setText(centerButtonTextStr);
    }

    public CharSequence getText() {
        return lastClicked;
    }

    public void setText(String text) {
        centerButtonText.setText(text);
    }

    public CharSequence getTopButtonText() {
        return topButtonText.getText();
    }

    public CharSequence getBottomButtonText() {
        return bottomButtonText.getText();
    }

    public CharSequence getLeftButtonText() {
        return leftButtonText.getText();
    }

    public CharSequence getRightButtonText() {
        return rightButtonText.getText();
    }

    public CharSequence getCenterButtonText() {
        return centerButtonText.getText();
    }

    public void setTopButtonText(String text) {
        topButtonText.setText(text);
    }

    public void setBottomButtonText(String text) {
        bottomButtonText.setText(text);
    }

    public void setLeftButtonText(String text) {
        leftButtonText.setText(text);
    }

    public void setRightButtonText(String text) {
        rightButtonText.setText(text);
    }

    public void setCenterButtonText(String text) {
        centerButtonText.setText(text);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int action = e .getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_BUTTON_PRESS) {
            return performClick();
        }
        if (gestureDetector.onTouchEvent(e)) {
            return super.onTouchEvent(e);
        }
        return super.onTouchEvent(e);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {}

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        lastClicked = centerButtonText.getText().toString();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {}

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Direction flingDir = Direction.fromVectors(e1.getX(), e2.getX(), e1.getY(), e2.getY());
        switch (flingDir) {
            case Up:
                lastClicked = topButtonText.getText().toString();
                break;
            case Down:
                lastClicked = bottomButtonText.getText().toString();
                break;
            case Left:
                lastClicked = leftButtonText.getText().toString();
                break;
            case Right:
                lastClicked = rightButtonText.getText().toString();
                break;
        }
        return true;
    }

    private enum Direction {
        Up,
        Down,
        Left,
        Right;

        // Convert the vector {(x1, y1) (x2, y2)} to a direction.
        // The following diagram shows the angle in radians that
        // are used to bound the directions.
        //
        //   -3π/4    U    -π/4
        //       \    |    /
        //        \   |   /
        //         \  |  /
        //          \ | /
        //           \|/
        //     L -----+----- R
        //           /|\
        //          / | \
        //         /  |  \
        //        /   |   \
        //       /    |    \
        //    3π/4    D     π/4
        public static Direction fromVectors(float x1, float x2, float y1, float y2) {
            double rad = Math.atan2(y2 - y1, x2 - x1);
            double downRight = Math.PI / 4;
            double downLeft = 3 * Math.PI / 4;
            double upRight = -Math.PI / 4;
            double upLeft = -3 * Math.PI / 4;
            if (rad < upRight && rad >= upLeft) {
                return Up;
            } else if (rad < upLeft || rad >= downLeft) {
                return Left;
            } else if (rad > downRight) {
                return Down;
            } else {
                return Right;
            }
        }
    }
}
