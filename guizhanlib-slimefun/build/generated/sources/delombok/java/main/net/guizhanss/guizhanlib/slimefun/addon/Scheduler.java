package net.guizhanss.guizhanlib.slimefun.addon;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.TimeUnit;

/**
 * A class for scheduling tasks.
 * <p>
 * Uses Paper's region-based scheduler API (GlobalRegionScheduler / AsyncScheduler)
 * so that it works correctly on both regular Paper servers and Folia.
 * These tasks are not tied to any specific world/location, so the global
 * region scheduler is the correct choice (see Paper's Folia scheduler docs).
 * <p>
 * Modified from InfinityLib
 *
 * @author Mooy1
 * @author ybw0014
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("ConstantConditions")
public final class Scheduler {

    private final Plugin plugin;

    public Scheduler(@Nonnull Plugin plugin) {
        Preconditions.checkArgument(plugin != null, "Plugin instance cannot be null");
        this.plugin = plugin;
    }

    public void run(@Nonnull Runnable runnable) {
        Bukkit.getGlobalRegionScheduler().execute(plugin, runnable);
    }

    public void runAsync(@Nonnull Runnable runnable) {
        Bukkit.getAsyncScheduler().runNow(plugin, task -> runnable.run());
    }

    public void run(int delayTicks, @Nonnull Runnable runnable) {
        Bukkit.getGlobalRegionScheduler().runDelayed(plugin, task -> runnable.run(), Math.max(1, delayTicks));
    }

    public void runAsync(int delayTicks, @Nonnull Runnable runnable) {
        Bukkit.getAsyncScheduler().runDelayed(plugin, task -> runnable.run(),
                ticksToMillis(delayTicks), TimeUnit.MILLISECONDS);
    }

    public void repeat(int intervalTicks, @Nonnull Runnable runnable) {
        repeat(intervalTicks, 1, runnable);
    }

    public void repeatAsync(int intervalTicks, @Nonnull Runnable runnable) {
        repeatAsync(intervalTicks, 1, runnable);
    }

    public void repeat(int intervalTicks, int delayTicks, @Nonnull Runnable runnable) {
        Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, task -> runnable.run(),
                Math.max(1, delayTicks), Math.max(1, intervalTicks));
    }

    public void repeatAsync(int intervalTicks, int delayTicks, @Nonnull Runnable runnable) {
        Bukkit.getAsyncScheduler().runAtFixedRate(plugin, task -> runnable.run(),
                ticksToMillis(delayTicks), ticksToMillis(Math.max(1, intervalTicks)), TimeUnit.MILLISECONDS);
    }

    /**
     * Converts a duration in Minecraft ticks (20 ticks = 1 second) to milliseconds,
     * for use with the real-time-based {@link Bukkit#getAsyncScheduler()}.
     * The minimum returned value is 1ms, since the async scheduler does not accept 0.
     *
     * @param ticks the duration in ticks
     * @return the equivalent duration in milliseconds
     */
    private static long ticksToMillis(int ticks) {
        return Math.max(1L, ticks * 50L);
    }
}
