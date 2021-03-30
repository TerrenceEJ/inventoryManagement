//This class is for the cycle counting functionality

package inv;

import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;

public class CycleCount extends TableCell<Product, Integer> {
    public CycleCount(){}

    @Override
    protected void updateItem(Integer item, boolean empty){
        super.updateItem(item, empty);
        if(item != null){
            if(item.intValue() <= 0){
                setText(item.toString());
                setTextFill(Color.RED);
            }
            else{
                setText(item.toString());
                setTextFill(Color.BLACK);
            }
        }
        else{
            setText(null);
        }

    }

}
