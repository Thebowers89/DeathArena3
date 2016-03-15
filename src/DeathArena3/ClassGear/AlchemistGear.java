package DeathArena3.ClassGear;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AlchemistGear {

    public static ItemStack helmet() {
        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setDisplayName(ChatColor.BLUE + "Alchemist Helmet");
        helmet.setItemMeta(helmetMeta);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return helmet;
    }

    public static ItemStack chestplate() {
        ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setDisplayName(ChatColor.BLUE + "Alchemist Chestplate");
        chest.setItemMeta(chestMeta);
        chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return chest;
    }

    public static ItemStack legs() {
        ItemStack leg = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta legMeta = leg.getItemMeta();
        legMeta.setDisplayName(ChatColor.BLUE + "Alchemist Leggings");
        leg.setItemMeta(legMeta);
        leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return leg;
    }

    public static ItemStack boots() {
        ItemStack boot = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta bootMeta = boot.getItemMeta();
        bootMeta.setDisplayName(ChatColor.BLUE + "Alchemist Boots");
        boot.setItemMeta(bootMeta);
        boot.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return boot;
    }

    public static ItemStack sword() {
        ItemStack s = new ItemStack(Material.WOOD_SWORD);
        ItemMeta sm = s.getItemMeta();
        sm.setDisplayName(ChatColor.BLUE + "Alchemist Sword");
        s.setItemMeta(sm);
        return s;
    }

}