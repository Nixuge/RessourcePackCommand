package me.nixuge.ressourcepackcommand.utils;

public class ArgsUtils {
    public static String joinArgs(final String[] args) {
        StringBuilder sb = new StringBuilder(args.length * 2);
        for(int i=0; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
