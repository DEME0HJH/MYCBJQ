package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RoleActivity extends AppCompatActivity {

    private RecyclerView rvRoleList;
    private RoleAdapter roleAdapter;
    private AppDatabase db;
    private String currentFilterType = "";   // 当前选中的筛选类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // 初始化数据库
        db = AppDatabase.getInstance(this);
        // 初始化搜索栏
        initSearchView();
        // 初始化筛选区
        initFilter();
        // 初始化角色列表（从数据库读取）
        initRoleList();
    }

    // 初始化搜索栏
    private void initSearchView() {
        SearchView searchView = findViewById(R.id.search_view_role);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterRoleByKeywordAndType(query, currentFilterType);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterRoleByKeywordAndType(newText, currentFilterType);
                return true;
            }
        });
    }

    // 初始化筛选区
    private void initFilter() {
        bindFilterTag(R.id.filter_rifle, "突击步枪");
        bindFilterTag(R.id.filter_sniper, "狙击枪");
        bindFilterTag(R.id.filter_smg, "冲锋枪");
        bindFilterTag(R.id.filter_shotgun, "霰弹枪");
        bindFilterTag(R.id.filter_pistol, "手枪");
        bindFilterTag(R.id.filter_dual_smg, "双枪冲锋枪");
        bindFilterTag(R.id.filter_crossbow, "弩");
        bindFilterTag(R.id.filter_bow, "弓");
        bindFilterTag(R.id.filter_gatling, "格林机枪");
    }

    // 通用筛选标签绑定
    private void bindFilterTag(int viewId, String type) {
        TextView tagView = findViewById(viewId);
        tagView.setOnClickListener(v -> {
            resetAllFilterTags();
            v.setSelected(true);
            currentFilterType = type;
            // 筛选数据
            String keyword = ((SearchView) findViewById(R.id.search_view_role)).getQuery().toString();
            filterRoleByKeywordAndType(keyword, type);
        });
    }

    // 重置筛选标签
    private void resetAllFilterTags() {
        findViewById(R.id.filter_rifle).setSelected(false);
        findViewById(R.id.filter_sniper).setSelected(false);
        findViewById(R.id.filter_smg).setSelected(false);
        findViewById(R.id.filter_shotgun).setSelected(false);
        findViewById(R.id.filter_pistol).setSelected(false);
        findViewById(R.id.filter_dual_smg).setSelected(false);
        findViewById(R.id.filter_crossbow).setSelected(false);
        findViewById(R.id.filter_bow).setSelected(false);
        findViewById(R.id.filter_gatling).setSelected(false);
    }

    // 初始化角色列表（从数据库读取）
    private void initRoleList() {
        rvRoleList = findViewById(R.id.rv_role_list);
        int columnCount = getResources().getConfiguration().screenWidthDp >= 600 ? 3 : 2;
        rvRoleList.setLayoutManager(new GridLayoutManager(this, columnCount));

        // 从数据库读取所有角色
        List<RoleEntity> entityList = db.roleDao().getAllRoles();
        List<RoleBean> roleList = new ArrayList<>();
        for (RoleEntity entity : entityList) {
            roleList.add(entity.toRoleBean());
        }

        // 设置适配器
        roleAdapter = new RoleAdapter(roleList);
        rvRoleList.setAdapter(roleAdapter);

        // 点击跳转详情页
        roleAdapter.setOnItemClickListener(roleBean -> {
            Intent intent = new Intent(this, RoleDetailActivity.class);
            intent.putExtra("role_bean", roleBean);
            startActivity(intent);
        });
    }

    // 组合筛选：关键词 + 武器类型
    private void filterRoleByKeywordAndType(String keyword, String type) {
        List<RoleEntity> entityList;
        if (keyword.isEmpty() && type.isEmpty()) {
            // 无筛选：查所有
            entityList = db.roleDao().getAllRoles();
        } else if (keyword.isEmpty()) {
            // 仅类型筛选
            entityList = db.roleDao().getRolesByWeaponType(type);
        } else if (type.isEmpty()) {
            // 仅关键词筛选
            entityList = db.roleDao().getRolesByKeyword(keyword);
        } else {
            // 组合筛选
            entityList = db.roleDao().getRolesByKeywordAndWeaponType(keyword, type);
        }

        // 转换为RoleBean，更新列表
        List<RoleBean> filteredList = new ArrayList<>();
        for (RoleEntity entity : entityList) {
            filteredList.add(entity.toRoleBean());
        }
        roleAdapter.updateData(filteredList);
    }
}