package br.com.lelo.threads.fila;

import java.util.Queue;

public class ProcessamentoMensagens implements Runnable {

    private final Queue<Integer> queue;

    public ProcessamentoMensagens(Queue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        while (queue.isEmpty() == false) {
            System.out.println(getName() + queue.poll());
        }
    }

    public String getName() {
        return getClass().getSimpleName() + " - ";
    }

}

