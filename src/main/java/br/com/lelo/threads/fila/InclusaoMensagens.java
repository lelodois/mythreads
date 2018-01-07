package br.com.lelo.threads.fila;

import java.util.Queue;

public class InclusaoMensagens implements Runnable {

    private int size;
    private final Queue<Integer> queue;

    public InclusaoMensagens(int size, Queue<Integer> queue) {
        this.size = size;
        this.queue = queue;
    }

    public void run() {
        for (int indice = 1; indice <= size; indice++) {
            queue.add(indice);
            System.out.println(getName() + " " + indice);
        }
    }

    public String getName() {
        return getClass().getSimpleName() + " - ";
    }
}
