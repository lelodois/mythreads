package br.com.lelo.threads.fila;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Queue;

import br.com.lelo.threads.commons.MyExecutorCompleteService;

public class InclusaoProcessamentoQueue {

    public void go(int size, Queue<Integer> queue) throws Exception {
        MyExecutorCompleteService<String> executor = new MyExecutorCompleteService<String>();

        for (Runnable thread : this.getAcoesThreads(size, queue)) {
            executor.addTasks(thread);
        }

        executor.submitAndWaitingAll();
    }

    private List<Runnable> getAcoesThreads(int size, Queue<Integer> map) {
        return newArrayList(new InclusaoMensagens(size, map), new ProcessamentoMensagens(map));
    }

}
