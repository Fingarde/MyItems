package fr.fingarde.myitems;


import org.bukkit.Color;

import java.util.ArrayList;

public class MyItem
{
    private String localizedName;
    private String name;
    private ItemType type;
    private BaseItem baseItem;
    private String texture;
    private int maxStackSize;

    private int durability;
    private boolean unbreakable;

    private Color color;
    private int armor;
    private int armorThougness;
    private int attackSpeed;
    private int attackDamage;
    private ArrayList<String> drops;

    public MyItem(String localizedName, String name, ItemType type, BaseItem baseItem, String texture, int maxStackSize)
    {
        this.localizedName = localizedName;
        this.name = name;
        this.type = type;
        this.baseItem = baseItem;
        this.texture = texture;
        this.maxStackSize = maxStackSize;

    }

    public MyItem(String localizedName, String name, ItemType type, BaseItem baseItem, String texture, int maxStackSize, int durability, int attackSpeed, int attackDamage)
    {
        this.localizedName = localizedName;
        this.name = name;
        this.type = type;
        this.baseItem = baseItem;
        this.texture = texture;
        this.maxStackSize = maxStackSize;
        this.durability = durability;
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
    }

    public MyItem(String localizedName, String name, ItemType type, BaseItem baseItem, String texture, int maxStackSize, int durability, Color color, int armor, int armorThougness)
    {
        this.localizedName = localizedName;
        this.name = name;
        this.type = type;
        this.baseItem = baseItem;
        this.texture = texture;
        this.maxStackSize = maxStackSize;
        this.durability = durability;
        this.color = color;
        this.armor = armor;
        this.armorThougness = armorThougness;
    }


    public MyItem(String localizedName, String name, ItemType type, BaseItem baseItem, String texture, int maxStackSize, int durability, ArrayList<String> drops)
    {
        this.localizedName = localizedName;
        this.name = name;
        this.type = type;
        this.baseItem = baseItem;
        this.texture = texture;
        this.maxStackSize = maxStackSize;
        this.durability = durability;
        this.drops = drops;
    }

    public enum ItemType
    {
        ARMOR,
        BLOCK,
        ITEM,
        TOOL;
    }

    public enum BaseItem
    {
        SHEARS(238),
        TRIDENT(250),
        CROSSBOW(326),
        FLINT_AND_STEEL(64),
        FISHING_ROD(64),
        BOW(384),
        SHIELD(336),

        WOODEN_AXE(59),
        WOODEN_HOE(59),
        WOODEN_PICKAXE(59),
        WOODEN_SHOVEL(59),
        WOODEN_SWORD(59),

        STONE_AXE(131),
        STONE_HOE(131),
        STONE_PICKAXE(131),
        STONE_SHOVEL(131),
        STONE_SWORD(131),

        IRON_AXE(250),
        IRON_HOE(250),
        IRON_PICKAXE(250),
        IRON_SHOVEL(250),
        IRON_SWORD(250),

        GOLD_AXE(32),
        GOLD_HOE(32),
        GOLD_PICKAXE(32),
        GOLD_SHOVEL(32),
        GOLD_SWORD(32),

        DIAMOND_AXE(1561),
        DIAMOND_HOE(1561),
        DIAMOND_PICKAXE(1561),
        DIAMOND_SHOVEL(1561),
        DIAMOND_SWORD(1561);


        private int durability;

        BaseItem(int durability)
        {
            this.durability = durability;
        }

        public int getDurability()
        {
            return durability;
        }
    }

    // MyItem Getters


    public String getLocalizedName()
    {
        return localizedName;
    }

    public String getName()
    {
        return name;
    }

    public ItemType getType()
    {
        return type;
    }

    public BaseItem getBaseItem()
    {
        return baseItem;
    }

    public String getTexture()
    {
        return texture;
    }

    public int getMaxStackSize()
    {
        return maxStackSize;
    }

    // For specific type


    public int getDurability()
    {
        return durability;
    }

    public Color getColor()
    {
        return color;
    }

    public int getArmor()
    {
        return armor;
    }

    public int getArmorThougness()
    {
        return armorThougness;
    }

    public int getAttackSpeed()
    {
        return attackSpeed;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public ArrayList<String> getDrops()
    {
        return drops;
    }


}
