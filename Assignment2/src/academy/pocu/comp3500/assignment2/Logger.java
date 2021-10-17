package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.Queue;

import java.io.BufferedWriter;

public final class Logger {
    static private Queue<Indent> indentedLogs;
    static private int currentIndentLv;

    static {
        clear();
    }

    public static void log(final String text) {
        indentedLogs.peek().insertLog(text);
    }

    public static void printTo(final BufferedWriter writer) {
        Indent indent;
        Queue<String> logs;
        String log;
        try {
            writer.flush();
            while(indentedLogs.getSize() > 0) {
                indent = indentedLogs.dequeue();
                logs = indent.getLogsQueue();
                while (logs.getSize() > 0) {
                    log = logs.dequeue();
                    writer.write(log);
                    writer.newLine();
                }
            }
        } catch(Exception e) {
            e.getStackTrace();
        }

    }

    public static void printTo(final BufferedWriter writer, final String filter) {
        Indent indent;
        Queue<String> logs;
        String log;
        try {
            writer.flush();
            while(indentedLogs.getSize() > 0) {
                indent = indentedLogs.dequeue();
                logs = indent.getLogsQueueIfHas(filter);
                while (logs.getSize() > 0) {
                    log = logs.dequeue();
                    writer.write(log);
                    writer.newLine();
                }
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
    }

    public static void clear() {
        indentedLogs = new Queue<>();
        currentIndentLv = 0;
        indentedLogs.enqueue(new Indent(currentIndentLv));
    }

    public static Indent indent() {
        currentIndentLv++;
        indentedLogs.enqueue(new Indent(currentIndentLv));
        return indentedLogs.peek();
    }

    public static void unindent() {
        currentIndentLv--;
        indentedLogs.enqueue(new Indent(currentIndentLv));
    }
}