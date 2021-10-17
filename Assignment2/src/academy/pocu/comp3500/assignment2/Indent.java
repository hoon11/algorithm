package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;
import academy.pocu.comp3500.assignment2.datastructure.Queue;

public final class Indent {
    private ArrayList<String> logs;
    private int indentLv;
    final private String INDENT_CHAR = "  ";

    public Indent(int indentLv) {
        this.logs = new ArrayList<String>();
        this.indentLv = indentLv;
    }
    public void discard() {
        this.logs = new ArrayList<String>();
    }

    public ArrayList<String> getLogs() {
        return this.logs;
    }

    public ArrayList<String> getLogsIfHas(String target) {
        ArrayList<String> filteredLogs = new ArrayList<String>();
        String log;
        for (int i = 0; i < this.logs.getSize(); i++) {
            log = this.logs.get(i);
            if (log.contains(target)) {
                filteredLogs.add(log);
            }
        }

        return filteredLogs;
    }

    public void insertLog(String log) {
        String indent = "";
        for (int i = 0; i < this.indentLv; i++) {
            indent = indent.concat(this.INDENT_CHAR);
        }
        this.logs.add(indent.concat(log));
    }

    public boolean hasLog() {
        return this.logs.getSize() > 0;
    }
}