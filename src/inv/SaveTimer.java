//This is just a Timer that runs in the background and makes sure the file stays updated throughout program usage
package inv;

import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

public class SaveTimer extends Timer {

    Product product = new Product();
    //Interval is in seconds
    private int interval = 30;
    private SaveTimeTask saveTimeTask = new SaveTimeTask();
    SaveTimer(){
        super();
        this.scheduleAtFixedRate(saveTimeTask, 0, interval*1000);
    }
    private class SaveTimeTask extends TimerTask{

        @Override
        public void run() {
            try {
                product.read();//read list
                product.save();//Call the save function
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            //The line below will just print that this method has been called
            System.out.println("SaveTimer has been hit!");
        }
    }
}
