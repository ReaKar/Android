package StartingMain;

import com.periodicThreads.periodicThread;

/**
 * Created by giannis on 12/18/15.
 */
public class PeriodicThreadWrapper {
    private Thread mythread;
    private periodicThread myperiodic;
    public PeriodicThreadWrapper(Thread mythread,periodicThread myperiodic){
        this.mythread=mythread;
        this.myperiodic=myperiodic;
    }

    public periodicThread getMyperiodic() {
        return myperiodic;
    }

    public Thread getMythread() {
        return mythread;
    }
}
