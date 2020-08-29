package gtask.cli.commands;

import com.google.api.services.tasks.model.Task;
import com.google.api.services.tasks.model.Tasks;
import gtask.cli.App;
import gtask.cli.helpers.Symbols;
import gtask.cli.services.TaskService;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Command(name = "tasks",
        description = "List all tasks")
public class TasksCommand implements Runnable {
    TaskService taskService = new TaskService();

    @ParentCommand
    private App parentCommand;

    @Option(names = {"-l", "--list"}, description = "Get task of specific list.")
    private String list = "@default";

    @Option(names = {"-c", "--complete"}, description = "Set task as completed", arity = "*")
    private List<Integer> complete;

    @Option(names = {"-a", "--add"}, description = "Insert new task")
    private String newTask;

    public void printTasks() throws IOException, GeneralSecurityException {
        Tasks tasks = taskService.getTasks(list);
        int index = 1;
        for (Task task : tasks.getItems()) {
            System.out.printf("[%d] ", index++);
            System.out.print(task.getTitle());
            if (task.getStatus().equals("completed"))
                System.out.print(' ' + Symbols.checkmark());
            System.out.println();
        }
    }

    private void chooseList() throws IOException, GeneralSecurityException {

    }


    public void run() {
        try {
            if (newTask != null) {
                Task task = new Task().setTitle(newTask);
                taskService.createTask(list, task);
            }

            if  (complete != null) {
                taskService.complete(list, complete);
            }
            this.printTasks();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
