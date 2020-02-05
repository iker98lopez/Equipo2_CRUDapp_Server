/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.ciphering;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * This class contains methods to decipher texts and creating hashings.
 *
 * @author iker lopez carrillo
 */
public class CipheringManager {

    /**
     * Logger to show error messages and exceptions.
     */
    private static final Logger LOGGER = Logger.getLogger("equipo2_crudapp_ciphering.HashCipher");

    /**
     * Generates a hashing from the byte array received and returns it as a
     * String in hexadecimal.
     *
     * @param text byte array to be ciphered.
     * @return hashing of the text received as a string in hexadecimal.
     */
    public static byte[] hashCipher(byte[] text) {

        MessageDigest messageDigest;
        byte hash[] = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(text);

            hash = messageDigest.digest();
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.warning("There was an error while ciphering. " + exception.getMessage());
        }
        return hash;
    }

    /**
     * This method receives a ciphered String, deciphers it and returns it as a
     * byte array.
     *
     * @param text String to decipher.
     * @return deciphered String as byte array.
     */
    public static byte[] decipherText(byte[] text) {
        byte[] decodedMessage = null;
        try {
            byte fileKey[] = fileReader("C:\\keys\\private.key");

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(fileKey);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decodedMessage = cipher.doFinal(text);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException exception) {
            LOGGER.warning("There was an error trying to decipher the text. " + exception.getClass() + " " + exception.getMessage());
        }
        return decodedMessage;
    }

    /*
    
    /**
     * This method converts the byte array text received to hexadecimal String.
     *
     * @param byteText byte array text to convert.
     * @return converted text in hexadecimal.
     */
    private static String byteToHex(byte[] byteText) {
        String hexText = "";
        for (int i = 0; i < byteText.length; i++) {
            String h = Integer.toHexString(byteText[i] & 0xFF);
            if (h.length() == 1) {
                hexText += "0";
            }
            hexText += h;
        }
        return hexText.toUpperCase();
    }

    /**
     * This method converts the hexadecimal string text received to byte array.
     *
     * @param hexText hexadecimal text to convert.
     * @return converted text in byte array.
     */
    private static byte[] hexToByte(String hexText) {
        if (hexText.length() % 2 == 1) {
            hexText = 0 + hexText;
        }
        byte[] byteText = new byte[hexText.length() / 2];
        for (int i = 0; i < hexText.length(); i += 2) {
            byteText[i / 2] = (byte) ((Character.digit(hexText.charAt(i), 16) << 4)
                    + Character.digit(hexText.charAt(i + 1), 16));
        }
        return byteText;
    }

    /**
     * This method reads the file in the path it receives and returns it as a
     * byte array.
     *
     * @param path Path of the file.
     * @return Byte array with the contents of the file.
     */
    public static byte[] fileReader(String path) {
        File file = new File(path);
        ByteArrayOutputStream out = null;
        InputStream in = null;

        try {
            byte[] buffer = new byte[256];
            out = new ByteArrayOutputStream();
            in = new FileInputStream(file);
            int read = 0;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (IOException exception) {
            LOGGER.warning("There was an error trying to read the key file. " + exception.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException exception) {
                    LOGGER.warning("There was an error trying to close the key file. " + exception.getMessage());
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException exception) {
                    LOGGER.warning("There was an error trying to close the key file. " + exception.getMessage());
                }
            }
        }
        return out.toByteArray();
    }
}
