package DeathArena3.Bosses;

import DeathArena3.Managers.ArenaManager;
import DeathArena3.Managers.BattleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;

public class ShiaLabeouf {

    public static Zombie shiaBoss(Location location, String arenaName) {
        Zombie boss = location.getWorld().spawn(location, Zombie.class);
        UUID bossID = boss.getUniqueId();
        EntityEquipment eq = boss.getEquipment();

        if (boss.isBaby()) {
            boss.setCustomName(ChatColor.DARK_RED + "Baby Labeouf");
        } else {
            boss.setCustomName(ChatColor.DARK_RED + "Shia Labeouf");
        }

        eq.setHelmet(bossHead());
        eq.setHelmetDropChance(0f);
        eq.setChestplate(chest());
        eq.setChestplateDropChance(0f);
        eq.setLeggings(leg());
        eq.setLeggingsDropChance(0f);
        eq.setBoots(boot());
        eq.setBootsDropChance(0f);
        eq.setItemInHand(sword());
        eq.setItemInHandDropChance(0f);

        ArenaManager.bossArena.put(bossID, arenaName);

        if (!(getTank(arenaName) == null)) {
            boss.setTarget(getTank(arenaName));
        }
        boss.setMaxHealth(setBossHealth(arenaName));
        boss.setHealth(setBossHealth(arenaName));
        boss.setRemoveWhenFarAway(false);
        boss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 2));
        return boss;
    }

    public static ItemStack bossHead() {
        ItemStack bHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta sMeta = (SkullMeta) bHead.getItemMeta();
        sMeta.setOwner("ShiaLabeouf");
        bHead.setItemMeta(sMeta);
        return bHead;
    }

    public static ItemStack chest() {
        ItemStack c = new ItemStack(Material.DIAMOND_CHESTPLATE);
        c.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        c.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        return c;
    }

    public static ItemStack leg() {
        ItemStack l = new ItemStack(Material.DIAMOND_LEGGINGS);
        l.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        l.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        return l;
    }

    public static ItemStack boot() {
        ItemStack b = new ItemStack(Material.DIAMOND_BOOTS);
        b.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        b.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        return b;
    }

    public static ItemStack sword() {
        ItemStack s = new ItemStack(Material.DIAMOND_SWORD);
        s.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        s.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        return s;
    }

    private static Player getTank(String arenaName) {
        ArrayList<String> names = ArenaManager.activeArenas.get(arenaName);
        Player player = null;
        for (String key : names) {
            if (BattleManager.tankList.contains(key)) {
                player = Bukkit.getServer().getPlayer(key);
            }
        }
        return player;
    }

    private static int setBossHealth(String arenaName) {
        ArrayList<String> names = ArenaManager.activeArenas.get(arenaName);
        return names.size() * 100;
    }

}
