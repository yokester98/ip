package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class to load and save file to local drive.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object to load and save file to local drive.
     *
     * @param filePath  Filepath of where to load/save file from/to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads file and reads data line by line.
     * Converts the data to Task objects.
     * Returns an ArrayList containing the Task objects.
     *
     * @return  ArrayList containing the Task objects.
     * @throws IOException  If fail to read the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        FileReader fReader = new FileReader(String.valueOf(this.filePath));
        BufferedReader reader = new BufferedReader(fReader);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] strArr;
            Task task;
            strArr = line.split(" \\| ");
            switch (strArr[0]) {
            case "T":
                task = new Todo(strArr[2]);
                if (strArr[1].equals("X")) {
                    task.setDone();
                }
                break;
            case "D":
                task = new Deadline(strArr[2], strArr[3]);
                if (strArr[1].equals("X")) {
                    task.setDone();
                }
                break;
            case "E":
                task = new Event(strArr[2], strArr[3]);
                if (strArr[1].equals("X")) {
                    task.setDone();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strArr[0]);
            }
            loadedTasks.add(task);
        }
        return loadedTasks;
    }

    /**
     * Make directory to where the file should be saved.
     */
    public void makeDirectory() {
        String home = System.getProperty("user.dir");
        java.nio.file.Path folderPath = java.nio.file.Paths.get(home, "data");
        File folder = new File(String.valueOf(folderPath));
        folder.mkdir();
        try {
            File file = new File(String.valueOf(filePath));
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file data.txt");
        }
    }

    /**
     * Save the file to the specified directory.
     *
     * @param tasks Tasklist to be saved to the file.
     * @throws IOException  If error saving tasklist to file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fWriter = new FileWriter(String.valueOf(filePath));
        BufferedWriter writer = new BufferedWriter(fWriter);
        StringBuilder output = new StringBuilder();
        for (Task task : tasks.getTaskList()) {
            output.append(task.getType()).append(" | ").append(task.getStatusIcon())
                    .append(" | ").append(task.getDescription());
            if (!task.getType().equals("T")) {
                output.append(" | ").append(task.getBy());
            }
            output.append("\n");
        }
        writer.write(output.toString());
        writer.close();
    }
}
