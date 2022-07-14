package iagopm.web;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import iagopm.web.model.Message;
import iagopm.web.utils.Hasher;

@RestController
public class ApiController {
	private static Logger logger = LogManager.getLogger(ApiController.class);

	@Value("${absolutePathToResumeScript}")
	private String absolutePathToResumeScript;
	@Value("${resumeScript}")
	private String resumeScript;
	
	@Autowired
	private Hasher hasher;

	@PostMapping(path = "/api/hash", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> hash(@RequestParam("input") String input) {
		return ResponseEntity.ok(hasher.hash(input));
	}

	@PostMapping(path = "/api/currentDate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> currentDate(@RequestParam("input") String input) {
		return ResponseEntity.ok(new Message(Instant.now().toString()));
	}
	
	@PostMapping(path = "/api/resume", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> resume(@RequestParam("input") String input) {
		try {
			Process process = new ProcessBuilder
					("cmd", "/c", "cd", absolutePathToResumeScript, "&", "python", resumeScript, "\"" + input + "\"")
					.start();
			String stdout = IOUtils.toString(process.getInputStream(), Charset.defaultCharset());
			return ResponseEntity.ok(new Message(stdout));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return ResponseEntity.badRequest().body(new Message("Error"));
		}

	}
}