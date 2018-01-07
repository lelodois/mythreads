package br.com.lelo.threads.map;

import java.util.Iterator;
import java.util.Map;

public class ListMensagens implements Runnable {

    private final Map<Integer, String> map;

    public ListMensagens(Map<Integer, String> map) {
        this.map = map;
    }

    public void run() {
        for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext();) {
            Integer key = iterator.next();
            System.out.println(getName() + map.get(key));
        }
    }

    public String getName() {
        return getClass().getSimpleName() + " - ";
    }

}

