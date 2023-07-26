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

public class RemovePackCommand extends AbstractCommand {
    public RemovePackCommand() {
        super("removepack");
    }

    @Override
    public List<String> getCommandAliases() {
        ArrayList<String> al = new ArrayList<>();
        al.add("rp");
        al.add("rpack");
        return al;
    }

    @Override
    public void onCommand(final ICommandSender sender, final String[] args) {
        List<Entry> entries = EntriesUtils.getEntriesApplied(true);
        String name = ArgsUtils.joinArgs(args);
        
        for (Entry entry : entries) {
            if (entry.getResourcePackName().equals(name)) {
                List<Entry> entriesAppliedMutable = new ArrayList<>(entries);
                entriesAppliedMutable.remove(entry);
                EntriesUtils.updatePacksNoRefresh(entriesAppliedMutable);

                this.tell(new MessageBuilder("===================="));
                PrintUtils.printPackList();
                PrintUtils.printSuccessfulMessage(false, name);
                return;
            }
        }
        
        this.tell(new MessageBuilder("Couldn't find pack with name '" + name + "'"));
    }
}
