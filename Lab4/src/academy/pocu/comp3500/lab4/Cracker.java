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
        while (userI < this.userTable.length) {
            String userPasswordHash = this.userTable[userI].getPasswordHash();
            String userEmail = this.userTable[userI].getEmail();


            if (userEmail.equals(this.email)) {
                //plainTexts[userI] = this.password;
            }

            String passwordCRC32 = rainbowTables[0].get(userPasswordHash);
            if (passwordCRC32 != null) {
                plainTexts[userI] = passwordCRC32;
            }

            String passwordMD2 = rainbowTables[1].get(userPasswordHash);
            if (passwordMD2 != null) {
                plainTexts[userI] = passwordMD2;
            }

            String passwordMD5 = rainbowTables[2].get(userPasswordHash);
            if (passwordMD5 != null) {
                plainTexts[userI] = passwordMD5;
            }

            String passwordSHA1 = rainbowTables[3].get(userPasswordHash);
            if (passwordSHA1 != null) {
                plainTexts[userI] = passwordSHA1;
            }

            String passwordSHA265 = rainbowTables[4].get(userPasswordHash);
            if (passwordSHA265 != null) {
                plainTexts[userI] = passwordSHA265;
            }

            userI++;
        }

        return plainTexts;
    }
}