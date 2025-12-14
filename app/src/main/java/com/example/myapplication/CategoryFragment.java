package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private RecyclerView rvCategoryList;
    private CategoryAdapter categoryAdapter;

    // 分类数据（类型、图标、跳转目标）
    // 在CategoryFragment的CATEGORY_LIST中替换图标资源
    private final List<CategoryBean> CATEGORY_LIST = new ArrayList<>() {{
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
        rvCategoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryAdapter = new CategoryAdapter(CATEGORY_LIST);
        rvCategoryList.setAdapter(categoryAdapter);

        // 条目点击跳转
        categoryAdapter.setOnItemClickListener(bean -> {
            Intent intent = new Intent(getContext(), bean.getTargetActivity());
            startActivity(intent);
        });
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

        // Getter方法
        public String getName() { return name; }
        public int getIconResId() { return iconResId; }
        public int getColorResId() { return colorResId; }
        public Class<?> getTargetActivity() { return targetActivity; }
    }

    // 分类列表Adapter
    private static class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
        private List<CategoryBean> mList;
        private OnItemClickListener mListener;

        public CategoryAdapter(List<CategoryBean> list) {
            this.mList = list;
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.mListener = listener;
        }

        @Override
        public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_category, parent, false);
            return new CategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryViewHolder holder, int position) {
            CategoryBean bean = mList.get(position);
            // 绑定数据
            holder.ivIcon.setImageResource(bean.getIconResId());
            holder.ivIcon.setBackgroundColor(holder.itemView.getContext().getResources().getColor(bean.getColorResId()));
            holder.tvName.setText(bean.getName());


            // 最后一条隐藏分隔线
            holder.vDivider.setVisibility(position == mList.size() - 1 ? View.GONE : View.VISIBLE);

            // 点击事件
            holder.itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onItemClick(bean);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        static class CategoryViewHolder extends RecyclerView.ViewHolder {
            ImageView ivIcon;
            TextView tvName;
            View vDivider;

            public CategoryViewHolder(View itemView) {
                super(itemView);
                ivIcon = itemView.findViewById(R.id.iv_category_icon);
                tvName = itemView.findViewById(R.id.tv_category_name);
                vDivider = itemView.findViewById(R.id.v_divider);
            }
        }

        public interface OnItemClickListener {
            void onItemClick(CategoryBean bean);
        }
    }
}