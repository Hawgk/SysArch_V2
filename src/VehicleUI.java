public class VehicleUI {

	private ManagementController managementController;

	// hands over the reference to the ManagementController
	public VehicleUI(ManagementController managementController) {
		this.managementController = managementController;
	}

	public void Output_values() {
		var measurements = managementController.getCurrent_measurements();

		// iterates through all sensors and outputs their data (including Jitter)
		for (int i = 0; i < measurements.length; i++) {
			if (measurements[i] != null)
				System.out.println("Name: " + measurements[i].getName() + " | Value: " + measurements[i].getValue() + " | Jitter: " + Calculate_Jitter()[i]);
		}
	}

	// claculates Jitter based on the timestamps in the buffer (maximum latency - minimum latency)
	private long[] Calculate_Jitter() {
		long[] jitter = new long[managementController.getSensorCount()];
		long max_time = 0, min_time = 0;
		var buffer = managementController.getBuffer();

		// repeat calculation for each sensor
		for (int i = 0; i < managementController.getSensorCount(); i++) {
			
			// NULLCHECK
			if (buffer[1][i] == null) continue;
			
			// initial times
			min_time = buffer[1][i].getTimestamp() - buffer[0][i].getTimestamp();
			max_time = min_time;

			for (int j = 2; j < buffer.length; j++) {
				
				// NULLCHECK
				if (buffer[j][i] == null) continue;

				var difference = buffer[j][i].getTimestamp() - buffer[j-1][i].getTimestamp();

				if (min_time > difference) min_time = difference;
				else if (max_time < difference) max_time = difference;
			}
			
			// calculated jitter over the buffer
			jitter[i]=max_time-min_time;
		
		}
		return jitter;
	}
}
