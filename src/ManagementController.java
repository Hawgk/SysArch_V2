import java.io.*;
public class ManagementController {

	private Sensor[] sensors = null;
	private double min_distance;
	private Measurement[] current_measurements = null;
	private Measurement[][] buffer = null;
	private int sensor_count = 1;
	private int buffer_size = 10;

	public ManagementController() {
		current_measurements = new Measurement[sensor_count];
		buffer = new Measurement[buffer_size][sensor_count];
	}

	public void Read_sensors() {
		Measurement overflow[] = new Measurement[sensor_count];

		for(int i = 0; i < sensor_count; i++) {
			current_measurements[i] = new Measurement(Read_temp(), "CPU temp");

			if (buffer[buffer_size-1][i] != null) overflow[i] = buffer[buffer_size-1][i];

			for(int j = (buffer_size-1); j > 0; j--){
				buffer[j][i] = buffer[j-1][i];
			}

			buffer[0][i] = current_measurements[0];

			if(overflow[i] != null) {
				if(!(overflow[i].isLogged())) {
					// save to log file
					System.out.println("OVERFLOW! Name: " + overflow[i].getName() + " | Value: " + overflow[i].getValue() + " | Timestamp: " + overflow[i].getTimestamp());
				}
			}
		}
		overflow = null;
	}

	public double Read_temp() {
		double temp = -273;
		String fileName = "/sys/class/thermal/thermal_zone0/temp";
		String line = null;
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