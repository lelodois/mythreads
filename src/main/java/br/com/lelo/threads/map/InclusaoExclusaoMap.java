package br.com.lelo.threads.map;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Map;

import br.com.lelo.threads.commons.MyExecutorCompleteService;

public class InclusaoExclusaoMap {

    public void go(int size, Map<Integer, String> map) throws Exception {
        MyExecutorCompleteService<String> executor = new MyExecutorCompleteService<String>();

        for (Runnable thread : this.getAcoesThreads(size, map)) {
            executor.addTasks(thread);
        }

        executor.submitAndWaitingAll();
    }

    private List<Runnable> getAcoesThreads(int size, Map<Integer, String> map) {
        return newArrayList(new InclusaoMensagens(size, map), new ListMensagens(map), new ExclusaoMensagens(map));
    }

}
