public class VehicleUI {

	private ManagementController managementController;

	public VehicleUI(ManagementController managementController) {
		this.managementController = managementController;
	}

	public void Output_values() {
		var measurements = managementController.getCurrent_measurements();

		for (int i = 0; i < measurements.length; i++) {
			if (measurements[i] != null)
				System.out.println("Name: " + measurements[i].getName() + " | Value: " + measurements[i].getValue() + " | Jitter: " + Calculate_Jitter()[i]);
		}
	}

	private long[] Calculate_Jitter() {
		long[] jitter = new long[managementController.getSensorCount()];
		long max_time = 0, min_time = 0;
		var buffer = managementController.getBuffer();

		for (int i = 0; i < managementController.getSensorCount(); i++) {
			if (buffer[1][i] != null) {
				min_time = buffer[1][i].getTimestamp() - buffer[0][i].getTimestamp();
				max_time = min_time;

				for (int j = 2; j < buffer.length; j++) {
					if (buffer[j][i] == null) continue;

					var difference = buffer[j][i].getTimestamp() - buffer[j-1][i].getTimestamp();

					if (min_time > difference) min_time = difference;
					else if (max_time < difference) max_time = difference;
				}

				jitter[i]=max_time-min_time;
			}
		}
		return jitter;
	}
}