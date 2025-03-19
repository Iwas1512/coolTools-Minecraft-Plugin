package coolTools.itemsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArmorEventListener implements Listener {
    private final CustomArmorManager armorManager;

    public ArmorEventListener(CustomArmorManager armorManager) {
        this.armorManager = armorManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        // Check for armor changes
        checkArmorAbilities(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Check for armor abilities when player joins
        checkArmorAbilities(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Disable flight when player leaves
        Player player = event.getPlayer();
        if (!player.isOp() && !player.hasPermission("itemsplugin.keepflight")) {
            player.setAllowFlight(false);
            player.setFlying(false);
        }
    }

    // Method to check and apply armor abilities
    public void checkArmorAbilities(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack boots = player.getInventory().getBoots();

        // Check for winged helmet
        if (helmet != null && helmet.hasItemMeta() && helmet.getItemMeta().hasDisplayName()
                && helmet.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Winged Helmet")) {
            player.setAllowFlight(true);
            if (!player.isFlying()) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Flight activated!");
            }
        } else if (!player.isOp() && !player.hasPermission("itemsplugin.keepflight")) {
            player.setAllowFlight(false);
            player.setFlying(false);
        }

        // Check for flash boots
        if (boots != null && boots.hasItemMeta() && boots.getItemMeta().hasDisplayName()
                && boots.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Flash Boots")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 4, false, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 20 * 10, 1, false, false, true));
        }
    }
}