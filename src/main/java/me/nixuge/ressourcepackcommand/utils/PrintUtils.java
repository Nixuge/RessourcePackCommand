package me.nixuge.ressourcepackcommand.utils;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository.Entry;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;


public class PrintUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    private static IChatComponent getProperTextComponent(String text, String cmd) {
        ChatComponentText messageObject = new ChatComponentText(text);
        ChatStyle style = new ChatStyle().setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, cmd));
        messageObject.setChatStyle(style);
        return messageObject;
    }

    public static void printPackList() {
        final EntityPlayerSP player = mc.thePlayer;
        if (player == null)
            return;
        
        ResourcePackRepository repo = mc.getResourcePackRepository();
        repo.updateRepositoryEntriesAll();

        List<Entry> entriesApplied = repo.getRepositoryEntries(); // Only applied entries
        List<Entry> entriesUnapplied = EntriesUtils.getEntriesUnapplied(false); // Entries to be filtered to only include unapplied ones

        for (Entry entry : entriesApplied) {
            String pn = entry.getResourcePackName();
            player.addChatMessage(getProperTextComponent("§c[-]§r " + pn, "removepack " + pn));
        }
        
        for (Entry entry : entriesUnapplied) {
            String pn = entry.getResourcePackName();
            player.addChatMessage(getProperTextComponent("§2[+]§r " + pn, "addpack " + pn));
        }
    }
    public static void printSuccessfulMessage(boolean added, String name) {
        final EntityPlayerSP player = mc.thePlayer;
        if (player == null)
            return;
        
        String action = added ? "added" : "removed";
        String full = "Successfully " + action + " pack " + name + "§r";
        player.addChatMessage(new ChatComponentText(full));

        ChatComponentText messageObject = new ChatComponentText("[Click here to refresh]");
        ChatStyle style = new ChatStyle().setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, "reloadpacks"));
        messageObject.setChatStyle(style);

        player.addChatMessage(messageObject);
    }
}
