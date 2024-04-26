package jeff.it.cryptography.service;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

	private static final StrongTextEncryptor encryptor;

	static {
		encryptor = new StrongTextEncryptor();
		encryptor.setPassword("123");
	}

//	private final StrongTextEncryptor encryptor;
//
//	public CryptoService(String password) {
//		this.encryptor = new StrongTextEncryptor();
//		this.encryptor.setPassword(System.getenv("AAP_KEY"));
//	}

	public static String encrypt(String rawText){
		return encryptor.encrypt(rawText);
	}

	public static String decrypt(String encryptedText){
		return encryptor.decrypt(encryptedText);
	}
}
