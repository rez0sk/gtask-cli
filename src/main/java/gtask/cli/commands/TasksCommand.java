package gtask.cli.commands;

import com.google.api.services.tasks.model.Task;
import com.google.api.services.tasks.model.TaskList;
import com.google.api.services.tasks.model.Tasks;
import gtask.cli.App;
import gtask.cli.services.TaskService;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Command(name = "tasks",
        description = "List all tasks")
public class TasksCommand implements Runnable {
    TaskService taskService = new TaskService();

    @ParentCommand
    private App parentCommand;

    public void run() {
        try {
            Tasks tasks = taskService.getTasks("MDM3MjcyMTQxNDk2MzM0Mjk4MDE6MDow");
            for (Task task: tasks.getItems()) {
                System.out.println(task.getTitle());
            }
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
