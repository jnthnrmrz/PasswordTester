package com.hashmaker.service;

import android.util.Log;

import com.hashmaker.data.Hash;

import java.security.MessageDigest;

public class HashAlgo {

    private MessageDigest m_digest;
    private double m_time;
    private Hash hash;

    public static HashAlgo getInstance(Algorithm Algorithm){
        switch (Algorithm){
            case MD5: return new HashAlgo("MD5");
            case SHA1: return  new HashAlgo("SHA-1");
            case SHA256: return new HashAlgo("SHA-256");
            case SHA384: return new HashAlgo("SHA-384");
            case SHA512: return  new HashAlgo("SHA-512");
            default: return new HashAlgo("MD5");
        }
    }

    private HashAlgo(String algo){
        try {
            hash = new Hash();
            hash.setType(algo);
            m_digest = java.security.MessageDigest.getInstance(algo);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private String hash(String password){

        //get the hash and time it takes.
        byte[] messageDigest = timedHash(password);
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        for (int i=0; i<messageDigest.length; i++) {
            // hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        Log.i("HashAlgo", "hash: " + hexString.toString());

        return hexString.toString();

    }

    private byte[] timedHash(String password){

        //hash and time
        long startTime = System.nanoTime();
        m_digest.update(password.getBytes());
        long endTime = System.nanoTime();

        //calculate the milliseconds it took to hash
        m_time = (endTime - startTime) / 1000000.0;
        byte messageDigest[] = m_digest.digest();
        return messageDigest;
    }

    public double getTime(){
        return  m_time;
    }

    public Hash getHashInfo(String password) {
        hash.setValue(this.hash(password));
        hash.setTime(this.getTime());
        return hash;
    }


}
