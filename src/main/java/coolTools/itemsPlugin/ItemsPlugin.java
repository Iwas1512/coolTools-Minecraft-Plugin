package coolTools.itemsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class ItemsPlugin extends JavaPlugin implements Listener {
    private CustomArmorManager armorManager;
    private ArmorEventListener armorListener;

    @Override
    public void onEnable() {
        // Initialize managers
        armorManager = new CustomArmorManager();

        // Create and register event listeners
        armorListener = new ArmorEventListener(armorManager);
        getServer().getPluginManager().registerEvents(armorListener, this);
        getServer().getPluginManager().registerEvents(this, this);

        // Start armor checker
        startArmorChecker();

        getLogger().info("ItemsPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        // Remove flight from all players when plugin disables
        getServer().getOnlinePlayers().forEach(player -> {
            if (!player.isOp() && !player.hasPermission("itemsplugin.keepflight")) {
                player.setAllowFlight(false);
                player.setFlying(false);
            }
        });

        getLogger().info("ItemsPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("supertool")) {
            // Create super pickaxe
            ItemStack superPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemMeta meta = superPickaxe.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "Super Pickaxe");

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Breaks blocks in a 3x3 area");
            meta.setLore(lore);

            superPickaxe.setItemMeta(meta);

            // Give the item to the player
            player.getInventory().addItem(superPickaxe);
            player.sendMessage(ChatColor.GREEN + "You received a Super Pickaxe!");

            return true;
        }

        if (label.equalsIgnoreCase("specialarmor")) {
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Usage: /specialarmor <speedboots|wingedhelmet>");
                return true;
            }

            String armorType = args[0].toLowerCase();

            if (armorType.equals("speedboots") || armorType.equals("wingedhelmet")) {
                ItemStack armor = armorManager.getCustomArmor(armorType);
                if (armor != null) {
                    player.getInventory().addItem(armor);
                    player.sendMessage(ChatColor.GREEN + "You received special armor: " + armorType);
                } else {
                    player.sendMessage(ChatColor.RED + "Error creating armor item");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Unknown armor type. Available types: speedboots, wingedhelmet");
            }

            return true;
        }

        return false;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Check if the player is using our super pickaxe
        if (item.getType() == Material.DIAMOND_PICKAXE && item.hasItemMeta() &&
                item.getItemMeta().hasDisplayName() &&
                item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Super Pickaxe")) {

            // Get the center block location
            int centerX = event.getBlock().getX();
            int centerY = event.getBlock().getY();
            int centerZ = event.getBlock().getZ();

            // Break blocks in a 3x3 area
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        // Skip the center block as it's already broken
                        if (x == 0 && y == 0 && z == 0) continue;

                        // Break the surrounding blocks
                        event.getBlock().getWorld()
                                .getBlockAt(centerX + x, centerY + y, centerZ + z)
                                .breakNaturally(item);
                    }
                }
            }
        }
    }

    private void startArmorChecker() {
        // Create a repeating task to check and reapply armor effects
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    armorListener.checkArmorAbilities(player);
                }
            }
        }.runTaskTimer(this, 100L, 100L); // Run every 5 seconds (100 ticks)
    }
}