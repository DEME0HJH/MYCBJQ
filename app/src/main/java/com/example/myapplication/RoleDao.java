package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/**
 * Role Dao：定义所有数据库操作（增删改查）
 */
@Dao
public interface RoleDao {
    // 插入单条角色数据
    @Insert
    void insertRole(RoleEntity role);

    // 批量插入（参数是List<RoleEntity>）
    @Insert
    void insertRoles(List<RoleEntity> roles);

    // 查询所有角色
    @Query("SELECT * FROM role")
    List<RoleEntity> getAllRoles();

    // 按武器类型筛选角色
    @Query("SELECT * FROM role WHERE weaponType = :weaponType")
    List<RoleEntity> getRolesByWeaponType(String weaponType);

    // 按关键词（角色名/武装名）筛选
    @Query("SELECT * FROM role WHERE name LIKE '%' || :keyword || '%' OR armedName LIKE '%' || :keyword || '%'")
    List<RoleEntity> getRolesByKeyword(String keyword);

    // 组合筛选：关键词 + 武器类型
    @Query("SELECT * FROM role WHERE (name LIKE '%' || :keyword || '%' OR armedName LIKE '%' || :keyword || '%') AND weaponType = :weaponType")
    List<RoleEntity> getRolesByKeywordAndWeaponType(String keyword, String weaponType);

    // 清空所有角色数据（可选）
    @Query("DELETE FROM role")
    void deleteAllRoles();
}