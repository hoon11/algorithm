package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

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
        int userI = 0;
        int hashAlgorithmIndex = -1;
        for (userI = 0; userI < this.userTable.length; userI++) {
            String userPasswordHash = this.userTable[userI].getPasswordHash();
            String userEmail = this.userTable[userI].getEmail();

            if (hashAlgorithmIndex != -1) {
                plainTexts[userI] = rainbowTables[hashAlgorithmIndex].get(userPasswordHash);

                continue;
            }

            String passwordCRC32 = rainbowTables[0].get(userPasswordHash);
            if (passwordCRC32 != null) {
                plainTexts[userI] = passwordCRC32;
                hashAlgorithmIndex = 0;
                continue;
            }

            String passwordMD2 = rainbowTables[1].get(userPasswordHash);
            if (passwordMD2 != null) {
                plainTexts[userI] = passwordMD2;
                hashAlgorithmIndex = 1;
                continue;
            }

            String passwordMD5 = rainbowTables[2].get(userPasswordHash);
            if (passwordMD5 != null) {
                plainTexts[userI] = passwordMD5;
                hashAlgorithmIndex = 2;
                continue;
            }

            String passwordSHA1 = rainbowTables[3].get(userPasswordHash);
            if (passwordSHA1 != null) {
                plainTexts[userI] = passwordSHA1;
                hashAlgorithmIndex = 3;
                continue;
            }

            String passwordSHA265 = rainbowTables[4].get(userPasswordHash);
            if (passwordSHA265 != null) {
                plainTexts[userI] = passwordSHA265;
                hashAlgorithmIndex = 4;
                continue;
            }
        }

        return plainTexts;
    }
}