package me.crimsondawn45.fabricshieldlib.object;

import net.minecraft.item.Item;
import net.minecraft.tag.Tag.Identified;

public class BannerShield extends AbstractShield
{
    @Deprecated
    public BannerShield(Settings settings, int cooldownTicks, int durability, Item repairItem)
    {
        super(settings, cooldownTicks, durability, repairItem);
    }

    @Deprecated
    public BannerShield(Settings settings, int cooldownTicks, int durability, Identified<Item> repairItemTag)
    {
        super(settings, cooldownTicks, durability, repairItemTag);
    }

    @Deprecated
    public BannerShield(Settings settings, int cooldownTicks, int durability, Item...repairItems)
    {
        super(settings, cooldownTicks, durability, repairItems);
    }
}