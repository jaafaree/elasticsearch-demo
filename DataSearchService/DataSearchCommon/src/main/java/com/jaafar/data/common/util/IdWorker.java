package com.jaafar.data.common.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @date 2018/3/8 9:53
 */
@Component
public class IdWorker {
    /**
     *     定义一个起始时间 2016-6-1 00:00:00
     */
    private static final long START_TIME_STAMP = 1464710400000L;

    private static final long WORKERID_BITS = 5L;
    private static final long DATACENTERID_BITS = 5L;
    private static final long MAX_WORKERID = ~(-1L << WORKERID_BITS);
    private static final long MAX_DATACENTERID = ~(-1L << DATACENTERID_BITS);

    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKERID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTERID_SHIFT = SEQUENCE_BITS + WORKERID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKERID_BITS + DATACENTERID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    private static final Random R = new Random();

    private final long workerId;
    private final long dataCenterId;
    private final long idEpoch;
    private long lastTimestamp = -1L;
    private long sequence = 0;

    public IdWorker() {
        this(START_TIME_STAMP);
    }

    public IdWorker(long idEpoch) {
        this(R.nextInt((int) MAX_WORKERID), R.nextInt((int) MAX_DATACENTERID), 0, idEpoch);
    }

    public IdWorker(long workerId, long dataCenterId, long sequence) {
        this(workerId, dataCenterId, sequence, START_TIME_STAMP);
    }

    public IdWorker(long workerId, long dataCenterId, long sequence, long idEpoch) {
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
        this.idEpoch = idEpoch;

        if (workerId < 0 || workerId > MAX_WORKERID) {
            throw new IllegalArgumentException("workerId is illegal: " + workerId);
        }
        if (dataCenterId < 0 || dataCenterId > MAX_DATACENTERID) {
            throw new IllegalArgumentException("dataCenterId is illegal: " + dataCenterId);
        }

        if (idEpoch >= timeGen()) {
            throw new IllegalArgumentException("idEpoch is illegal: " + idEpoch);
        }
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getTime() {
        return timeGen();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new IllegalArgumentException("Clock moved backwards.");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - idEpoch) << TIMESTAMP_LEFT_SHIFT) | (dataCenterId <<
                DATACENTERID_SHIFT) | (workerId << WORKERID_SHIFT) | sequence;
    }

    public long getIdTimestamp(long id) {
        return idEpoch + (id >> TIMESTAMP_LEFT_SHIFT);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
