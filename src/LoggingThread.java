public class LoggingThread implements Runnable{
    // Thread that runs the Send_values() method of CommunicationController after each delay

    // Thread values and object references
    CommunicationController communicationController;
    String name;
    Thread t;

    // set the delay of the while loop (in milliseconds)
    private long delay_time = 100;


    // Reference to CommunicationController is handed over
    public LoggingThread(CommunicationController communicationController){
        
        this.communicationController = communicationController;

        // Create new Thread
        t = new Thread(this, "Reading Thread");
        
        // Setting second lowest priority
        t.setPriority(2);

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
                System.out.println("Logging...");
                this.communicationController.Send_values();
            }
            // catch the exception in case the Thread is interrupted
        }catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
    }
}
