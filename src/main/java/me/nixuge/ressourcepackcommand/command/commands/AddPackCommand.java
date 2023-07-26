package me.nixuge.ressourcepackcommand.command.commands;


import net.minecraft.client.resources.ResourcePackRepository.Entry;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

import me.nixuge.ressourcepackcommand.MessageBuilder;
import me.nixuge.ressourcepackcommand.command.AbstractCommand;
import me.nixuge.ressourcepackcommand.utils.ArgsUtils;
import me.nixuge.ressourcepackcommand.utils.EntriesUtils;
import me.nixuge.ressourcepackcommand.utils.PrintUtils;

public class AddPackCommand extends AbstractCommand {
    public AddPackCommand() {
        super("addpack");
    }

    @Override
    public List<String> getCommandAliases() {
        ArrayList<String> al = new ArrayList<>();
        al.add("ap");
        al.add("apack");
        return al;
    }

    @Override
    public void onCommand(final ICommandSender sender, final String[] args) {
        List<Entry> entries = EntriesUtils.getEntriesUnapplied(true);
        
        String name = ArgsUtils.joinArgs(args);
        
        for (Entry entry : entries) {
            if (entry.getResourcePackName().equals(name)) {
                List<Entry> entriesApplied = new ArrayList<>(EntriesUtils.getEntriesApplied(false));
                entriesApplied.add(entry);

                EntriesUtils.updatePacksNoRefresh(entriesApplied);
                
                this.tell(new MessageBuilder("===================="));
                PrintUtils.printPackList();
                PrintUtils.printSuccessfulMessage(true, name);
                return;
            }
        }
        this.tell(new MessageBuilder("Couldn't find pack with name '" + name + "'"));
    }
}
