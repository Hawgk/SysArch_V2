public class Measurement {
    private double value;
    private String name;
    private long timestamp;
    private boolean isLogged;

    public Measurement(double value, String name) {
        this.value = value;
        this.name = name;
        this.timestamp = System.nanoTime();
        this.isLogged = false;
    }

    public double getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public boolean isLogged() {
        return isLogged;
    }
}
