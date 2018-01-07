package br.com.lelo.threads.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.google.common.collect.Lists;

public class SerializeBase<T> {

    public List<String> serializar(List<T> ts) throws Exception {
        List<String> filesNames = Lists.newArrayListWithExpectedSize(ts.size());
        for (T t : ts) {
            filesNames.add(serializar(t));
        }
        return filesNames;
    }

    public List<T> desserializar(List<String> filesNames) throws Exception {
        List<T> itens = Lists.newArrayListWithExpectedSize(filesNames.size());
        for (String filename : filesNames) {
            itens.add(desserializar(filename));
        }
        return itens;
    }

    public String serializar(T t) throws Exception {
        String fileName = SerializeFile.getFileName(t);
        FileOutputStream fo = new FileOutputStream(SerializeFile.getPathSerializacao(fileName));
        ObjectOutputStream oo = new ObjectOutputStream(fo);
        oo.writeObject(t);
        oo.close();
        return fileName;
    }

    @SuppressWarnings("unchecked")
    public T desserializar(String serializeFileName) throws Exception {
        FileInputStream fi = new FileInputStream(SerializeFile.getPathSerializacao(serializeFileName));
        ObjectInputStream oi = new ObjectInputStream(fi);
        T t = (T) oi.readObject();
        oi.close();
        return t;
    }

}
