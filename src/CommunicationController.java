import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommunicationController {

	private MotorController motorController;
	private ManagementController managementController;

	// hands over the reference to the ManagementController
	public CommunicationController(ManagementController managementController) {
		this.managementController = managementController;
	}

	public void Send_values() throws IOException {
		var buffer = managementController.getBuffer();
		
		BufferedWriter my_writer = null;

		try{

			File my_file = new File("log.txt");
			if(my_file.createNewFile())System.out.println("File created: " + my_file.getName());

			my_writer = new BufferedWriter(new FileWriter(my_file,true));

			// iterates through every sensor and measurement time, checks if the data has been logged and outputs it
			for (int i = 0; i < buffer.length; i++) {
				for (int j = 0; i < buffer[i].length; i++) {
					
					if (buffer[i][j] == null) continue;
					
					// logs data if it hasn't been before and raises isLogged flag afterwards
					if (!buffer[i][j].isLogged()) {
						String log_string="Name: " + buffer[i][j].getName() + " | Value: " + buffer[i][j].getValue() + " | Timestamp: " + buffer[i][j].getTimestamp();
						System.out.println(log_string);
						my_writer.write(log_string);
						my_writer.newLine();
						buffer[i][j].setLogged(true);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (my_writer != null) my_writer.close();
		}
	}

	public void Receive_values() {
	}

}
