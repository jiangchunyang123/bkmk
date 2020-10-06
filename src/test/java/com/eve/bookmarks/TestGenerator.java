package com.eve.bookmarks;

public class TestGenerator {
    public void generator(Class clazz) {
        generatorService(clazz);
        generatorController(clazz);
    }

    private void generatorService(Class clazz) {
        String clazzName = clazz.getName();
        String serviceName = clazzName + "Service";
        String serviceImplName = serviceName + "Impl";

    }

    private void generatorController(Class clazz) {

    }

    void generatorFile(String folder) {

    }
}
