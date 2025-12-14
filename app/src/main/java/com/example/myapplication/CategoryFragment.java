package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private RecyclerView rvCategoryList;

    // 分类数据（统一使用一份，避免重复定义）
    private final List<CategoryBean> CATEGORY_LIST = new ArrayList<>() {{
        // 先用系统图标（避免自定义图标缺失报错，后续替换为R.drawable.ic_role等）
        add(new CategoryBean("角色", android.R.drawable.ic_menu_myplaces, R.color.cbjq_blue, RoleActivity.class));
        add(new CategoryBean("武器", android.R.drawable.ic_menu_add, R.color.cbjq_light_blue, WeaponActivity.class));
        add(new CategoryBean("天启", android.R.drawable.ic_menu_share, R.color.cbjq_purple, ApocalypseActivity.class));
        add(new CategoryBean("后勤", android.R.drawable.ic_menu_help, R.color.cbjq_green, LogisticsActivity.class));
        add(new CategoryBean("神格", android.R.drawable.ic_menu_edit, R.color.cbjq_orange, GodheadActivity.class));
    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        rvCategoryList = rootView.findViewById(R.id.rv_category_list);

        // 初始化列表
        initCategoryList();

        return rootView;
    }

    private void initCategoryList() {
        // 1. 设置网格布局管理器（动态适配列数）
        int columnCount = getResources().getConfiguration().screenWidthDp >= 600 ? 3 : 2;
        rvCategoryList.setLayoutManager(new GridLayoutManager(getContext(), columnCount));

        // 2. 设置适配器
        rvCategoryList.setAdapter(new CategoryAdapter(CATEGORY_LIST));
    }

    // 分类数据Bean
    private static class CategoryBean {
        private String name;          // 类型名称
        private int iconResId;        // 图标资源
        private int colorResId;       // 图标背景色
        private Class<?> targetActivity; // 跳转目标

        public CategoryBean(String name, int iconResId, int colorResId, Class<?> targetActivity) {
            this.name = name;
            this.iconResId = iconResId;
            this.colorResId = colorResId;
            this.targetActivity = targetActivity;
        }

        // Getter方法（统一命名，避免调用时出错）
        public String getName() { return name; }
        public int getIconResId() { return iconResId; }
        public int getColorResId() { return colorResId; }
        public Class<?> getTargetActivity() { return targetActivity; }
    }

    // 分类列表Adapter（修正所有语法和逻辑错误）
    private static class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
        private List<CategoryBean> mList;

        public CategoryAdapter(List<CategoryBean> list) {
            this.mList = list;
        }

        @NonNull
        @Override
        public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 加载分类项布局
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_category, parent, false);
            return new CategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
            CategoryBean bean = mList.get(position);
            // 设置分类名称
            holder.tvName.setText(bean.getName());
            // 设置图标
            holder.ivIcon.setImageResource(bean.getIconResId());

            // 动态设置圆形图标背景色（替代shape_circle.xml，避免资源缺失）
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE); // 矩形（替代圆形）
            int cornerRadius = holder.itemView.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.dp_12);
            drawable.setCornerRadius(cornerRadius);
            drawable.setColor(ContextCompat.getColor(holder.itemView.getContext(), bean.getColorResId()));
            holder.ivIcon.setBackground(drawable);

//            // 隐藏分隔线（网格布局不需要分隔线）
//            if (holder.vDivider != null) {
//                holder.vDivider.setVisibility(View.GONE);
//            }

            // 点击跳转事件
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), bean.getTargetActivity());
                v.getContext().startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size(); // 避免空指针
        }

        // 统一ViewHolder类名，避免冲突
        static class CategoryViewHolder extends RecyclerView.ViewHolder {
            ImageView ivIcon;
            TextView tvName;
            View vDivider;

            public CategoryViewHolder(@NonNull View itemView) {
                super(itemView);
                // 绑定布局控件（与item_category.xml中的ID对应）
                ivIcon = itemView.findViewById(R.id.iv_category_icon);
                tvName = itemView.findViewById(R.id.tv_category_name);
//                // 兼容原有分隔线控件（避免找不到ID报错）
//                try {
//                    vDivider = itemView.findViewById(R.id.v_divider);
//                } catch (Exception e) {
//                    vDivider = null;
//                }
            }
        }
    }
}