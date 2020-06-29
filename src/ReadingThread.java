public class ReadingThread implements Runnable{
    // Thread that runs the clients check for the reset value after each delay

    // Thread values and object references
    ManagementController managementController;
    String name;
    Thread t;

    // set the delay of the while loop (in milliseconds)
    private long delay_time = 100;

    public ReadingThread(ManagementController managementController){
        this.managementController=managementController;
        // Create new Thread
        t = new Thread(this, "Reading Thread");
        // Setting highest priority
        t.setPriority(10);
        // print out name
        System.out.println("New thread: " + t);
        // Starts Thread
        t.start();
    }


    public void run() {
        try {
            // run continuously
            while(true){
                // the Thread waits for the delay time
                Thread.sleep(delay_time);
                System.out.println("Reading...");
                this.managementController.Read_sensors();
            }
            // catch the exception in case the Thread is interrupted
        }catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
    }
}
