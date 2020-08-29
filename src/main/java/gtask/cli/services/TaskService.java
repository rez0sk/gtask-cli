package gtask.cli.services;

import com.google.api.client.util.DateTime;
import com.google.api.services.tasks.model.Task;
import com.google.api.services.tasks.model.TaskLists;
import com.google.api.services.tasks.model.Tasks;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

public class TaskService extends TasksApi {
    public TaskLists getTaskLists() throws IOException, GeneralSecurityException {
        return getService().tasklists().list()
                .setMaxResults(10L)
                .execute();
    }

    public Tasks getTasks(String listID) throws IOException, GeneralSecurityException {
        return getService().tasks()
                .list(listID)
                .setShowDeleted(true)
                .setShowCompleted(true)
                .setShowHidden(true)
                .setMaxResults(10L)
                .execute();
    }

    public Task createTask(String list, Task task) throws IOException, GeneralSecurityException {
        return getService().tasks()
                .insert(list, task)
                .execute();
    }

    public void complete(String list, List<Integer> completed) throws IOException, GeneralSecurityException {
        int index = 1;
        DateTime now = new DateTime(new Date());
        for(Task task: getTasks(list).getItems()) {
            if (completed.contains(index++)) {
                System.out.printf("Updating %s ... ", task.getTitle()).println();
                task.setStatus("completed");
                task.setCompleted(now);
                getService().tasks().update(list, task.getId(), task).execute();
            }
        }
    }
}
