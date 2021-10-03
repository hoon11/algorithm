package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.CRC32;

public final class Cracker {
    private User[] userTable;
    private String email;
    private String password;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] plainTexts = new String[this.userTable.length];
        int hashAlgorithmIndex = -1;

        User findUser = null;
        for (int i = 0; i < this.userTable.length; i++) {
            if (this.userTable[i].getEmail().equals(this.email)) {
                findUser = this.userTable[i];
                break;
            }
        }

        if (isCRC32(password, findUser.getPasswordHash())) {
            hashAlgorithmIndex = 0;
        } else if (isMD2(password, findUser.getPasswordHash())) {
            hashAlgorithmIndex = 1;
        } else if (isMD5(password, findUser.getPasswordHash())) {
            hashAlgorithmIndex = 2;
        } else if (isSHA1(password, findUser.getPasswordHash())) {
            hashAlgorithmIndex = 3;
        } else if (isSHA256(password, findUser.getPasswordHash())) {
            hashAlgorithmIndex = 4;
        }

        if (hashAlgorithmIndex == -1) {
            return plainTexts;
        }

        for (int i = 0; i < this.userTable.length; i++) {
            plainTexts[i] = rainbowTables[hashAlgorithmIndex].get(this.userTable[i].getPasswordHash());
        }

        return plainTexts;
    }

    private boolean isCRC32(String password, String passwordHash) {
        CRC32 crc = new CRC32();
        crc.update(password.getBytes());

        return passwordHash.equals(String.valueOf(crc.getValue()));
    }

    private boolean isMD2(String password, String passwordHash) {
        MessageDigest md2 = null;
        try {
            md2 = MessageDigest.getInstance("MD2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md2 != null) {
            md2.update(password.getBytes());
        }

        String passwordHashTry = Base64.getEncoder().encodeToString(md2.digest());
        return passwordHash.equals(passwordHashTry);
    }

    private boolean isMD5(String password, String passwordHash) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md5 != null) {
            md5.update(password.getBytes());
        }

        String passwordHashTry = Base64.getEncoder().encodeToString(md5.digest());
        return passwordHash.equals(passwordHashTry);
    }

    private boolean isSHA1(String password, String passwordHash) {
        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (sha1 != null) {
            sha1.update(password.getBytes());
        }

        String passwordHashTry = Base64.getEncoder().encodeToString(sha1.digest());
        return passwordHash.equals(passwordHashTry);
    }

    private boolean isSHA256(String password, String passwordHash) {
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (sha256 != null) {
            sha256.update(password.getBytes());
        }

        String passwordHashTry = Base64.getEncoder().encodeToString(sha256.digest());
        return passwordHash.equals(passwordHashTry);
    }


}