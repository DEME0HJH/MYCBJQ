package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// 角色列表适配器
public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.RoleViewHolder> {
    private List<RoleBean> mRoleList;
    private OnItemClickListener mListener; // 点击事件回调

    // 构造方法
    public RoleAdapter(List<RoleBean> roleList) {
        this.mRoleList = roleList;
    }

    // 设置点击事件监听
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载角色列表项布局（后续需创建item_role.xml）
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_role, parent, false);
        return new RoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        RoleBean role = mRoleList.get(position);
        // 绑定数据到UI
        holder.ivRole.setImageResource(role.getResId());
        holder.tvName.setText(role.getName());
        holder.tvType.setText(role.getType());

        // 列表项点击事件
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(role);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRoleList.size();
    }

    // 列表项ViewHolder
    static class RoleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRole;
        TextView tvName;
        TextView tvType;

        public RoleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRole = itemView.findViewById(R.id.iv_role);
            tvName = itemView.findViewById(R.id.tv_role_name);
            tvType = itemView.findViewById(R.id.tv_role_type);
        }
    }

    // 点击事件回调接口
    public interface OnItemClickListener {
        void onItemClick(RoleBean roleBean);
    }
}