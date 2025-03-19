package coolTools.itemsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class CustomArmorManager {
    private final Map<String, ItemStack> customArmor = new HashMap<>();

    public CustomArmorManager() {
        // Initialize special armor pieces
        createSpeedBoots();
        createWingedHelmet();
    }

    private void createSpeedBoots() {
        ItemStack speedBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = speedBoots.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Flash Boots");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Speed Level: " + ChatColor.RED + "V");
        lore.add(ChatColor.GRAY + "Run as fast as the Flash!");
        lore.add(ChatColor.GOLD + "Special Ability: Super Speed");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.getByKey(org.bukkit.NamespacedKey.minecraft("protection")), 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        speedBoots.setItemMeta(meta);
        this.customArmor.put("speedboots", speedBoots);
    }

    private void createWingedHelmet() {
        ItemStack wingedHelmet = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta meta = wingedHelmet.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Winged Helmet");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Soar through the skies!");
        lore.add(ChatColor.GOLD + "Special Ability: Flight");
        lore.add(ChatColor.YELLOW + "Equip to enable flight");
        meta.setLore(lore);


        meta.addEnchant(Enchantment.getByKey(org.bukkit.NamespacedKey.minecraft("respiration")), 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        wingedHelmet.setItemMeta(meta);
        this.customArmor.put("wingedhelmet", wingedHelmet);
    }

    public ItemStack getCustomArmor(String name) {
        return this.customArmor.getOrDefault(name, null);
    }

    public boolean isCustomArmor(ItemStack item) {
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return false;
        }

        String name = item.getItemMeta().getDisplayName();
        return name.equals(ChatColor.AQUA + "Flash Boots") ||
                name.equals(ChatColor.LIGHT_PURPLE + "Winged Helmet");
    }
}