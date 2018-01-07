package br.com.lelo.threads.serialize;

import java.io.File;

public class SerializeFile {

    private static final String SERIALIZED_FILE_EXTENSION = ".ser";

    private SerializeFile() {}

    public static void deleteAll() {
        for (File file : new File(getBasePath()).listFiles()) {
            if (isSerializedFile(file)) {
                file.delete();
            }
        }
    }

    public static boolean isSerializedFile(File file) {
        return file.getName().contains(SERIALIZED_FILE_EXTENSION);
    }

    public static File[] getAllFiles() {
        return new File(getBasePath()).listFiles();
    }

    public static String getPathSerializacao(String fileName) {
        return getBasePath() + File.separator + fileName;
    }

    public static String getFileName(Object object) {
        return object.toString() + SERIALIZED_FILE_EXTENSION;
    }

    private static String getBasePath() {
        return SerializeFile.class.getClassLoader().getResource(".").getPath();
    }

}

