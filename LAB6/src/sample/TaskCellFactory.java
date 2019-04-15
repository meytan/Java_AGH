package sample;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class TaskCellFactory implements Callback<ListView<Task>, ListCell<Task>>
{
    @Override
    public ListCell<Task> call(ListView<Task> listview)
    {
        return new TaskCell();
    }
}
