public class DisplayThread implements Runnable{
    // Thread that runs the clients check for the reset value after each delay

    // Thread values and object references
    VehicleUI vehicleUI;
    String name;
    Thread t;

    // set the delay of the while loop (in milliseconds)
    private long delay_time = 100;

    // Reference to BoundaryObject is handed over from the ServerThread, so it can be passed down to the Client
    public DisplayThread(VehicleUI vehicleUI){
        this.vehicleUI = vehicleUI;
        // Create new Thread
        t = new Thread(this, "Reading Thread");
        // Setting lowest priority
        t.setPriority(1);
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
                System.out.println("Displaying...");
                this.vehicleUI.Output_values();
            }
            // catch the exception in case the Thread is interrupted
        }catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
    }
}
