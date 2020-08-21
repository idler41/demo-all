package com.lfx.demo.generator.id;

import java.util.Random;

public class SnowflakeIdGen {

    /**
     * 起始的时间戳: 2020-08-21 00:00:00
     */
    private static final  long START_TIME = 1597939200000L;

    /**
     * 每一部分占用的位数
     */
    private static final  long SEQUENCE_BIT = 12; //序列号占用的位数
    private static final  long MACHINE_BIT = 5;   //机器标识占用的位数
    private static final  long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private static final  long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    private static final  long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private static final  long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final  long MACHINE_LEFT = SEQUENCE_BIT;
    private static final  long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private static final  long TIME_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private final long dataCenterId;  //数据中心
    private final long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastTime = -1L;//上一次时间戳

    private static final Random RANDOM = new Random();


    public SnowflakeIdGen(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATACENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than " + MAX_DATACENTER_NUM + " or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than " + MAX_MACHINE_NUM + " or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currStmp = getNewTime();
        if (currStmp < lastTime) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastTime) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                //seq 为0的时候表示是下一毫秒时间开始对seq做随机
                sequence = RANDOM.nextInt(100);
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号重新开始
            sequence = RANDOM.nextInt(100);
        }

        lastTime = currStmp;

        return (currStmp - START_TIME) << TIME_LEFT //时间戳部分
                | dataCenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewTime();
        while (mill <= lastTime) {
            mill = getNewTime();
        }
        return mill;
    }

    private long getNewTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        int t = 1 << 8;
        System.out.println(t);
        System.out.println(Integer.toBinaryString(t));
        SnowflakeIdGen snowFlake = new SnowflakeIdGen(2, 3);
        for (int i = 0; i < (1 << 4); i++) {
            System.out.println(snowFlake.nextId());
        }

    }
}