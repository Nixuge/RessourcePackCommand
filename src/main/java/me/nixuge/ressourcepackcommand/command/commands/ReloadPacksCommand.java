package me.nixuge.ressourcepackcommand.command.commands;

import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

import me.nixuge.ressourcepackcommand.command.AbstractCommand;
import me.nixuge.ressourcepackcommand.utils.EntriesUtils;

public class ReloadPacksCommand extends AbstractCommand {
    public ReloadPacksCommand() {
        super("reloadpacks");
    }

    @Override
    public List<String> getCommandAliases() {
        ArrayList<String> al = new ArrayList<>();
        al.add("rlp");
        al.add("rlpacks");
        return al;
    }

    @Override
    public void onCommand(final ICommandSender sender, final String[] args) {
        EntriesUtils.refresh();
    }
}
