package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initDatabase();
    }

    public static Context getContext() {
        return mContext;
    }

    private void initDatabase() {
        AppDatabase db = AppDatabase.getInstance(mContext);
        if (db.roleDao().getAllRoles().isEmpty()) {
            List<JsonParser.RoleBeanJson> jsonList = JsonParser.parseRoleData(mContext);
            if (jsonList != null) {
                List<RoleEntity> roleEntities = new ArrayList<>();
                for (JsonParser.RoleBeanJson jsonBean : jsonList) {
                    RoleEntity entity = new RoleEntity(
                            jsonBean.name,
                            jsonBean.armedName,
                            jsonBean.weaponType,
                            jsonBean.damageType,
                            jsonBean.resId,
                            jsonBean.maxHp,
                            jsonBean.attack,
                            jsonBean.defense,
                            jsonBean.critRate,
                            jsonBean.critDamageBoost,
                            jsonBean.burstEnergyEfficiency,
                            jsonBean.stamina,
                            jsonBean.kineticResistance,
                            jsonBean.heatResistance,
                            jsonBean.coldResistance,
                            jsonBean.electricResistance,
                            jsonBean.specialResistance,
                            jsonBean.skillHaste,
                            jsonBean.normalEnergyRecovery,
                            jsonBean.attachUnitStrength,
                            jsonBean.syncIndex,
                            jsonBean.weaponAdaptation,
                            jsonBean.fireRate,
                            jsonBean.gunDamageType,
                            jsonBean.maxRange,
                            jsonBean.magazineCapacity,
                            jsonBean.reloadSpeed,
                            jsonBean.gunCritDamage,
                            jsonBean.ammoType
                    );
                    roleEntities.add(entity);
                }
                db.roleDao().insertRoles(roleEntities);
            }
        }
    }
}