package com.xavier.designpattern.dp22decorator.file;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Xavier on 2018-02-21.
 */
public class EncryptOutputStream extends FilterOutputStream {
    public EncryptOutputStream(OutputStream os) {
        super(os);
    }

    @Override
    public void write(int b) throws IOException {
        b = b+2;
        if(b>=97+26) {
            b=b-26;
        }
        super.write(b);
    }
}
