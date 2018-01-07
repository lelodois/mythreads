package br.com.lelo.threads.serialize;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.lelo.threads.commons.MyExecutorCompleteService;

public class SerializeThreadsEmployee {

    private final SerializeBase<Employee> serialize = new SerializeBase<Employee>();
    private static final int QT_ITENS_POR_THREAD = 50;

    public List<String> serializar(List<Employee> ts) throws Exception {
        final List<String> files = Lists.newArrayListWithExpectedSize(ts.size());
        MyExecutorCompleteService<String> executor = new MyExecutorCompleteService<String>();

        for (final List<Employee> partitioned : Lists.partition(ts, QT_ITENS_POR_THREAD)) {
            executor.addTasks(new Runnable() {
                public void run() {
                    serializar(partitioned, files);
                }
            });
        }
        executor.submitAndWaitingAll();
        return files;
    }

    public List<Employee> desserializar(List<String> files) throws Exception {
        final List<Employee> itens = Lists.newArrayListWithExpectedSize(files.size());
        MyExecutorCompleteService<String> executor = new MyExecutorCompleteService<String>();

        for (final List<String> partitioned : Lists.partition(files, QT_ITENS_POR_THREAD)) {
            executor.addTasks(new Runnable() {
                public void run() {
                    desserializar(itens, partitioned);
                }
            });
        }
        executor.submitAndWaitingAll();
        return itens;
    }

    private void serializar(List<Employee> ts, List<String> files) {
        for (Employee t : ts) {
            try {
                files.add(serialize.serializar(t));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void desserializar(List<Employee> itens, List<String> names) {
        for (String fileName : names) {
            try {
                itens.add(serialize.desserializar(fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
