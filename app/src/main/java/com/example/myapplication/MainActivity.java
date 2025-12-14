package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private MineFragment mineFragment;
    // 记录当前显示的Fragment
    private Fragment currentFragment;

    Button ButtonToPage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 初始化Fragment
        initFragments();
        // 初始化底部导航栏
        initBottomNav();
        // 默认显示首页
        switchFragment(homeFragment);

//        ButtonToPage1 = findViewById(R.id.ButtonToPage1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        ButtonToPage1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Page1Activity.class);
//
//                intent.putExtra("key", "传给action_page1页面的数据");
//
//                // 启动新页面
//                startActivity(intent);
//            }
//        });
    }

    /**
     * 通用方法：初始化所有Fragment（可复用，只需替换Fragment类）
     */
    private void initFragments() {
        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        mineFragment = new MineFragment();
        // 初始时将所有Fragment加入管理器（避免重复创建）
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, homeFragment);
        ft.add(R.id.fragment_container, categoryFragment);
        ft.add(R.id.fragment_container, mineFragment);
        ft.hide(categoryFragment);
        ft.hide(mineFragment);
        ft.commit();
        // 标记当前显示的Fragment
        currentFragment = homeFragment;
    }

    /**
     * 通用方法：初始化底部导航栏（可复用，只需修改菜单id和Fragment对应关系）
     */
    private void initBottomNav() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        // 新版监听：无需@NonNull注解，直接用Lambda表达式
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                switchFragment(homeFragment);
            } else if (itemId == R.id.nav_category) {
                switchFragment(categoryFragment);
            } else if (itemId == R.id.nav_mine) {
                switchFragment(mineFragment);
            }
            return true;
        });
    }

    /**
     * 通用Fragment切换方法（核心复用逻辑）
     * @param targetFragment 要切换的目标Fragment
     */
    public void switchFragment(Fragment targetFragment) {
        if (currentFragment == targetFragment) {
            return; // 避免重复切换
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 隐藏当前Fragment
        ft.hide(currentFragment);
        // 如果目标Fragment未添加，先添加
        if (!targetFragment.isAdded()) {
            ft.add(R.id.fragment_container, targetFragment);
        }
        // 显示目标Fragment
        ft.show(targetFragment);
        ft.commit();
        // 更新当前Fragment标记
        currentFragment = targetFragment;
    }


    // MainActivity.java中添加
    public CategoryFragment getCategoryFragment() {
        return categoryFragment;
    }
}