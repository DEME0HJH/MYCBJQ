package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.RoleViewHolder> {
    private List<RoleBean> mRoleList;
    private OnItemClickListener mListener;

    // 构造方法
    public RoleAdapter(List<RoleBean> roleList) {
        this.mRoleList = roleList;
    }

    // 设置点击监听
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    // 新增：更新列表数据
    public void updateData(List<RoleBean> newList) {
        if (newList == null) {
            this.mRoleList.clear();
        } else {
            this.mRoleList.clear();
            this.mRoleList.addAll(newList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_role, parent, false);
        return new RoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        // 空数据保护
        if (mRoleList == null || mRoleList.isEmpty() || position >= mRoleList.size()) {
            return;
        }

        RoleBean role = mRoleList.get(position);
        // 绑定立绘（默认占位图）
        if (role.getResId() != 0) {
            holder.ivRole.setImageResource(role.getResId());
        } else {
            holder.ivRole.setImageResource(R.drawable.role_placeholder);
        }
        // 绑定核心信息（角色名/武装名/武器类型/伤害类型）
        holder.tvName.setText(role.getName());
        holder.tvArmedName.setText("武装：" + role.getArmedName());
        holder.tvWeaponType.setText("武器：" + role.getWeaponType());
        holder.tvDamageType.setText("伤害：" + role.getDamageType());
        // 绑定核心属性（攻击力）
        holder.tvAttack.setText("攻击力：" + role.getAttack());

        // 点击事件
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null && role != null) {
                mListener.onItemClick(role);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRoleList == null ? 0 : mRoleList.size();
    }

    // ViewHolder（扩展新属性展示控件）
    static class RoleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRole;
        TextView tvName;
        TextView tvArmedName;
        TextView tvWeaponType;
        TextView tvDamageType;
        TextView tvAttack;

        public RoleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRole = itemView.findViewById(R.id.iv_role);
            tvName = itemView.findViewById(R.id.tv_role_name);
            tvArmedName = itemView.findViewById(R.id.tv_role_armed_name);
            tvWeaponType = itemView.findViewById(R.id.tv_role_weapon_type);
            tvDamageType = itemView.findViewById(R.id.tv_role_damage_type);
            tvAttack = itemView.findViewById(R.id.tv_role_attack);
        }
    }

    // 点击回调接口
    public interface OnItemClickListener {
        void onItemClick(RoleBean roleBean);
    }
}