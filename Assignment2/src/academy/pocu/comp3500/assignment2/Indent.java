package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.Queue;

public final class Indent {
    private Queue<String> logs;
    private int indentLv;
    final private String INDENT_CHAR = "  ";

    public Indent(int indentLv) {
        this.logs = new Queue<String>();
        this.indentLv = indentLv;
    }
    public void discard() {
        this.logs = new Queue<String>();
    }

    public Queue<String> getLogsQueue() {
        return this.logs;
    }

    public Queue<String> getLogsQueueIfHas(String target) {
        Queue<String> filteredQueue = new Queue<String>();
        String log;
        for (int i = 0; i < this.logs.getSize(); i++) {
            log = this.logs.dequeue();
            if (log.equals(target)) {
                filteredQueue.enqueue(log);
            }
        }

        return logs;
    }

    public void insertLog(String log) {
        String indent = "";
        for (int i = 0; i < this.indentLv; i++) {
            indent = indent.concat(this.INDENT_CHAR);
        }
        this.logs.enqueue(indent.concat(log));
    }

    public boolean hasLog() {
        return this.logs.getSize() > 0;
    }
}