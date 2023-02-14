package org.example.service;

import org.springframework.lang.NonNull;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

final class AesGcmUtils {
    private static final String ALG_AES = "AES";
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private  static int nonceLength = 96 / 8;
    private static final int TAG_LENGTH_BIT = 16;//must be one of {128, 120, 112, 104, 96}

    private  static String kEncryptionVersionPrefix = "v10";
    private static String KEY = "Q43zL3p8MQoaj82mSVmLYO/wKyMl2nc3LDeYbBpU9oA=";

    static Base64.Decoder decoder = Base64.getDecoder();
    static Base64.Encoder encoder = Base64.getEncoder();

    public static void main(String[] args) throws Exception {
        byte[] encryptedValue = new byte[] {(byte)76,(byte)0x31,(byte)0x30,(byte)0x39,(byte)0xbe,(byte)0x44,(byte)0x49,(byte)0x80,(byte)0x49,(byte)0x6d,(byte)0x36,(byte)0x51,(byte)0xc3,(byte)0x71,(byte)0xa9,(byte)0xb2,(byte)0xfb,(byte)0xbf,(byte)0x1b,(byte)0x53,(byte)0xe7,(byte)0x90,(byte)0x9f,(byte)0x64,(byte)0x99,(byte)0x6e,(byte)0xeb,(byte)0xe9,(byte)0x6a,(byte)0x0b,(byte)0x39,(byte)0x95,(byte)0x10,(byte)0x4d,(byte)0xb2,(byte)0xf7,(byte)0x7f,(byte)0x6d,(byte)0x74,(byte)0x1f,(byte)0xba,(byte)0x62,(byte)0x23};
        byte[] encryptedKeyBytes = Base64.getDecoder().decode(
                "RFBBUEkBAAAA0Iyd3wEV0RGMegDAT8KX6wEAAADnU1C1lg83Sb8syZNQdxLFAAAAAAIAAAAAABBmAAAAAQAAIAAAADWFuaqy+2H9hgRFZfAYLU96/2+bt1dz0mpueAa2Gz5TAAAAAA6AAAAAAgAAIAAAAOYfbEpNHKu0uzLS3HQ3HHqMz3aM3+IQ+7KUtvonY0/FMAAAAF5AgEY4janvnz11or6/LCeUHrGQ0flqj7+nxMDQ757uv70bHnXcafNGVwVL89a/8EAAAABjkVkJyfcyxGC4WCG6l93L24T41IVgZthNlpsrFjVKKOoWsEZYPbiX1g8gTYw9boLntBUwJw57XvwqPQtYoPFz");
        encryptedKeyBytes = Arrays.copyOfRange(encryptedKeyBytes, "DPAPI".length(), encryptedKeyBytes.length);

        byte[] iv = Arrays.copyOfRange(encryptedValue, kEncryptionVersionPrefix.length(), kEncryptionVersionPrefix.length() + nonceLength);

        encryptedValue = Arrays.copyOfRange(encryptedValue, kEncryptionVersionPrefix.length() + nonceLength, encryptedValue.length);
        String encryptedValueString = new String(AesGcmUtils.decrypt(encryptedValue, decoder.decode(KEY)
                , iv));
        System.out.println(encryptedValueString);
    }
    /**
     * Get encrypted object
     *
     * @param key It must be 32-bit
     * @param iv  It must be 16-bit
     * @return
     */
    private static Cipher getCipher(@NonNull byte[] key, @NonNull byte[] iv) {
        SecretKeySpec keySpec = new SecretKeySpec(key, ALG_AES);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            GCMParameterSpec params = new GCMParameterSpec(TAG_LENGTH_BIT * 8, iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, params);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return cipher;
    }

    public static byte[] encrypt(@NonNull byte[] content, @NonNull byte[] key, @NonNull byte[] iv) throws Exception {
        Cipher cipher = getCipher(key, iv);
        if (cipher == null) {
            throw new NullPointerException("Failed to get encryption method");
        }
        return cipher.doFinal(content);
    }

    public static byte[] decrypt(@NonNull byte[] content, @NonNull byte[] key, @NonNull byte[] iv) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, ALG_AES);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec params = new GCMParameterSpec(TAG_LENGTH_BIT * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, params);
        return cipher.doFinal(content);
    }

    public static byte[] buildIv(String ivString) {
        byte[] derivative = ivString.getBytes();
        byte[] iv = new byte[16];
        System.arraycopy(derivative, 0, iv, 0, iv.length);
        return iv;
    }
}
