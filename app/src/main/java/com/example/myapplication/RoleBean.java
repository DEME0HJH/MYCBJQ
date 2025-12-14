package com.example.myapplication;

// 角色数据Bean类
public class RoleBean {
    private String name;    // 角色名
    private String type;    // 定位（突击/侦查等）
    private String element; // 元素属性
    private int star;       // 星级
    private int resId;      // 立绘资源ID

    // 构造方法
    public RoleBean(String name, String type, String element, int star, int resId) {
        this.name = name;
        this.type = type;
        this.element = element;
        this.star = star;
        this.resId = resId;
    }

    // Getter方法（提供属性访问）
    public String getName() { return name; }
    public String getType() { return type; }
    public String getElement() { return element; }
    public int getStar() { return star; }
    public int getResId() { return resId; }
}