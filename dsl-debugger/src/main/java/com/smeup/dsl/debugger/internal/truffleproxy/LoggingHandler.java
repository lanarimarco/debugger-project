/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.internal.truffleproxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Attenzione è un catorcio. Mi serve per serializzare in modo elementare i logs
 * e fare un analisi del protocollo
 * @author marco.lanari
 */
class LoggingHandler extends Handler {
    
    private PrintStream stream;
    
    private static final String ANALISI_PROTOCOLLO_DIR = 
            "C:\\dati\\googledrive\\Documenti\\Lavoro\\clienti\\smeup\\dev\\jariko\\debugger\\work\\analisiprotocollo";
    
    void startLoggingSession(String truffleSessionId) throws IOException{
        if (stream != null) {
            stream.close();
        }
        stream = new PrintStream(new FileOutputStream(new File(ANALISI_PROTOCOLLO_DIR, truffleSessionId + ".log")));
    }
    
    void endLoggingSession() {
        if (stream != null) {
            stream.close();
        }
        stream = null;
    }
    
    @Override
    public void publish(LogRecord record) {
        stream.println(MessageFormat.format(record.getMessage(), record.getParameters()));
        flush();
    }

    @Override
    public void flush() {
        if (stream != null) {
            stream.flush();
        }
    }

    @Override
    public void close() throws SecurityException {
        if (stream != null) {
            stream.close();
        }
    }
    
    
    
    
}
