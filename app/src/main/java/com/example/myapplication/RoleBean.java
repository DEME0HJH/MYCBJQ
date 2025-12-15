package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 角色数据模型（包含完整属性：基础/特殊/枪械）
 */
public class RoleBean implements Parcelable {
    // 基础信息
    private String name;                // 角色名（如：安卡希雅）
    private String armedName;           // 武装名（如：深空之眼）
    private String weaponType;          // 武器类型（突击步枪/冲锋枪等）
    private String damageType;          // 伤害类型（动能/高热/低温/电击/特异）
    private int resId;                  // 立绘资源ID

    // 基础属性
    private int maxHp;                  // 最大生命
    private int attack;                 // 攻击力
    private int defense;                // 防御力

    // 特殊属性
    private float critRate;             // 暴击率（百分比，如：15.0 → 15%）
    private float critDamageBoost;      // 暴击伤害增幅（百分比）
    private float burstEnergyEfficiency;// 爆发能量获取效率（百分比）
    private int stamina;                // 耐力
    private float kineticResistance;    // 动能抗性（百分比）
    private float heatResistance;       // 高热抗性（百分比）
    private float coldResistance;       // 低温抗性（百分比）
    private float electricResistance;   // 电击抗性（百分比）
    private float specialResistance;    // 特异抗性（百分比）
    private float skillHaste;           // 技能急速（百分比）
    private float normalEnergyRecovery; // 常规能量回复速度（数值/秒）
    private float attachUnitStrength;   // 附属单位强度（百分比）
    private float syncIndex;            // 同调指数（数值）

    // 枪械属性
    private float weaponAdaptation;     // 武器适配度（百分比）
    private float fireRate;             // 射速（发/秒）
    private String gunDamageType;       // 枪械伤害类型（复用伤害类型：动能/高热等）
    private float maxRange;             // 极限射程（米）
    private int magazineCapacity;       // 弹容（发）
    private float reloadSpeed;          // 换弹速度（秒）
    private float gunCritDamage;        // 枪械暴击伤害（百分比）
    private String ammoType;            // 弹药类型（如：步枪弹/手枪弹）

    // 基础构造方法（兼容旧代码，仅核心字段）
    public RoleBean(String name, String weaponType, String damageType, int star, int resId) {
        this.name = name;
        this.weaponType = weaponType;
        this.damageType = damageType;
        // 初始化所有属性默认值（避免空指针/数值异常）
        this.resId = resId; // 新增！
        initDefaultValues();
    }

    // 完整构造方法（含所有属性）
    public RoleBean(String name, String armedName, String weaponType, String damageType,int resId,
                    // 基础属性
                    int maxHp, int attack, int defense,
                    // 特殊属性
                    float critRate, float critDamageBoost, float burstEnergyEfficiency, int stamina,
                    float kineticResistance, float heatResistance, float coldResistance,
                    float electricResistance, float specialResistance, float skillHaste,
                    float normalEnergyRecovery, float attachUnitStrength, float syncIndex,
                    // 枪械属性
                    float weaponAdaptation, float fireRate, String gunDamageType, float maxRange,
                    int magazineCapacity, float reloadSpeed, float gunCritDamage, String ammoType) {
        this.name = name;
        this.armedName = armedName;
        this.weaponType = weaponType;
        this.damageType = damageType;
        this.resId = resId;
        // 基础属性
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        // 特殊属性
        this.critRate = critRate;
        this.critDamageBoost = critDamageBoost;
        this.burstEnergyEfficiency = burstEnergyEfficiency;
        this.stamina = stamina;
        this.kineticResistance = kineticResistance;
        this.heatResistance = heatResistance;
        this.coldResistance = coldResistance;
        this.electricResistance = electricResistance;
        this.specialResistance = specialResistance;
        this.skillHaste = skillHaste;
        this.normalEnergyRecovery = normalEnergyRecovery;
        this.attachUnitStrength = attachUnitStrength;
        this.syncIndex = syncIndex;
        // 枪械属性
        this.weaponAdaptation = weaponAdaptation;
        this.fireRate = fireRate;
        this.gunDamageType = gunDamageType;
        this.maxRange = maxRange;
        this.magazineCapacity = magazineCapacity;
        this.reloadSpeed = reloadSpeed;
        this.gunCritDamage = gunCritDamage;
        this.ammoType = ammoType;
    }

    // 初始化所有属性默认值（避免空指针/数值异常）
    private void initDefaultValues() {
        // 基础信息默认值
        this.armedName = "";
        this.gunDamageType = "";
        this.ammoType = "";
        // 基础属性默认值
        this.maxHp = 0;
        this.attack = 0;
        this.defense = 0;
        // 特殊属性默认值（百分比默认0，数值默认0）
        this.critRate = 0.0f;
        this.critDamageBoost = 0.0f;
        this.burstEnergyEfficiency = 0.0f;
        this.stamina = 0;
        this.kineticResistance = 0.0f;
        this.heatResistance = 0.0f;
        this.coldResistance = 0.0f;
        this.electricResistance = 0.0f;
        this.specialResistance = 0.0f;
        this.skillHaste = 0.0f;
        this.normalEnergyRecovery = 0.0f;
        this.attachUnitStrength = 0.0f;
        this.syncIndex = 0.0f;
        // 枪械属性默认值
        this.weaponAdaptation = 0.0f;
        this.fireRate = 0.0f;
        this.maxRange = 0.0f;
        this.magazineCapacity = 0;
        this.reloadSpeed = 0.0f;
        this.gunCritDamage = 0.0f;
    }

    // ===================== Parcelable 序列化（传递完整对象） =====================
    protected RoleBean(Parcel in) {
        name = in.readString();
        armedName = in.readString();
        weaponType = in.readString();
        damageType = in.readString();
        resId = in.readInt();
        // 基础属性
        maxHp = in.readInt();
        attack = in.readInt();
        defense = in.readInt();
        // 特殊属性
        critRate = in.readFloat();
        critDamageBoost = in.readFloat();
        burstEnergyEfficiency = in.readFloat();
        stamina = in.readInt();
        kineticResistance = in.readFloat();
        heatResistance = in.readFloat();
        coldResistance = in.readFloat();
        electricResistance = in.readFloat();
        specialResistance = in.readFloat();
        skillHaste = in.readFloat();
        normalEnergyRecovery = in.readFloat();
        attachUnitStrength = in.readFloat();
        syncIndex = in.readFloat();
        // 枪械属性
        weaponAdaptation = in.readFloat();
        fireRate = in.readFloat();
        gunDamageType = in.readString();
        maxRange = in.readFloat();
        magazineCapacity = in.readInt();
        reloadSpeed = in.readFloat();
        gunCritDamage = in.readFloat();
        ammoType = in.readString();
    }

    public static final Creator<RoleBean> CREATOR = new Creator<RoleBean>() {
        @Override
        public RoleBean createFromParcel(Parcel in) {
            return new RoleBean(in);
        }

        @Override
        public RoleBean[] newArray(int size) {
            return new RoleBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(armedName);
        dest.writeString(weaponType);
        dest.writeString(damageType);
        dest.writeInt(resId);
        // 基础属性
        dest.writeInt(maxHp);
        dest.writeInt(attack);
        dest.writeInt(defense);
        // 特殊属性
        dest.writeFloat(critRate);
        dest.writeFloat(critDamageBoost);
        dest.writeFloat(burstEnergyEfficiency);
        dest.writeInt(stamina);
        dest.writeFloat(kineticResistance);
        dest.writeFloat(heatResistance);
        dest.writeFloat(coldResistance);
        dest.writeFloat(electricResistance);
        dest.writeFloat(specialResistance);
        dest.writeFloat(skillHaste);
        dest.writeFloat(normalEnergyRecovery);
        dest.writeFloat(attachUnitStrength);
        dest.writeFloat(syncIndex);
        // 枪械属性
        dest.writeFloat(weaponAdaptation);
        dest.writeFloat(fireRate);
        dest.writeString(gunDamageType);
        dest.writeFloat(maxRange);
        dest.writeInt(magazineCapacity);
        dest.writeFloat(reloadSpeed);
        dest.writeFloat(gunCritDamage);
        dest.writeString(ammoType);
    }

    // ===================== Getter/Setter（全字段，含空值保护） =====================
    // 基础信息
    public String getName() { return name == null ? "" : name; }
    public void setName(String name) { this.name = name; }

    public String getArmedName() { return armedName == null ? "" : armedName; }
    public void setArmedName(String armedName) { this.armedName = armedName; }

    public String getWeaponType() { return weaponType == null ? "" : weaponType; }
    public void setWeaponType(String weaponType) { this.weaponType = weaponType; }

    public String getDamageType() { return damageType == null ? "" : damageType; }
    public void setDamageType(String damageType) { this.damageType = damageType; }

    public int getResId() { return resId; }
    public void setResId(int resId) { this.resId = resId; }

    // 基础属性
    public int getMaxHp() { return maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    // 特殊属性
    public float getCritRate() { return critRate; }
    public void setCritRate(float critRate) { this.critRate = critRate; }

    public float getCritDamageBoost() { return critDamageBoost; }
    public void setCritDamageBoost(float critDamageBoost) { this.critDamageBoost = critDamageBoost; }

    public float getBurstEnergyEfficiency() { return burstEnergyEfficiency; }
    public void setBurstEnergyEfficiency(float burstEnergyEfficiency) { this.burstEnergyEfficiency = burstEnergyEfficiency; }

    public int getStamina() { return stamina; }
    public void setStamina(int stamina) { this.stamina = stamina; }

    public float getKineticResistance() { return kineticResistance; }
    public void setKineticResistance(float kineticResistance) { this.kineticResistance = kineticResistance; }

    public float getHeatResistance() { return heatResistance; }
    public void setHeatResistance(float heatResistance) { this.heatResistance = heatResistance; }

    public float getColdResistance() { return coldResistance; }
    public void setColdResistance(float coldResistance) { this.coldResistance = coldResistance; }

    public float getElectricResistance() { return electricResistance; }
    public void setElectricResistance(float electricResistance) { this.electricResistance = electricResistance; }

    public float getSpecialResistance() { return specialResistance; }
    public void setSpecialResistance(float specialResistance) { this.specialResistance = specialResistance; }

    public float getSkillHaste() { return skillHaste; }
    public void setSkillHaste(float skillHaste) { this.skillHaste = skillHaste; }

    public float getNormalEnergyRecovery() { return normalEnergyRecovery; }
    public void setNormalEnergyRecovery(float normalEnergyRecovery) { this.normalEnergyRecovery = normalEnergyRecovery; }

    public float getAttachUnitStrength() { return attachUnitStrength; }
    public void setAttachUnitStrength(float attachUnitStrength) { this.attachUnitStrength = attachUnitStrength; }

    public float getSyncIndex() { return syncIndex; }
    public void setSyncIndex(float syncIndex) { this.syncIndex = syncIndex; }

    // 枪械属性
    public float getWeaponAdaptation() { return weaponAdaptation; }
    public void setWeaponAdaptation(float weaponAdaptation) { this.weaponAdaptation = weaponAdaptation; }

    public float getFireRate() { return fireRate; }
    public void setFireRate(float fireRate) { this.fireRate = fireRate; }

    public String getGunDamageType() { return gunDamageType == null ? "" : gunDamageType; }
    public void setGunDamageType(String gunDamageType) { this.gunDamageType = gunDamageType; }

    public float getMaxRange() { return maxRange; }
    public void setMaxRange(float maxRange) { this.maxRange = maxRange; }

    public int getMagazineCapacity() { return magazineCapacity; }
    public void setMagazineCapacity(int magazineCapacity) { this.magazineCapacity = magazineCapacity; }

    public float getReloadSpeed() { return reloadSpeed; }
    public void setReloadSpeed(float reloadSpeed) { this.reloadSpeed = reloadSpeed; }

    public float getGunCritDamage() { return gunCritDamage; }
    public void setGunCritDamage(float gunCritDamage) { this.gunCritDamage = gunCritDamage; }

    public String getAmmoType() { return ammoType == null ? "" : ammoType; }
    public void setAmmoType(String ammoType) { this.ammoType = ammoType; }

    // ===================== 工具方法（UI展示专用） =====================
    /**
     * 百分比格式化（如：0.15 → 15%）
     */
    private String formatPercent(float value) {
        return String.format("%.1f%%", value * 100);
    }

    /**
     * 数值格式化（保留1位小数）
     */
    private String formatNumber(float value) {
        return String.format("%.1f", value);
    }

    // 暴击率（带%）
    public String getCritRateText() {
        return formatPercent(critRate);
    }

    // 暴击伤害增幅（带%）
    public String getCritDamageBoostText() {
        return formatPercent(critDamageBoost);
    }

    // 抗性通用格式化（带%）
    public String getKineticResistanceText() { return formatPercent(kineticResistance); }
    public String getHeatResistanceText() { return formatPercent(heatResistance); }
    public String getColdResistanceText() { return formatPercent(coldResistance); }
    public String getElectricResistanceText() { return formatPercent(electricResistance); }
    public String getSpecialResistanceText() { return formatPercent(specialResistance); }

    // 射速（发/秒）
    public String getFireRateText() {
        return formatNumber(fireRate) + " 发/秒";
    }

    // 极限射程（米）
    public String getMaxRangeText() {
        return formatNumber(maxRange) + " 米";
    }

    // 换弹速度（秒）
    public String getReloadSpeedText() {
        return formatNumber(reloadSpeed) + " 秒";
    }

    // 调试用
    @Override
    public String toString() {
        return "RoleBean{" +
                "name='" + name + '\'' +
                ", armedName='" + armedName + '\'' +
                ", weaponType='" + weaponType + '\'' +
                ", attack=" + attack +
                '}';
    }
}