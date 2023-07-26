package me.nixuge.ressourcepackcommand.command.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

import me.nixuge.ressourcepackcommand.command.AbstractCommand;
import me.nixuge.ressourcepackcommand.utils.PrintUtils;

public class ListPacksCommand extends AbstractCommand {
    Minecraft mc = Minecraft.getMinecraft();

    public ListPacksCommand() {
        super("listpacks");
    }

    @Override
    public List<String> getCommandAliases() {
        ArrayList<String> al = new ArrayList<>();
        al.add("listp");
        return al;
    }

    @Override
    public void onCommand(final ICommandSender sender, final String[] args) {
        PrintUtils.printPackList();
    }
}
