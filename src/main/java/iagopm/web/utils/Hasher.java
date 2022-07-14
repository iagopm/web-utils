package iagopm.web.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import iagopm.web.model.Message;

@Component
public class Hasher {

	@Value("${salt}")
	private String salt;

	public Message hash(String input) {
		if (input.isEmpty() || input.length() > 256) {
			return null;
		}
		String inputToHash = "";
		inputToHash = resverseOdd(input);
		inputToHash += concatNumberDependingOnStringProperties(input);
		return new Message(DigestUtils.sha512Hex(inputToHash + salt));
	}

	private String concatNumberDependingOnStringProperties(String input) {
		String stringToAdd = "";
		double total = 0;
		boolean isPI = false;
		boolean isRAD = false;
		boolean isAureum = false;
		int lowerCaseCounter = 0;
		// If contains a number
		if (input.matches(".*[0-9]")) {
			isPI = true;
		}
		// If contains a upper case char
		if (!input.equals(input.toLowerCase())) {
			isAureum = true;
		}
		for (int iterator = 0; iterator < input.length(); iterator++) {
			// If contains more than three lower case char
			if (Character.isLowerCase(input.charAt(iterator))) {
				lowerCaseCounter += 1;
				if (lowerCaseCounter >= 3) {
					isRAD = true;
				}
			}
		}
		if (isAureum) {
			double inputMultiplied = (1 + Math.sqrt(5)) / 2 * input.length();
			stringToAdd += inputMultiplied + "";
			total += inputMultiplied;
		}
		if (isPI) {
			double inputMultiplied = Math.PI * input.length();
			stringToAdd += inputMultiplied + "";
			total += inputMultiplied;
		}
		if (isRAD) {
			double inputMultiplied = Math.toRadians(input.length()) * input.length();
			stringToAdd += inputMultiplied + "";
			total += inputMultiplied;
		}
		stringToAdd += total + "";
		return stringToAdd;
	}

	private String resverseOdd(String input) {
		if ((input.length() % 2) == 0) {
			return input;
		} else {
			return reverseString(input);
		}
	}

	private String reverseString(String string) {
		StringBuilder stringReversed = new StringBuilder();
		for (int i = string.length() - 1; i >= 0; i--) {
			stringReversed.append(string.charAt(i));
		}
		return stringReversed.toString();
	}
}