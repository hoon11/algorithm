package academy.pocu.comp3500.lab5;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

public class Bank {
    private HashMap<byte[], Long> accounts;
    private Cipher cipher;

    public Bank(byte[][] pubKeys, final long[] amounts) {
        this.accounts = new HashMap<byte[], Long>();

        for (int i = 0; i < pubKeys.length; i++) {
            this.accounts.put(pubKeys[i], amounts[i]);
        }
        try {
            this.cipher = Cipher.getInstance("RSA");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public long getBalance(final byte[] pubKey) {
        Long balance = this.accounts.get(pubKey);
        if (balance == null) {
            return 0;
        }

        return balance.longValue();
    }

    public boolean transfer(final byte[] from, byte[] to, final long amount, final byte[] signature) {
        PublicKey publicKey = null;
        if (amount <= 0) {
            return false;
        }

        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(from));

            this.cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] descriptedSignature = this.cipher.doFinal(signature);

            byte[] fromToAmount = getFromToAmount(from, to, amount);
            byte[] fromToAmountHash = getSHA256HashOrNull(fromToAmount);

            if (isEqual(descriptedSignature, fromToAmountHash)) {
                if (this.accounts.get(from).longValue() < amount) {
                    return false;
                }

                if (this.accounts.get(to) == null) {
                    return false;
                }

                // to.balance + amount > Long.MaxValue ?
                if (BigInteger.valueOf(this.accounts.get(to)).add(BigInteger.valueOf(amount)).compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
                    return false;
                }
                this.accounts.replace(from, this.accounts.get(from).longValue() - amount);
                this.accounts.replace(to, this.accounts.get(to).longValue() + amount);

                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return false;
    }

    private byte[] getFromToAmount(final byte[] from, byte[] to, final long amount) {
        byte[] bytes = new byte[from.length + to.length + 8];
        for (int i = 0; i < from.length; i++) {
            bytes[i] = from[i];
        }
        for (int i = 0; i < to.length; i++) {
            bytes[i + from.length] = to[i];
        }
        byte[] amountBytes = longToBytes(amount);
        for (int i = 0; i < amountBytes.length; i++) {
            bytes[i + from.length + to.length] = amountBytes[i];
        }

        return bytes;
    }

    private static byte[] longToBytes(long data) {
        return new byte[] { (byte) ((data >> 56) & 0xff), (byte) ((data >> 48) & 0xff), (byte) ((data >> 40) & 0xff), (byte) ((data >> 32) & 0xff), (byte) ((data >> 24) & 0xff), (byte) ((data >> 16) & 0xff), (byte) ((data >> 8) & 0xff), (byte) ((data >> 0) & 0xff) };
    }

    private byte[] getSHA256HashOrNull(byte[] values) {
        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-256");
            sha1.update(values);

            return sha1.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isEqual(byte[] a, byte[] b) {
        boolean ret = true;
        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    ret = false;
                    break;
                }
            }
        } else {
            ret = false;
        }
        return ret;
    }
}