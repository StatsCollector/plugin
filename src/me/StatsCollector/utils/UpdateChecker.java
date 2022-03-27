package me.StatsCollector.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    /* Variables */
    final JavaPlugin plugin;
    final int resourceId;

    /* Constructor */
    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    /* Methods */
    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
           try(InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if(scanner.hasNext()) consumer.accept(scanner.next());
           } catch (IOException exception) {
               plugin.getLogger().info("StatsCollector: Unable to check for updates: " + exception.getMessage());
           }
        });
    }
}
