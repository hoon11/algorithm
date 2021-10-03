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

        int hashAlgorithmIndex = -1;
        for (int userI = 0; userI < this.userTable.length; userI++) {
            String userPasswordHash = this.userTable[userI].getPasswordHash();

            String passwordCRC32 = rainbowTables[0].get(userPasswordHash);
            if (passwordCRC32 != null) {
                hashAlgorithmIndex = 0;
                break;
            }

            String passwordMD2 = rainbowTables[1].get(userPasswordHash);
            if (passwordMD2 != null) {
                hashAlgorithmIndex = 1;
                break;
            }

            String passwordMD5 = rainbowTables[2].get(userPasswordHash);
            if (passwordMD5 != null) {
                hashAlgorithmIndex = 2;
                break;
            }

            String passwordSHA1 = rainbowTables[3].get(userPasswordHash);
            if (passwordSHA1 != null) {
                hashAlgorithmIndex = 3;
                break;
            }

            String passwordSHA265 = rainbowTables[4].get(userPasswordHash);
            if (passwordSHA265 != null) {
                hashAlgorithmIndex = 4;
                break;
            }
        }

        if (hashAlgorithmIndex == -1) {
            return plainTexts;
        }

        for (int userI = 0; userI < this.userTable.length; userI++) {
            String userPasswordHash = this.userTable[userI].getPasswordHash();
            plainTexts[userI] = rainbowTables[hashAlgorithmIndex].get(userPasswordHash);
        }
        
        return plainTexts;
    }
}