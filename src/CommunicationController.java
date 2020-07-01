public class CommunicationController {

	private MotorController motorController;
	private ManagementController managementController;

	// hands over the reference to the ManagementController
	public CommunicationController(ManagementController managementController) {
		this.managementController = managementController;
	}

	public void Send_values() {
		var buffer = managementController.getBuffer();
		
		// iterates through every sensor and measurement time, checks if the data has been logged and outputs it
		for (int i = 0; i < buffer.length; i++) {
			for (int j = 0; i < buffer[i].length; i++) {
				if (buffer[i][j] != null) {
					
					// logs data if it hasn't been before and raises isLogged flag afterwards
					if (!buffer[i][j].isLogged()) {
						System.out.println("Name: " + buffer[i][j].getName() + " | Value: " + buffer[i][j].getValue() + " | Timestamp: " + buffer[i][j].getTimestamp());
						buffer[i][j].setLogged(true);
					}
				}
			}
		}
	}

	public void Receive_values() {
	}

}
