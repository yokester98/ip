package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Deadline class to store deadline type of task.
 */
public class Deadline extends Task {

    protected String type;

    /**
     * Constructs Deadline object.
     *
     * @param description Description of task.
     * @param by    Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description, by);
        this.type = "D";
    }

    /**
     * Returns a String of the description of the task.
     *
     * @return  description of the task
     */
    public String getDescription() {
        return super.description;
    }

    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Return description and date of the task.
     *
     * @return  Description and date of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + super.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
