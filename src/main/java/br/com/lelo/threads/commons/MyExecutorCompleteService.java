package br.com.lelo.threads.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;

public class MyExecutorCompleteService<T> {

    private List<Runnable> tasks = Lists.newArrayList();

    public void addTasks(Runnable t) {
        tasks.add(t);
    }

    public List<T> submitAndWaitingAll() throws Exception {
        if (tasks.isEmpty() || tasks == null) return new ArrayList<T>();

        ExecutorService executorService = Executors.newFixedThreadPool(tasks.size());
        CompletionService<T> tasksService = new ExecutorCompletionService<T>(executorService);

        for (Runnable task : tasks)
            tasksService.submit(task, null);

        List<T> results = Lists.newArrayList();

        for (int indice = 1; indice <= tasks.size(); indice++)
            results.add(tasksService.take().get());

        executorService.shutdown();
        tasks.clear();
        return results;
    }
}
