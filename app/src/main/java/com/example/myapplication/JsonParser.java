package com.example.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JsonParser {
    // 解析角色JSON数据
    public static List<RoleBeanJson> parseRoleData(Context context) {
        AssetManager assetManager = context.getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(assetManager.open("role_data.json")));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            Gson gson = new Gson();
            return gson.fromJson(sb.toString(), new TypeToken<List<RoleBeanJson>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 对应JSON字段的辅助类
    public static class RoleBeanJson {
        public String name;
        public String armedName;
        public String weaponType;
        public String damageType;
        public String resId;
        public int maxHp;
        public int attack;
        public int defense;
        public float critRate;
        public float critDamageBoost;
        public float burstEnergyEfficiency;
        public int stamina;
        public float kineticResistance;
        public float heatResistance;
        public float coldResistance;
        public float electricResistance;
        public float specialResistance;
        public float skillHaste;
        public float normalEnergyRecovery;
        public float attachUnitStrength;
        public float syncIndex;
        public float weaponAdaptation;
        public float fireRate;
        public String gunDamageType;
        public float maxRange;
        public int magazineCapacity;
        public float reloadSpeed;
        public float gunCritDamage;
        public String ammoType;
    }
}