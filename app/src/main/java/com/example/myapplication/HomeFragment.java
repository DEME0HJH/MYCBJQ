package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView; // 关键：导入AndroidX版SearchView
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // 1. 搜索栏交互（正确引用AndroidX版SearchView）
        SearchView searchView = rootView.findViewById(R.id.search_view);
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(getContext(), "搜索：" + query, Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        // 2. 功能按钮点击（角色图鉴跳分类页）
        GridLayout functionGrid = (GridLayout) rootView.findViewById(R.id.function_grid);
        if (functionGrid != null) {
            functionGrid.getChildAt(0).setOnClickListener(v -> {
                if (getActivity() != null && getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.switchFragment(mainActivity.getCategoryFragment());
                }
            });
        }

        // 3. TOP3角色卡片点击
        ViewGroup top3Scroll = (ViewGroup) rootView.findViewById(R.id.top3_scroll);
        if (top3Scroll != null) {
            ViewGroup top3Container = (ViewGroup) top3Scroll.getChildAt(0);
            top3Container.getChildAt(0).setOnClickListener(v ->
                    Toast.makeText(getContext(), "查看安卡希雅详情", Toast.LENGTH_SHORT).show()
            );
        }

        return rootView;
    }
}