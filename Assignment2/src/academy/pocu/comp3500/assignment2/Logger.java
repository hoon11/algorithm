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
        indentedLogs = new ArrayList<Indent>();
        currentIndentLv = 0;
        indentedLogs.add(new Indent(currentIndentLv));
    }

    public static Indent indent() {
        currentIndentLv++;
        indentedLogs.add(new Indent(currentIndentLv));

        return indentedLogs.get(indentedLogs.getSize() - 1);
    }

    public static void unindent() {
        currentIndentLv--;
        indentedLogs.add(new Indent(currentIndentLv));
    }
}