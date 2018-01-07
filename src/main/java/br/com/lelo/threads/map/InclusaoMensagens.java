package br.com.lelo.threads.map;

import java.util.Map;

public class InclusaoMensagens implements Runnable {

    private int size;
    private final Map<Integer, String> map;

    public InclusaoMensagens(int size, Map<Integer, String> map) {
        this.size = size;
        this.map = map;
    }

    public void run() {
        for (int indice = 1; indice <= size; indice++) {
            String value = "Indice > " + indice;
            map.put(indice, "Indice > " + value);
            System.out.println(getName() + " " + value);
        }
    }

    public String getName() {
        return getClass().getSimpleName() + " - ";
    }

}
