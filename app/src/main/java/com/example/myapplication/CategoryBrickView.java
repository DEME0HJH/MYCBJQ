package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class CategoryBrickView extends LinearLayout {

    private ImageView ivIcon;
    private TextView tvName;

    public CategoryBrickView(Context context) {
        super(context);
        initView(context);
    }

    public CategoryBrickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CategoryBrickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    // 初始化布局
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_category_brick, this, true);
        ivIcon = findViewById(R.id.iv_brick_icon);
        tvName = findViewById(R.id.tv_brick_name);
    }

    // 配置砖块属性（对外暴露的方法）
    public void setBrickAttrs(int iconResId, String name, int bgColor) {
        ivIcon.setImageResource(iconResId);
        tvName.setText(name);
        // 修正：直接在当前View中找到CardView（无需通过android.R.id.content）
        androidx.cardview.widget.CardView cardView = findViewById(R.id.card_brick);
        if (cardView != null) {
            cardView.setCardBackgroundColor(bgColor); // CardView需用setCardBackgroundColor方法，不是setBackgroundColor
        }
    }

    // 设置点击事件（跳转逻辑）
    public void setOnBrickClickListener(OnClickListener listener) {
        this.setOnClickListener(listener);
    }
}