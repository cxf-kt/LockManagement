package com.bed_lock.utils;


 

import sun.misc.BASE64Decoder;

 

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher; 

import javax.crypto.KeyGenerator;

import java.security.Key; 

import java.security.SecureRandom;

 

@SuppressWarnings("restriction")

public class DESUtil {

 

	private static Key key;

    //自己的密钥

    private static String KEY_STR = "mykey";

    

    static { 

    	try { 

    		KeyGenerator generator = KeyGenerator.getInstance("DES");

    		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

    		secureRandom.setSeed(KEY_STR.getBytes());

    		generator.init(secureRandom);

    		key = generator.generateKey();

    		generator = null; 

    	} catch (Exception e) {

    		throw new RuntimeException(e); 

    	} 

    }

  

    /**

     * 对字符串进行加密，返回BASE64的加密字符串

     *

     * @param str

     * @return

     * @see [类、类#方法、类#成员]

     */ 

    public static String getEncryptString(String str) {

    	 BASE64Encoder base64Encoder = new BASE64Encoder();

    	 try { 

    		 byte[] strBytes = str.getBytes("UTF-8");

    		 Cipher cipher = Cipher.getInstance("DES");

    		 cipher.init(Cipher.ENCRYPT_MODE, key);

    		 byte[] encryptStrBytes = cipher.doFinal(strBytes);

    		 return base64Encoder.encode(encryptStrBytes);

    	 } catch (Exception e) {

    			 throw new RuntimeException(e); 

    	 } 

    }

 

    /**

     * 对BASE64加密字符串进行解密

     *

     */ 

    public static String getDecryptString(String str) {

    	BASE64Decoder base64Decoder = new BASE64Decoder();

    	try {

    		byte[] strBytes = base64Decoder.decodeBuffer(str);

    		Cipher cipher = Cipher.getInstance("DES");

    		cipher.init(Cipher.DECRYPT_MODE, key);

    		byte[] encryptStrBytes = cipher.doFinal(strBytes);

    		return new String(encryptStrBytes, "UTF-8");

    	} catch (Exception e) {

    		throw new RuntimeException(e);

    	}

    }

 

    public static void main(String[] args) {

    	String name = "root";

    	String password = "Chen_0215";

    	String encryname = getEncryptString(name);

    	String encrypassword = getEncryptString(password);

    	System.out.println("encryname : " + encryname);

    	System.out.println("encrypassword : " + encrypassword);

    	System.out.println("name : " + getDecryptString(encryname));

    	System.out.println("password : " + getDecryptString(encrypassword));

    }

 

}
