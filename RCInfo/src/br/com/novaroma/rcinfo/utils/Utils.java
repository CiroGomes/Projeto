package br.com.novaroma.rcinfo.utils;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import br.com.novaroma.rcinfo.entities.Person;

public class Utils {
	@SuppressWarnings("rawtypes")
	public static int generateCode(Map map) {
		Random codeGenerate = new Random();
		int code = 0;
		while (code <= 9999 || map.containsKey(String.valueOf(code))) {
			code = codeGenerate.nextInt(99999);
		}
		return code;
	}

	public static String criptografarSenha(String password) throws Exception {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigestAdminPassword[] = algorithm.digest(password.getBytes("UTF-8"));

		StringBuilder hexStringAdminPassword = new StringBuilder();
		for (byte b : messageDigestAdminPassword) {
			hexStringAdminPassword.append(String.format("%02X", 0xFF & b));
		}
		return hexStringAdminPassword.toString();

	}

	public static int validateBirthday(Person client) {
		Calendar calendar = Calendar.getInstance();

		int birthday = calendar.get(Calendar.DAY_OF_YEAR) - client.getBirthday().get(Calendar.DAY_OF_YEAR);
		return birthday >= 0 ? calendar.get(Calendar.YEAR) - client.getBirthday().get(Calendar.YEAR)
				: calendar.get(Calendar.YEAR) - client.getBirthday().get(Calendar.YEAR) - 1;
	}
}
