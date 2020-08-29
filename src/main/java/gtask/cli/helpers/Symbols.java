package gtask.cli.helpers;

public class Symbols {
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_RESET  = "\u001B[0m";

    public static String checkmark() {
        return ANSI_GREEN + '\u2713' + ANSI_RESET;
    }
}
