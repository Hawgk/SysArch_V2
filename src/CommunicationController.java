public class CommunicationController {

	private MotorController motorController;
	private ManagementController managementController;

	public CommunicationController(ManagementController managementController) {
		this.managementController = managementController;
	}

	public void Send_values() {
		var buffer = managementController.getBuffer();
		for (int i = 0; i < buffer.length; i++) {
			for (int j = 0; i < buffer[i].length; i++) {
				if (buffer[i][j] != null) {
					if (!buffer[i][j].isLogged()) {
						// Here values would be saved to file or sent
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