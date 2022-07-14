package iagopm.web.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class Spoof {

	private String serverName;
	private int serverPort;
	private String remoteAddr;
	private String remoteHost;
	private Locale locale;
	private String localAddr;
	private String localName;
	private int localPort;
	private int remotePort;
	private Timestamp createdOn;
	private String requestUrl;
	public Spoof() {
	}

	public Spoof(HttpServletRequest request) {
		this.requestUrl = request.getServletPath();
		this.serverName = request.getServerName();
		this.serverPort = request.getServerPort();
		this.remoteAddr = request.getRemoteAddr();
		this.remoteHost = request.getRemoteHost();
		this.locale = request.getLocale();
		this.localAddr = request.getLocalAddr();
		this.localName = request.getLocalName();
		this.localPort = request.getLocalPort();
		this.remotePort = request.getRemotePort();
		this.createdOn = Timestamp.from(Instant.now());
	}

	@Override
	public String toString() {
		return "S#" + requestUrl + "#" + serverName + "#" + serverPort + "#" + remoteAddr + "#" + remoteHost + "#"
				+ locale + "#" + localAddr + "#" + localName + "#" + localPort + "#" + remotePort + "#"
				+ createdOn.getTime();
	}
}