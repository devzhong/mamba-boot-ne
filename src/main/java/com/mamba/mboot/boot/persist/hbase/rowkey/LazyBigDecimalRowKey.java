package com.mamba.mboot.boot.persist.hbase.rowkey;

import java.io.IOException;
import java.math.BigDecimal;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;

public class LazyBigDecimalRowKey extends BigDecimalRowKey {
    private ImmutableBytesWritable rawBytes;

    public LazyBigDecimalRowKey() {
    }

    public Class<?> getDeserializedClass() {
        return ImmutableBytesWritable.class;
    }

    public Object deserialize(ImmutableBytesWritable w) throws IOException {
        if (this.rawBytes == null) {
            this.rawBytes = new ImmutableBytesWritable();
        }

        this.rawBytes.set(w.get(), w.getOffset(), w.getLength());
        super.skip(w);
        return this.rawBytes;
    }

    public BigDecimal getBigDecimal(ImmutableBytesWritable w) throws IOException {
        return (BigDecimal)super.deserialize(w);
    }
}