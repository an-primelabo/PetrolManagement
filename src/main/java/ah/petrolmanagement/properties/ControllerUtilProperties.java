package ah.petrolmanagement.properties;

public class ControllerUtilProperties {
	private static final ControllerUtilProperties singleton = new ControllerUtilProperties();

	private String host;
	private int connectionTimeout;
	private int socketTimeout;

	private ControllerUtilProperties() {
		this.host = "http://localhost:8080/PetrolManagement/";
		this.connectionTimeout = 200;
		this.socketTimeout = 20000;
	}

	public String getHost() {
		return host;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public static ControllerUtilProperties getInstance() {
		return singleton;
	}
}