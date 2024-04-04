package ui.listener;

import org.testng.IExecutionListener;

public interface Listener extends IExecutionListener {

    @Override
    public default void onExecutionFinish() {
        long endTime = System.currentTimeMillis();
        System.out.println("Inform all the suite have finished execution at" + endTime);
    }

    @Override
    public default void onExecutionStart() {
        long startTime = System.currentTimeMillis();
        System.out.println("Inform all the suite have started execution at" + startTime);
    }
}
