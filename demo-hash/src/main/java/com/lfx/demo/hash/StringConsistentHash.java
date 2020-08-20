package com.lfx.demo.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import lombok.Getter;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-20 15:28:19
 */
public class StringConsistentHash implements ShardHash<String> {

    @Getter
    private final int realNodeAmount;
    @Getter
    private final int virtualNodeAmount;

    @SuppressWarnings("UnstableApiUsage")
    private final HashFunction hash;

    private final SortedMap<Integer, Integer> bucketMap;

    public StringConsistentHash(int realNodeAmount, int virtualNodeAmount) {
        if (realNodeAmount < 1 || virtualNodeAmount < 1) {
            throw new IllegalArgumentException("参数不能为负数:realNodeAmount=" + realNodeAmount + ",virtualNodeAmount=" + virtualNodeAmount);
        }
        this.realNodeAmount = realNodeAmount;
        this.virtualNodeAmount = virtualNodeAmount;
        //noinspection UnstableApiUsage
        this.hash = Hashing.murmur3_32();
        this.bucketMap = new TreeMap<>();

        for (int i = 0; i < virtualNodeAmount; i++) {
            for (int j = 0; j < realNodeAmount; j++) {
                String virtualNodeName = buildNodeName(i, j);
                //noinspection UnstableApiUsage
                this.bucketMap.put(this.hash.hashUnencodedChars(virtualNodeName).asInt(), j);
            }
        }

//        System.out.println(bucketMap.values());
    }

    @Override
    public int hash(String s) {
        //noinspection UnstableApiUsage
        SortedMap<Integer, Integer> tail = bucketMap.tailMap(hash.hashUnencodedChars(s).asInt());
        // 如果没有比该key更大的值，则从第一个node开始，形成环路
        return tail.isEmpty() ? bucketMap.get(bucketMap.firstKey()) : tail.get(tail.firstKey());
    }

    protected String buildNodeName(int virtualNode, int realNode) {
        return "virtual-" + virtualNode + "-node-" + realNode;
    }

}
