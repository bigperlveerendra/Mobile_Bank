package com.example.mobilebankingapp;


import java.security.MessageDigest;

import javax.crypto.*;
import javax.crypto.spec.*;

import android.widget.Toast;

public class Encryption {

  static SecretKeySpec skeySpec;
  private static Cipher cipher;
  
  public Encryption(byte [] keyraw) throws Exception{
    if(keyraw == null){
      byte[] bytesOfMessage = "".getBytes("UTF-8");
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] bytes = md.digest(bytesOfMessage);
      
      skeySpec = new SecretKeySpec(bytes, "AES");
      cipher = Cipher.getInstance("AES/CBC/NoPadding");
    }
    else{
    
    skeySpec = new SecretKeySpec(keyraw, "AES");
    cipher = Cipher.getInstance("AES/CBC/NoPadding");
    
    }
  }
  
  public Encryption(String passphrase) throws Exception{
    byte[] bytesOfMessage = passphrase.getBytes("UTF-8");
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] thedigest = md.digest(bytesOfMessage);
    skeySpec = new SecretKeySpec(thedigest, "AES");
    
    
    cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    System.out.println(skeySpec);
  }
  
  public Encryption() throws Exception{
    byte[] bytesOfMessage = "".getBytes("UTF-8");
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] thedigest = md.digest(bytesOfMessage);
    skeySpec = new SecretKeySpec(thedigest, "AES");
    
    skeySpec = new SecretKeySpec(new byte[16], "AES");
    cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
  }
  
  public static byte[] encrypt (byte[] plaintext) throws Exception{
    //returns byte array encrypted with key
    
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
    
    byte[] ciphertext =  cipher.doFinal(plaintext);
    return ciphertext;
  }
  
  public static byte[] decrypt (byte[] ciphertext) throws Exception{
    //returns byte array decrypted with key
    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
    
    byte[] plaintext = cipher.doFinal(ciphertext);
    
    return plaintext;
  }
  
  
}