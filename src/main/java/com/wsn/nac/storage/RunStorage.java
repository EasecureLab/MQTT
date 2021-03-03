package com.wsn.nac.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 9:50
 *
 * @version 1.0
 */
@Component
public class RunStorage implements CommandLineRunner {

    @Autowired
    StorageThread storageThread;
    @Override
    public void run(String... args) throws Exception {
        storageThread.start();
    }
}
