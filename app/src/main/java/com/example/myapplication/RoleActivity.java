package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView; // 正确：AndroidX版
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RoleActivity extends AppCompatActivity {

    private RecyclerView rvRoleList;
    private RoleAdapter roleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // 初始化搜索栏
        initSearchView();
        // 初始化筛选区
        initFilter();
        // 初始化角色列表
        initRoleList();
    }

    // 初始化搜索栏
    private void initSearchView() {
        SearchView searchView = findViewById(R.id.search_view_role);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 搜索角色逻辑
                filterRoleByKeyword(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 实时搜索逻辑
                filterRoleByKeyword(newText);
                return true;
            }
        });
    }

    // 初始化筛选区
    private void initFilter() {
        // 定位筛选逻辑（如点击“突击”筛选突击角色）
        findViewById(R.id.filter_assault).setOnClickListener(v -> {
            filterRoleByType("突击");
        });
    }

    // 初始化角色列表
    private void initRoleList() {
        rvRoleList = findViewById(R.id.rv_role_list);
        rvRoleList.setLayoutManager(new GridLayoutManager(this, 2)); // 2列网格
        // 模拟角色数据
        List<RoleBean> roleList = new ArrayList<>();
        roleList.add(new RoleBean("安卡希雅", "突击", "电", 5, R.drawable.role_placeholder));
        roleList.add(new RoleBean("芬妮", "侦查", "火", 5, R.drawable.role_placeholder));
        // 设置Adapter
        roleAdapter = new RoleAdapter(roleList);
        rvRoleList.setAdapter(roleAdapter);
        // 列表项点击跳转到角色详情页
        roleAdapter.setOnItemClickListener(roleBean -> {
            Intent intent = new Intent(this, RoleDetailActivity.class);
            intent.putExtra("role_name", roleBean.getName());
            startActivity(intent);
        });
    }

    // 按关键词筛选角色
    private void filterRoleByKeyword(String keyword) {
        // 筛选逻辑（省略，根据keyword过滤列表）
    }

    // 按类型筛选角色
    private void filterRoleByType(String type) {
        // 筛选逻辑（省略，根据type过滤列表）
    }
}