package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

public final class Indent {
    private ArrayList<String> logs;
    private int indentLv;
    private Indent child;
    final private String INDENT_CHAR = "  ";

    public Indent(int indentLv) {
        this.logs = new ArrayList<String>();
        this.indentLv = indentLv;
        this.child = null;
    }
    public void discard() {
        this.logs.clear();
        Indent child = this.child;
        while (child != null) {
            child.logs.clear();
            child = child.getChild();
        }
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

    public Indent getChild() {
        return child;
    }

    public void setChild(Indent child) {
        this.child = child;
    }
}