package me.crimsondawn45.fabricshieldlib.object;

import java.util.Arrays;
import java.util.List;

import me.crimsondawn45.fabricshieldlib.FabricShieldLib;
import me.crimsondawn45.fabricshieldlib.util.ItemListType;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class FabricShield extends Item
{
	private int cooldownTicks;
	private Item repairItem;
	private Tag.Identified<Item> repairItemTag;
	private List<Item> repairItemList;
	private ItemListType itemListType;
	
	/**
	 * Fabric Shield Item
	 * 
	 * @param settings - Item settings.
	 * @param cooldownTicks - How many ticks the shield will be disabled for when hit by an axe.
	 * @param durability - How much damage the shield can handle before it breaks.
	 * @param repairItem - Item that can be used to repair the shield.
	 */
	public FabricShield(Settings settings, int cooldownTicks, int durability, Item repairItem)
	{
		super(settings.maxDamage(durability));
		
		DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
		
		this.cooldownTicks = cooldownTicks;
		this.repairItem = repairItem;
		this.itemListType = ItemListType.ITEM;
		
		FabricShieldLib.fabricShields.add(this);
		FabricShieldLib.allShields.add(this);
	}
	
	/**
	 * Fabric Shield Item
	 * 
	 * @param settings - Item settings
	 * @param cooldownTicks - How many ticks the shield will be disabled for when hit by an axe.
	 * @param durability - How much damage the shield can handle before it breaks.
	 * @param repairItemTag - Item that can be used to repair the shield.
	 */
	public FabricShield(Settings settings, int cooldownTicks, int durability, Tag.Identified<Item> repairItemTag)
	{
		super(settings.maxDamage(durability));
		
		DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
		
		this.cooldownTicks = cooldownTicks;
		this.repairItemTag = repairItemTag;
		this.itemListType = ItemListType.TAG;
		
		FabricShieldLib.fabricShields.add(this);
		FabricShieldLib.allShields.add(this);
	}
	
	public FabricShield(Settings settings, int cooldownTicks, int durability, Item...repairItems)
	{
		super(settings.maxDamage(durability));
		
		DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
		
		this.cooldownTicks = cooldownTicks;
		this.repairItemList = Arrays.asList(repairItems);
		this.itemListType = ItemListType.LIST;
		
		FabricShieldLib.fabricShields.add(this);
		FabricShieldLib.allShields.add(this);
	}
	
	/**
	 * onBlock
	 * 
	 * Fired whenever this shield successfully blocks an attack.
	 * 
	 * @param defender - Entity that is using this shield.
	 * @param source - Source of the damage.
	 * @param amount - Amount of damage blocked.
	 */
	public void onBlockDamage(LivingEntity defender, DamageSource source, float amount, Hand hand, ItemStack shield){}
	
	/**
	 * whileBlockingTick
	 * 
	 * Fired every tick this shield is blocking.
	 * 
	 * @param defender - Entity that is using this shield.
	 */
	public void whileBlockingTick(LivingEntity defender, Hand hand, ItemStack shield){}
	
	/**
	 * whileHoldingShieldTick
	 * 
	 * Fired every tick this shield is held
	 * 
	 * @param defender - Entity that is using this shield.
	 * @param isBlocking - If the shield is currently blocking.
	 */
	public void whileHoldingTick(LivingEntity defender, boolean isBlocking, Hand hand, ItemStack shield){}
	
	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.BLOCK;
    }

	@Override
	public int getMaxUseTime(ItemStack stack)
	{
		return 72000;
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
	{
      ItemStack itemStack = user.getStackInHand(hand);
      user.setCurrentHand(hand);
      return TypedActionResult.consume(itemStack);
	}
	
	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient)
	{
		switch(this.itemListType)
		{
			case ITEM:	return this.repairItem == ingredient.getItem();
			case LIST:	return this.repairItemList.contains(ingredient.getItem());
			case TAG:	return this.repairItemTag.contains(ingredient.getItem());
			
			default:	return false;
		}
    }
	
	@Override
	public boolean isEnchantable(ItemStack item)
	{
		return !item.hasEnchantments();
	}
	
	@Override
	public int getEnchantability()
	{
		return 9;
	}
	
	/**
	 * getCoolDownTicks
	 * 
	 * @return How many ticks the shield goes into cooldown for after being disabled.
	 */
	public int getCooldownTicks()
	{
		return this.cooldownTicks;
	}
}