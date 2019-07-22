package com.scorer.gateway;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

@Component
public class CloseSystemOut {

    @PostConstruct
    public void redirectSystemOut() throws FileNotFoundException {
        if(false){
            System.setOut(new PrintStream(new FileOutputStream("C:\\outlog.txt")));
            System.setErr(new PrintStream(new FileOutputStream("C:\\errlog.txt")));
        }
    }

}
