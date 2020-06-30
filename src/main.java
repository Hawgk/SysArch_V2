public class main {
	private static ManagementController management_controller;
	private static CommunicationController communication_controller;
	private static VehicleUI vehicle_ui;	

	public static void main(String[] args) {
		// Creating obejcts
		management_controller = new ManagementController();
		communication_controller = new CommunicationController(management_controller);
		vehicle_ui = new VehicleUI(management_controller);

		// Starting reading Thread
		ReadingThread readingThread = new ReadingThread(management_controller);
		// Starting logging Thread
		LoggingThread loggingThread = new LoggingThread(communication_controller);
		// Starting displaying Thread
		DisplayThread displayThread = new DisplayThread(vehicle_ui);

		while(true){ }
	}

}
