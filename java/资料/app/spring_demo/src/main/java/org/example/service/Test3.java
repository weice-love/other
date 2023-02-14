package org.example.service;

import com.github.windpapi4j.WinDPAPI;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/30 17:48
 */
public class Test3 {

    public static void main(String[] args) {
        test();
    }

    public static void test()
    {
        Security.addProvider(new BouncyCastleProvider());

        int keyLength = 256 / 8;
        int nonceLength = 96 / 8;
        String kEncryptionVersionPrefix = "v10";
        int GCM_TAG_LENGTH = 16;

        try
        {
            byte[] encryptedKeyBytes = Base64.getDecoder().decode(
//                    "RFBBUEkBAAAA0Iyd3wEV0RGMegDAT8KX6wEAAACtP/XjPc54RaDgxj8Eef6QAAAAAAIAAAAAABBmAAAAAQAAIAAAAHICJrBWI0qPkRwEH6iO4zyo4cupd1kX23HTvlGjbf3rAAAAAA6AAAAAAgAAIAAAANV1OHmguiAyFxk6vAFOb+K1bwNCjUshXByRmwbXYd8mMAAAAKzGG5QNq4NliAapY5N3rKaS+kqJNmYJJrla5tZ7LS/9Z39jogumIA0zjkypIiG7EkAAAACrrL+zxA4OSuc8onLmqfimVj/lhh21n8ERnTNMk+67dFnoy2KzcUgk8mJfKforKbgNRH5RcVcNOh4Lz/LcUgMu");
                    "RFBBUEkBAAAA0Iyd3wEV0RGMegDAT8KX6wEAAADnU1C1lg83Sb8syZNQdxLFAAAAAAIAAAAAABBmAAAAAQAAIAAAADWFuaqy+2H9hgRFZfAYLU96/2+bt1dz0mpueAa2Gz5TAAAAAA6AAAAAAgAAIAAAAOYfbEpNHKu0uzLS3HQ3HHqMz3aM3+IQ+7KUtvonY0/FMAAAAF5AgEY4janvnz11or6/LCeUHrGQ0flqj7+nxMDQ757uv70bHnXcafNGVwVL89a/8EAAAABjkVkJyfcyxGC4WCG6l93L24T41IVgZthNlpsrFjVKKOoWsEZYPbiX1g8gTYw9boLntBUwJw57XvwqPQtYoPFz");
            System.out.println(new String(encryptedKeyBytes));
//            assertTrue(new String(encryptedKeyBytes).startsWith("DPAPI"));
            encryptedKeyBytes = Arrays.copyOfRange(encryptedKeyBytes, "DPAPI".length(), encryptedKeyBytes.length);

            WinDPAPI winDPAPI = WinDPAPI.newInstance(WinDPAPI.CryptProtectFlag.CRYPTPROTECT_UI_FORBIDDEN);
            byte[] keyBytes = winDPAPI.unprotectData(encryptedKeyBytes);

//            byte[] keyBytes = Base64.getDecoder().decode("Q43zL3p8MQoaj82mSVmLYO/wKyMl2nc3LDeYbBpU9oA=");
            System.out.println(Base64.getEncoder().encodeToString(keyBytes));
//            System.out.println(HexDumpHelper.dumpHexString(keyBytes));
//            assertEquals(keyLength, keyBytes.length);

//            byte[] encryptedValue = new byte[] {(byte)76,(byte)0x31,(byte)0x30,(byte)0x39,(byte)0xbe,(byte)0x44,(byte)0x49,(byte)0x80,(byte)0x49,(byte)0x6d,(byte)0x36,(byte)0x51,(byte)0xc3,(byte)0x71,(byte)0xa9,(byte)0xb2,(byte)0xfb,(byte)0xbf,(byte)0x1b,(byte)0x53,(byte)0xe7,(byte)0x90,(byte)0x9f,(byte)0x64,(byte)0x99,(byte)0x6e,(byte)0xeb,(byte)0xe9,(byte)0x6a,(byte)0x0b,(byte)0x39,(byte)0x95,(byte)0x10,(byte)0x4d,(byte)0xb2,(byte)0xf7,(byte)0x7f,(byte)0x6d,(byte)0x74,(byte)0x1f,(byte)0xba,(byte)0x62,(byte)0x23};
            byte[] encryptedValue = new byte[] {(byte)0x76,(byte)0x31,(byte)0x30,(byte)0x0a,(byte)0xe4,(byte)0x86,(byte)0xe8,(byte)0x4b,(byte)0x7b,(byte)0xb9,(byte)0xf7,(byte)0xd1,(byte)0x04,(byte)0x7e,(byte)0xc6,(byte)0xd0,(byte)0xd9,(byte)0xa2,(byte)0x6c,(byte)0x47,(byte)0x54,(byte)0xa2,(byte)0xcb,(byte)0x58,(byte)0x94,(byte)0xdf,(byte)0x87,(byte)0xfc,(byte)0xd8,(byte)0x0b,(byte)0x24,(byte)0x4b,(byte)0x21,(byte)0x68,(byte)0x81,(byte)0x90,(byte)0xda,(byte)0xf8,(byte)0xba,(byte)0x6d,(byte)0x20,(byte)0x9e,(byte)0x5d,(byte)0x7f,(byte)0x41,(byte)0x4b,(byte)0x67,(byte)0x09,(byte)0x85,(byte)0xbf,(byte)0xd6,(byte)0x74,(byte)0xce,(byte)0x0a,(byte)0x36,(byte)0xc2,(byte)0xe0,(byte)0x42,(byte)0xda,(byte)0x06,(byte)0xaa,(byte)0x22,(byte)0x8b};
//            byte[] encryptedValue = new byte[] {(byte)76,(byte)0x31,(byte)0x30,(byte)0xcb,(byte)0x5e,(byte)0x43,(byte)0xd1,(byte)0x60,(byte)0x44,(byte)0xfd,(byte)0xb0,(byte)0x91,(byte)0xf6,(byte)0x60,(byte)0x70,(byte)0x4c,(byte)0xd7,(byte)0x05,(byte)0xe8,(byte)0x16,(byte)0xb2,(byte)0xc8,(byte)0x22,(byte)0xb5,(byte)0xb1,(byte)0xe9,(byte)0x5b,(byte)0x84,(byte)0x9f,(byte)0xe3,(byte)0xe3,(byte)0xcb,(byte)0xe6,(byte)0x5d,(byte)0xe0,(byte)0xb3,(byte)0xbc,(byte)0xf6,(byte)0x55,(byte)0x94,(byte)0x78,(byte)0xe5,(byte)0x8a};
//            byte[] encryptedValue = new byte[] { (byte) 0x76, (byte) 0x31, (byte) 0x30, (byte) 0x74, (byte) 0x90, (byte) 0x31, (byte) 0x14, (byte) 0xed, (byte) 0x50, (byte) 0xac, (byte) 0x4e, (byte) 0xf9, (byte) 0x7e, (byte) 0x6b, (byte) 0x62, (byte) 0x17, (byte) 0x01, (byte) 0x52, (byte) 0x5f, (byte) 0x52, (byte) 0x78, (byte) 0xcd, (byte) 0x24, (byte) 0x1a, (byte) 0x34, (byte) 0x0f, (byte) 0xc2, (byte) 0xbd, (byte) 0x2a, (byte) 0xc3, (byte) 0x2f, (byte) 0x00, (byte) 0x5d, (byte) 0x0d, (byte) 0x56, (byte) 0xf5, (byte) 0xa6, (byte) 0x84, (byte) 0x59 };
//            System.out.println(HexDumpHelper.dumpHexString(encryptedValue));
            System.out.println(Base64.getEncoder().encodeToString(encryptedValue));

            // Obtain the nonce.
            byte[] nonce = Arrays.copyOfRange(encryptedValue, kEncryptionVersionPrefix.length(), kEncryptionVersionPrefix.length() + nonceLength);
//            System.out.println(HexDumpHelper.dumpHexString(nonce));
            System.out.println(Base64.getEncoder().encodeToString(nonce));

            // Strip off the versioning prefix before decrypting.
            encryptedValue = Arrays.copyOfRange(encryptedValue, kEncryptionVersionPrefix.length() + nonceLength, encryptedValue.length);
//            System.out.println(HexDumpHelper.dumpHexString(encryptedValue));
            System.out.println(Base64.getEncoder().encodeToString(encryptedValue));

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

            String encryptedValueString = new String(cipher.doFinal(encryptedValue));
            System.err.println("密码" + encryptedValueString);
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
}
