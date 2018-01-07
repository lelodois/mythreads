package br.com.lelo.threads;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.lelo.threads.serialize.Employee;
import br.com.lelo.threads.serialize.SerializeBase;
import br.com.lelo.threads.serialize.SerializeFile;
import br.com.lelo.threads.serialize.SerializeThreadsEmployee;

public class SerializeDesserializeTest {

    long start = 0;

    @Test
    public void serializeBaseTest() throws Exception {
        List<Employee> records = this.getItens(5000);
        SerializeBase<Employee> baseSerialize = new SerializeBase<Employee>();

        this.startTask("[Serialização 1]");
        List<String> files = baseSerialize.serializar(records);
        this.finishTask("[Serialização Base]");

        this.startTask("[Desserialização 1]");
        List<Employee> itens = baseSerialize.desserializar(files);
        this.finishTask("[Desserialização 1]");

        this.assertContains(records, itens);
        this.clearTest();
    }

    @Test
    public void serializeThreadsTest() throws Exception {
        List<Employee> records = this.getItens(5000);
        SerializeThreadsEmployee serializeThreads = new SerializeThreadsEmployee();

        this.startTask("[Serialização 2]");
        List<String> files = serializeThreads.serializar(records);
        this.finishTask("[Serialização 2]");

        this.startTask("[Desserialização 2]");
        List<Employee> itens = serializeThreads.desserializar(files);
        this.finishTask("[Desserialização 2]");

        this.assertContains(records, itens);
        this.clearTest();
    }

    private void clearTest() throws Exception {
        this.startTask("[Exclusão]");
        SerializeFile.deleteAll();

        for (File file : SerializeFile.getAllFiles()) {
            if (SerializeFile.isSerializedFile(file)) {
                Assert.fail("Serialização não excluida  ");
            }
        }
        System.out.println("************************************");
    }

    private void assertContains(List<Employee> originais, List<Employee> itens) {
        Assert.assertFalse(itens.isEmpty());
        Assert.assertFalse(originais.isEmpty());
        for (Employee employee : itens) {
            if (originais.contains(employee) == false) {
                Assert.fail("Objeto não desserializado");
            }
        }
    }

    private void startTask(String task) {
        System.out.println(task);
        start = System.currentTimeMillis();
    }

    private void finishTask(String task) {
        System.out.println(task + " [fim] - tempo: " + ((System.currentTimeMillis() - start) / 1000) + " segundos");
        start = 0;
    }

    private List<Employee> getItens(int size) {
        List<Employee> records = new ArrayList<Employee>();

        for (int indice = 0; indice < size; indice++) {
            records.add(new Employee(indice, "Employee " + indice, new Date()));
        }
        return records;
    }
}

