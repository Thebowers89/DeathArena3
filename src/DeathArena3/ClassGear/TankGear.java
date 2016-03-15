package DeathArena3.ClassGear;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TankGear {

    public static ItemStack helmet() {
        ItemStack h = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta hm = h.getItemMeta();
        hm.setDisplayName(ChatColor.GOLD + "Tank Helmet");
        h.setItemMeta(hm);
        h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        return h;
    }

    public static ItemStack chestplate() {
        ItemStack c = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta cm = c.getItemMeta();
        cm.setDisplayName(ChatColor.GOLD + "Tank Chestplate");
        c.setItemMeta(cm);
        c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        return c;
    }

    public static ItemStack leggings() {
        ItemStack l = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta lm = l.getItemMeta();
        lm.setDisplayName(ChatColor.GOLD + "Tank Leggings");
        l.setItemMeta(lm);
        l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        return l;
    }

    public static ItemStack boots() {
        ItemStack b = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta bm = b.getItemMeta();
        bm.setDisplayName(ChatColor.GOLD + "Tank Boots");
        b.setItemMeta(bm);
        b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        return b;
    }

    public static ItemStack sword() {
        ItemStack s = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta sm = s.getItemMeta();
        sm.setDisplayName(ChatColor.GOLD + "Tank Sword");
        s.setItemMeta(sm);
        s.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        return s;
    }

}
