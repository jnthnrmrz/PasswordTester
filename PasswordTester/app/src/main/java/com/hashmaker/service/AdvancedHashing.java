package com.hashmaker.service;

import com.lambdaworks.crypto.SCryptUtil;
import com.hashmaker.data.Hash;

import org.mindrot.jbcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AdvancedHashing {

    private AdvancedAlgo m_algo;
    private double m_time;
    private Hash hash;

    public AdvancedHashing(AdvancedAlgo advancedAlgo) {
        m_algo = advancedAlgo;
        hash = new Hash();
    }

    public String hash(String password) {
        String hashed;
        switch (m_algo) {
            case PBKDF2:
                hashed = hashPBKDF2(password);
                hash.setType("PBKDF2");
                hash.setValue(hashed);
                hash.setTime(getTime());
                return hashed;
            case SCRYPT:
                hashed = hashSCRYPT(password);
                hash.setType("SCRYPT");
                hash.setValue(hashed);
                hash.setTime(getTime());
                return hashed;
            case BCRYPT:
                hashed = hashBCRYPT(password);
                hash.setType("BCRYPT");
                hash.setValue(hashed);
                hash.setTime(getTime());
                return hashed;
            default:
                hashed = null;
        }

        return hashed;
    }

    public double getTime() {
        return m_time;
    }

    private String hashPBKDF2(String password) {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = new byte[100];
        long start, end;

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);

        SecretKeyFactory skf;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            start = System.nanoTime();
            byte[] hash = skf.generateSecret(spec).getEncoded();
            end = System.nanoTime();

            m_time = (end - start) / 1000000.0;

            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "hash failed";
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return "hash failed";
        }
    }

    private String hashBCRYPT(String password) {
        long start, end;

        start = System.nanoTime();
        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        end = System.nanoTime();

        m_time = (end - start) / 1000000.0;

        return generatedSecuredPasswordHash;
    }


    private String hashSCRYPT(String password) {
        long start, end;

        start = System.nanoTime();
        String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
        end = System.nanoTime();

        m_time = (end - start) / 1000000.0;

        return generatedSecuredPasswordHash;
    }

    public Hash getHashInfo() {
        return hash;
    }

}

