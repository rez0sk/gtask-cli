package gtask.cli.services;

import com.google.api.services.tasks.model.TaskLists;
import com.google.api.services.tasks.model.Tasks;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class TaskService extends TasksApi {
    public TaskLists getTaskLists() throws IOException, GeneralSecurityException {
        return getService().tasklists().list()
                .setMaxResults(10L)
                .execute();
    }

    public Tasks getTasks(String listID) throws IOException, GeneralSecurityException {
        return getService().tasks()
                .list(listID)
                .setMaxResults(10L)
                .execute();
    }
}
