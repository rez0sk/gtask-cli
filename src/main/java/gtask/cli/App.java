package gtask.cli;

import gtask.cli.commands.ListsCommand;
import gtask.cli.commands.TasksCommand;
import picocli.CommandLine;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;

@Command(name = "gtask", mixinStandardHelpOptions = true, version = "gtask 0.1",
        description = "Google Task Cli Tool.")
public class App implements Callable<Integer> {

    @Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "MD5";


    public static void main(String... args) {
        CommandLine commandLine = new CommandLine(new App())
                .setExecutionStrategy(new CommandLine.RunAll());
        commandLine.addSubcommand("lists", ListsCommand.class);
        commandLine.addSubcommand("tasks", TasksCommand.class);
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}

