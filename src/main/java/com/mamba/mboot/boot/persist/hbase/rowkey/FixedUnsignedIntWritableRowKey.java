package com.mamba.mboot.boot.persist.hbase.rowkey;

import java.io.IOException;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.IntWritable;

public class FixedUnsignedIntWritableRowKey extends FixedIntWritableRowKey {
    public FixedUnsignedIntWritableRowKey() {
    }

    protected IntWritable invertSign(IntWritable iw) {
        iw.set(iw.get() ^ -2147483648);
        return iw;
    }

    public void serialize(Object o, ImmutableBytesWritable w) throws IOException {
        this.invertSign((IntWritable)o);
        super.serialize(o, w);
        this.invertSign((IntWritable)o);
    }

    public Object deserialize(ImmutableBytesWritable w) throws IOException {
        return this.invertSign((IntWritable)super.deserialize(w));
    }
}
