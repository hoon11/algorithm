package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

import java.io.BufferedWriter;

public final class Logger {
    static private ArrayList<Indent> indentedLogs;
    static private int currentIndentLv;

    static {
        clear();
    }

    public static void log(final String text) {
        Indent indentedLog = indentedLogs.get(indentedLogs.getSize() - 1);
        indentedLog.insertLog(text);
    }

    public static void printTo(final BufferedWriter writer) {
        Indent indent;
        ArrayList<String> logs;
        String log;
        try {
            writer.flush();
            int i = 0;
            while (i < indentedLogs.getSize()) {
                indent = indentedLogs.get(i);
                logs = indent.getLogs();
                int j = 0;
                while (j < logs.getSize()) {
                    log = logs.get(j);
                    writer.write(log);
                    writer.newLine();
                    j++;
                }
                i++;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static void printTo(final BufferedWriter writer, final String filter) {
        Indent indent;
        ArrayList<String> logs;
        String log;
        try {
            writer.flush();
            int i = 0;
            while (i < indentedLogs.getSize()) {
                indent = indentedLogs.get(i);
                logs = indent.getLogsIfHas(filter);
                int j = 0;
                while (j < logs.getSize()) {
                    log = logs.get(j);
                    writer.write(log);
                    writer.newLine();
                    j++;
                }
                i++;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void clear() {
        if (indentedLogs == null) {
            indentedLogs = new ArrayList<>();
        } else {
            indentedLogs.clear();
        }
        currentIndentLv = 0;
        indentedLogs.add(new Indent(currentIndentLv));
    }

    public static Indent indent() {
        currentIndentLv++;
        Indent last = indentedLogs.get(indentedLogs.getSize() - 1);
        Indent newIndent = new Indent(currentIndentLv);
        last.setChild(newIndent);
        indentedLogs.add(newIndent);

        return newIndent;
    }

    public static void unindent() {
        if ( currentIndentLv > 0) {
            currentIndentLv--;
        }
        Indent last = indentedLogs.get(indentedLogs.getSize() - 1);
        Indent newIndent = new Indent(currentIndentLv);
        last.setChild(newIndent);
        indentedLogs.add(newIndent);
    }
}