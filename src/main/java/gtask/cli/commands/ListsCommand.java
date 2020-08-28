package gtask.cli.commands;

import com.google.api.services.tasks.model.TaskList;
import com.google.api.services.tasks.model.TaskLists;
import gtask.cli.App;
import gtask.cli.services.TaskService;
import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Command(name = "lists",
        description = "List all task lists")
public class ListsCommand implements Runnable {
    TaskService tasksApi = new TaskService();

    @ParentCommand
    private App parentCommand;

    public void run() {
        try {
            TaskLists taskLists = tasksApi.getTaskLists();
           for (TaskList taskList: taskLists.getItems()) {
               System.out.println(taskList.getTitle());
           }
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
