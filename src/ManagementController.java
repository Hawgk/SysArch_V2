import java.io.*;
// Manages incoming data and provides buffer
public class ManagementController {

	// Currently not in use
	private Sensor[] sensors = null;
	private double min_distance;
	// Buffers
	private Measurement[] current_measurements = null;
	// FIFO buffer
	private Measurement[][] buffer = null;
	// Static values
	private static int sensor_count = 1;
	private static int buffer_size = 10;

	public ManagementController() {
		current_measurements = new Measurement[sensor_count];
		buffer = new Measurement[buffer_size][sensor_count];
	}

	public void Read_sensors() {
		// Overflow buffer
		Measurement overflow[] = new Measurement[sensor_count];
		// Loop for reading sensor data
		for(int i = 0; i < sensor_count; i++) {
			current_measurements[i] = new Measurement(Read_temp(), "CPU temp");
			// Checking last element of buffer vor values
			// Catching overflow
			if (buffer[buffer_size-1][i] != null) overflow[i] = buffer[buffer_size-1][i];
			// Moving buffer to free up first element
			for(int j = (buffer_size-1); j > 0; j--){
				buffer[j][i] = buffer[j-1][i];
			}

			buffer[0][i] = current_measurements[0];
			// Checking if overflow value has been logged
			if(overflow[i] != null) {
				if(!(overflow[i].isLogged())) {
					// Output log in case of overflow
					System.out.println("OVERFLOW! Name: " + overflow[i].getName() + " | Value: " + overflow[i].getValue() + " | Timestamp: " + overflow[i].getTimestamp());
				}
			}
		}
		overflow = null;
	}

	// Function reading CPU temperature of Raspberry Pi
	// CPU temperature is saved to file on the RPi
	public double Read_temp() {
		// Setting error temperature if reading fails
		double temp = -273;
		String fileName = "/sys/class/thermal/thermal_zone0/temp";
		String line = null;
		// Reading value from temperature file
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				temp = (Integer.parseInt(line) / 1000.0);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		return temp;
	}

	public void Get_min_distance() {

	}

	public void Set_min_distance() {

	}

	public Measurement[] getCurrent_measurements() {
		return current_measurements;
	}

	public Measurement[][] getBuffer() {
		return buffer;
	}

	public int getSensorCount() {
		return sensor_count;
	}
}