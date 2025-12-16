package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Room 实体类：对应数据库中的 role 表
 * 字段和 RoleBean 完全对齐，用于数据库存储
 */
@Entity(tableName = "role")
public class RoleEntity {
    @PrimaryKey(autoGenerate = true) // 自增主键
    private long id;                  // 数据库主键（非业务字段）
    private String name;              // 角色名
    private String armedName;         // 武装名
    private String weaponType;        // 武器类型
    private String damageType;        // 伤害类型
    private String resName;           // 立绘资源名（代替resId，避免资源ID变动）
    // 基础属性
    private int maxHp;
    private int attack;
    private int defense;
    // 特殊属性
    private float critRate;
    private float critDamageBoost;
    private float burstEnergyEfficiency;
    private int stamina;
    private float kineticResistance;
    private float heatResistance;
    private float coldResistance;
    private float electricResistance;
    private float specialResistance;
    private float skillHaste;
    private float normalEnergyRecovery;
    private float attachUnitStrength;
    private float syncIndex;
    // 枪械属性
    private float weaponAdaptation;
    private float fireRate;
    private String gunDamageType;
    private float maxRange;
    private int magazineCapacity;
    private float reloadSpeed;
    private float gunCritDamage;
    private String ammoType;

    // 空构造（Room 必须）
    public RoleEntity() {}

    // 全参构造（用于数据导入）
    public RoleEntity(String name, String armedName, String weaponType, String damageType, String resName,
                      int maxHp, int attack, int defense,
                      float critRate, float critDamageBoost, float burstEnergyEfficiency, int stamina,
                      float kineticResistance, float heatResistance, float coldResistance,
                      float electricResistance, float specialResistance, float skillHaste,
                      float normalEnergyRecovery, float attachUnitStrength, float syncIndex,
                      float weaponAdaptation, float fireRate, String gunDamageType, float maxRange,
                      int magazineCapacity, float reloadSpeed, float gunCritDamage, String ammoType) {
        this.name = name;
        this.armedName = armedName;
        this.weaponType = weaponType;
        this.damageType = damageType;
        this.resName = resName;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
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
        this.weaponAdaptation = weaponAdaptation;
        this.fireRate = fireRate;
        this.gunDamageType = gunDamageType;
        this.maxRange = maxRange;
        this.magazineCapacity = magazineCapacity;
        this.reloadSpeed = reloadSpeed;
        this.gunCritDamage = gunCritDamage;
        this.ammoType = ammoType;
    }

    // 转换为 RoleBean（用于UI展示）
    public RoleBean toRoleBean() {
        // 资源名转资源ID（避免硬编码resId）
        int resId = MyApplication.getContext().getResources()
                .getIdentifier(resName, "drawable", MyApplication.getContext().getPackageName());
        if (resId == 0) {
            resId = R.drawable.role_placeholder; // 兜底占位图
        }
        return new RoleBean(
                name, armedName, weaponType, damageType, resId,
                maxHp, attack, defense,
                critRate, critDamageBoost, burstEnergyEfficiency, stamina,
                kineticResistance, heatResistance, coldResistance,
                electricResistance, specialResistance, skillHaste,
                normalEnergyRecovery, attachUnitStrength, syncIndex,
                weaponAdaptation, fireRate, gunDamageType, maxRange,
                magazineCapacity, reloadSpeed, gunCritDamage, ammoType
        );
    }

    // 所有字段的 Getter/Setter（Room 必须）
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getArmedName() { return armedName; }
    public void setArmedName(String armedName) { this.armedName = armedName; }

    public String getWeaponType() { return weaponType; }
    public void setWeaponType(String weaponType) { this.weaponType = weaponType; }

    public String getDamageType() { return damageType; }
    public void setDamageType(String damageType) { this.damageType = damageType; }

    public String getResName() { return resName; }
    public void setResName(String resName) { this.resName = resName; }

    public int getMaxHp() { return maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

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

    public float getWeaponAdaptation() { return weaponAdaptation; }
    public void setWeaponAdaptation(float weaponAdaptation) { this.weaponAdaptation = weaponAdaptation; }

    public float getFireRate() { return fireRate; }
    public void setFireRate(float fireRate) { this.fireRate = fireRate; }

    public String getGunDamageType() { return gunDamageType; }
    public void setGunDamageType(String gunDamageType) { this.gunDamageType = gunDamageType; }

    public float getMaxRange() { return maxRange; }
    public void setMaxRange(float maxRange) { this.maxRange = maxRange; }

    public int getMagazineCapacity() { return magazineCapacity; }
    public void setMagazineCapacity(int magazineCapacity) { this.magazineCapacity = magazineCapacity; }

    public float getReloadSpeed() { return reloadSpeed; }
    public void setReloadSpeed(float reloadSpeed) { this.reloadSpeed = reloadSpeed; }

    public float getGunCritDamage() { return gunCritDamage; }
    public void setGunCritDamage(float gunCritDamage) { this.gunCritDamage = gunCritDamage; }

    public String getAmmoType() { return ammoType; }
    public void setAmmoType(String ammoType) { this.ammoType = ammoType; }
}