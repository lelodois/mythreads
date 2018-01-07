package br.com.lelo.threads;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Assert;
import org.junit.Test;

import br.com.lelo.threads.fila.InclusaoProcessamentoQueue;

public class InclusaoExclusaoQueueTest {

    @Test
    public void test1000() {
        this.start(1000, getSyncronizedQueue());
    }

    @Test
    public void test10000() {
        this.start(10000, getSyncronizedQueue());
    }

    @Test
    public void test100000() {
        this.start(100000, getSyncronizedQueue());
    }

    @Test(expected = Exception.class)
    public void test100000OnError() throws Exception {
        new InclusaoProcessamentoQueue().go(100000, new LinkedList<Integer>());
    }

    private ConcurrentLinkedQueue<Integer> getSyncronizedQueue() {
        return new ConcurrentLinkedQueue<Integer>();
    }

    private void start(int size, ConcurrentLinkedQueue<Integer> queue) {
        try {
            new InclusaoProcessamentoQueue().go(size, queue);
            System.out.println(queue.size());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Erro ao remover algum item");
        }
    }
}
