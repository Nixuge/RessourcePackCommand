package me.nixuge.ressourcepackcommand.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository.Entry;

public class EntriesUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void refresh() {
        mc.refreshResources();
    }

    public static void updatePacksNoRefresh(List<Entry> list) {
        mc.getResourcePackRepository().setRepositories(list);
        mc.gameSettings.resourcePacks.clear();
        mc.gameSettings.incompatibleResourcePacks.clear();

        for (ResourcePackRepository.Entry resourcepackrepository$entry : list) {
            mc.gameSettings.resourcePacks.add(resourcepackrepository$entry.getResourcePackName());

            if (resourcepackrepository$entry.func_183027_f() != 1) {
                mc.gameSettings.incompatibleResourcePacks.add(resourcepackrepository$entry.getResourcePackName());
            }
        }

        mc.gameSettings.saveOptions();
    }

    public static List<Entry> getEntriesApplied(boolean update) {
        ResourcePackRepository repo = mc.getResourcePackRepository();
        if (update)
            repo.updateRepositoryEntriesAll();
        
        return repo.getRepositoryEntries();
    }

    public static List<Entry> getEntriesUnapplied(boolean update) {
        ResourcePackRepository repo = mc.getResourcePackRepository();
        if (update)
            repo.updateRepositoryEntriesAll();

        List<Entry> entriesApplied = repo.getRepositoryEntries(); // Only applied entries
        List<Entry> entriesAll = repo.getRepositoryEntriesAll(); // All entries including applied ones
        List<Entry> entriesUnapplied = new ArrayList<>(); // Entries to be filtered to only include unapplied ones

        for (Entry entry : entriesAll) {
            if (!entriesApplied.contains(entry))
                entriesUnapplied.add(entry);
        }
        return entriesUnapplied;
    }
}
