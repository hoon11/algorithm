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
        int j = 0;
        for (int i = 0; i < this.userTable.length; i++) {
            while (j < rainbowTables.length && hashAlgorithmIndex == -1) {
                if (rainbowTables[j].get(this.userTable[i].getPasswordHash())) {
                    hashAlgorithmIndex = j;
                }
                j++;
            }
        }

        if (hashAlgorithmIndex != -1) {
            return plainTexts;
        }

        for (int i = 0; i < this.userTable.length; i++) {
            plainTexts[i] = rainbowTables[hashAlgorithmIndex].get(this.userTable[i].getPasswordHash());
        }
        
        return plainTexts;
    }
}