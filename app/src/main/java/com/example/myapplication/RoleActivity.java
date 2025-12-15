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
    private List<RoleBean> originalRoleList; // 原始完整数据
    private String currentFilterType = "";   // 当前选中的筛选类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // 初始化原始数据（含全量属性）
        initOriginalRoleData();
        // 初始化搜索栏
        initSearchView();
        // 初始化筛选区
        initFilter();
        // 初始化角色列表
        initRoleList();
    }

    // 初始化原始角色数据（补充全量属性）
    private void initOriginalRoleData() {
        originalRoleList = new ArrayList<>();

        // 示例1：安卡希雅（突击步枪）
        originalRoleList.add(new RoleBean(
                "安卡希雅",                // 角色名
                "深空之眼",               // 武装名
                "突击步枪",               // 武器类型
                "电击",                   // 伤害类型
                R.drawable.role_placeholder, // 新增：立绘占位图ID
                // 基础属性
                8500, 1200, 650,
                // 特殊属性
                0.15f, 0.50f, 1.20f, 100,
                0.10f, 0.08f, 0.05f, 0.20f, 0.12f, 0.08f, 1.5f, 0.9f, 1.10f,
                // 枪械属性
                0.95f, 6.5f, "电击", 80.0f, 30, 2.1f, 0.45f, "步枪弹"
        ));

        // 示例2：芬妮（狙击枪）
        originalRoleList.add(new RoleBean(
                "芬妮",                   // 角色名
                "星落",                   // 武装名
                "狙击枪",                 // 武器类型
                "高热",                   // 伤害类型
                R.drawable.role_placeholder, // 新增：立绘占位图ID
                // 基础属性
                7200, 1800, 500,
                // 特殊属性
                0.25f, 0.80f, 0.80f, 80,
                0.05f, 0.15f, 0.08f, 0.05f, 0.10f, 0.12f, 1.2f, 0.7f, 1.30f,
                // 枪械属性
                0.98f, 1.2f, "高热", 150.0f, 5, 3.5f, 0.75f, "狙击弹"
        ));

        // 示例3：露西（冲锋枪）
        originalRoleList.add(new RoleBean(
                "露西",                   // 角色名
                "疾光",                   // 武装名
                "冲锋枪",                 // 武器类型
                "低温",                   // 伤害类型
                R.drawable.role_placeholder, // 新增：立绘占位图ID
                // 基础属性
                7800, 950, 700,
                // 特殊属性
                0.20f, 0.40f, 1.50f, 120,
                0.08f, 0.05f, 0.18f, 0.10f, 0.08f, 0.10f, 1.8f, 1.1f, 0.95f,
                // 枪械属性
                0.92f, 12.0f, "低温", 40.0f, 45, 1.8f, 0.35f, "冲锋枪弹"
        ));

        // 可继续添加其他角色...
    }

    // 初始化搜索栏（兼容新属性：支持搜索武装名）
    private void initSearchView() {
        SearchView searchView = findViewById(R.id.search_view_role);
        searchView.setIconifiedByDefault(false); // 默认展开搜索框
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

    // 初始化筛选区（更新武器类型匹配值，兼容新命名）
    private void initFilter() {
        // 绑定所有筛选标签点击事件（匹配新武器类型命名）
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

    // 通用筛选标签绑定方法
    private void bindFilterTag(int viewId, String type) {
        TextView tagView = findViewById(viewId);
        tagView.setOnClickListener(v -> {
            resetAllFilterTags(); // 重置所有标签
            v.setSelected(true);  // 选中当前标签
            currentFilterType = type; // 更新筛选类型
            // 执行筛选
            String keyword = ((SearchView) findViewById(R.id.search_view_role)).getQuery().toString();
            filterRoleByKeywordAndType(keyword, type);
        });
    }

    // 重置所有筛选标签为未选中
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

    // 初始化角色列表
    private void initRoleList() {
        rvRoleList = findViewById(R.id.rv_role_list);
        // 适配屏幕：小屏2列，大屏3列
        int columnCount = getResources().getConfiguration().screenWidthDp >= 600 ? 3 : 2;
        rvRoleList.setLayoutManager(new GridLayoutManager(this, columnCount));
        // 设置适配器
        roleAdapter = new RoleAdapter(new ArrayList<>(originalRoleList));
        rvRoleList.setAdapter(roleAdapter);
        // 列表项点击跳转详情页
        roleAdapter.setOnItemClickListener(roleBean -> {
            Intent intent = new Intent(this, RoleDetailActivity.class);
            intent.putExtra("role_bean", roleBean); // 传递完整角色对象
            startActivity(intent);
        });
    }

    // 关键词+类型双重筛选（支持搜索角色名/武装名）
    private void filterRoleByKeywordAndType(String keyword, String type) {
        List<RoleBean> filteredList = new ArrayList<>();
        for (RoleBean bean : originalRoleList) {
            // 关键词匹配（角色名/武装名，忽略大小写）
            boolean keywordMatch = keyword.isEmpty() ||
                    bean.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    bean.getArmedName().toLowerCase().contains(keyword.toLowerCase());
            // 类型匹配（空=不筛选）
            boolean typeMatch = type.isEmpty() || bean.getWeaponType().equals(type);
            if (keywordMatch && typeMatch) {
                filteredList.add(bean);
            }
        }
        roleAdapter.updateData(filteredList); // 更新列表
    }

    // 兼容原有筛选方法
    private void filterRoleByKeyword(String keyword) {
        filterRoleByKeywordAndType(keyword, currentFilterType);
    }

    private void filterRoleByType(String type) {
        String keyword = ((SearchView) findViewById(R.id.search_view_role)).getQuery().toString();
        filterRoleByKeywordAndType(keyword, type);
    }
}