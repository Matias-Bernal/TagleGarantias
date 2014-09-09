package common;

public class RootAndIp {

	/* Valores por defecto - verificar el conf.ini */
	private static String ip_servidor="";
	private static String ip_cliente="";
	private static int port= 43210;
	private static String db="";
	private static String username = "";
	private static String password = "";
	private static float valor_mano_obra_renault = new Float(0);
	private static float valor_mano_obra_nissan = new Float(0);
	private static String path_manual = "";
	private static String path_reportes = "";
	private static String smtpHost = "";
	private static String smtpserverport = "";
	private static String mailfabirca = "";
	private static String mailrepuestos = "";
	private static String passrepuestos = "";
	

	public static void setConf(String nameFile) {
		if ((nameFile == null)||(nameFile.trim().length() == 0)){
			nameFile = "conf.ini";
		}
		IniFile ini = new IniFile(nameFile);
		ip_servidor = ini.getParameters("ip_servidor");
		ip_cliente= ini.getParameters("ip_cliente");
		db = ini.getParameters("db");
		port = Integer.parseInt(ini.getParameters("port"));
		username = ini.getParameters("username");
		password = ini.getParameters("password");
		valor_mano_obra_renault = Float.parseFloat(ini.getParameters("valor_mano_obra_renault"));
		valor_mano_obra_nissan = Float.parseFloat(ini.getParameters("valor_mano_obra_nissan"));
		path_reportes = ini.getParameters("path_reportes");
		smtpHost = ini.getParameters("smtpHost");
		smtpserverport = ini.getParameters("smtpserverport");
		mailrepuestos = ini.getParameters("mailrepuestos");
		passrepuestos = ini.getParameters("passrepuestos");
		path_manual = ini.getParameters("path_manual");
		mailfabirca = ini.getParameters("mailfabrica");
	}

	public static String getIp_servidor() {
		return ip_servidor;
	}

	public static void setIp_servidor(String ip_servidor) {
		RootAndIp.ip_servidor = ip_servidor;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		RootAndIp.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		RootAndIp.password = password;
	}

	public static String getDb() {
		return db;
	}

	public static void setDb(String db) {
		RootAndIp.db = db;
	}

	public static String getIp_cliente() {
		return ip_cliente;
	}

	public static void setIp_cliente(String ip_cliente) {
		RootAndIp.ip_cliente = ip_cliente;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		RootAndIp.port = port;
	}

	public static float getValor_mano_obra_nissan() {
		return valor_mano_obra_nissan;
	}

	public static void setValor_mano_obra_nissan(float valor_mano_obra_nissan) {
		RootAndIp.valor_mano_obra_nissan = valor_mano_obra_nissan;
	}

	public static float getValor_mano_obra_renault() {
		return valor_mano_obra_renault;
	}

	public static void setValor_mano_obra_renault(float valor_mano_obra_renault) {
		RootAndIp.valor_mano_obra_renault = valor_mano_obra_renault;
	}

	public static String getPath_reportes() {
		return path_reportes;
	}

	public static void setPath_reportes(String path_reportes) {
		RootAndIp.path_reportes = path_reportes;
	}

	public static String getSmtpHost() {
		return smtpHost;
	}

	public static void setSmtpHost(String smtpHost) {
		RootAndIp.smtpHost = smtpHost;
	}

	public static String getSmtpserverport() {
		return smtpserverport;
	}

	public static void setSmtpserverport(String smtpserverport) {
		RootAndIp.smtpserverport = smtpserverport;
	}

	public static String getMailrepuestos() {
		return mailrepuestos;
	}

	public static void setMailrepuestos(String mailrepuestos) {
		RootAndIp.mailrepuestos = mailrepuestos;
	}

	public static String getPath_manual() {
		return path_manual;
	}

	public static void setPath_manual(String path_manual) {
		RootAndIp.path_manual = path_manual;
	}

	public static String getMailfabirca() {
		return mailfabirca;
	}

	public static void setMailfabirca(String mailfabirca) {
		RootAndIp.mailfabirca = mailfabirca;
	}

	public static String getPassrepuestos() {
		return passrepuestos;
	}

	public static void setPassrepuestos(String passrepuestos) {
		RootAndIp.passrepuestos = passrepuestos;
	}
	
}

