package games.bevs.minecraftbut.commons.utils;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemStackBuilder {
	private ItemStack itemStack;

	public ItemStackBuilder(Material material, short data) {
		this.itemStack = new ItemStack(material, data);
	}

	public ItemStackBuilder(Material material, int amount) {
		this.itemStack = new ItemStack(material, amount);
	}
	
	public ItemStackBuilder(Material material) {
		this.itemStack = new ItemStack(material);
	}

	public ItemStackBuilder amount(int amount) {
		this.itemStack.setAmount(amount);
		return this;
	}
	
	public ItemStackBuilder durability(int amount) 
    {
        this.build().setDurability((short)amount);
        return this;
    }

    public ItemStackBuilder durabilityLeft(int amount)
    {
        this.build().setDurability((short)(this.build().getType().getMaxDurability() - amount));
        return this;
    }
    
    /**
     * On use on LEATHER_ARMOR
     * @param color
     * @return
     */
    public ItemStackBuilder setLeatherColour(Color color)
    {
        try 
        {
            LeatherArmorMeta itemMeta = (LeatherArmorMeta)this.build().getItemMeta();
            itemMeta.setColor(color);
            this.build().setItemMeta((ItemMeta)itemMeta);
            return this;
        }
        catch (ClassCastException ex)
        {
           ex.printStackTrace();
        }
        return this;
    }

	public ItemStackBuilder lore(String... lore) {
		ItemMeta itemMeta = build().getItemMeta();
		itemMeta.setLore(Arrays.asList(lore));
		build().setItemMeta(itemMeta);
		return this;
	}

	public ItemStackBuilder displayName(String displayName) {
		ItemMeta itemMeta = build().getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		build().setItemMeta(itemMeta);
		return this;
	}

	public ItemStackBuilder enchantment(Enchantment enchantment, int level) {
		this.itemStack.addUnsafeEnchantment(enchantment, level);
		return this;
	}
	
	public ItemMeta getItemMeta() 
	{
		return this.itemStack.getItemMeta(); 
	}

	public ItemStack build() {
		return this.itemStack;
	}
}
