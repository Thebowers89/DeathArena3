package DeathArena3.ClassGear;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RangerGear {

    public static ItemStack helmet() {
        ItemStack h = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta hm = h.getItemMeta();
        hm.setDisplayName(ChatColor.GREEN + "Ranger Helmet");
        h.setItemMeta(hm);
        h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return h;
    }

    public static ItemStack chest() {
        ItemStack c = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta cm = c.getItemMeta();
        cm.setDisplayName(ChatColor.GREEN + "Ranger Chestplate");
        c.setItemMeta(cm);
        c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return c;
    }

    public static ItemStack leg() {
        ItemStack l = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta lm = l.getItemMeta();
        lm.setDisplayName(ChatColor.GREEN + "Ranger Leggings");
        l.setItemMeta(lm);
        l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return l;
    }

    public static ItemStack boot() {
        ItemStack b = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta bm = b.getItemMeta();
        bm.setDisplayName(ChatColor.GREEN + "Ranger Boots");
        b.setItemMeta(bm);
        b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return b;
    }

    public static ItemStack bow() {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName(ChatColor.GREEN + "Ranger Bow");
        bow.setItemMeta(bowMeta);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        return bow;
    }

}
