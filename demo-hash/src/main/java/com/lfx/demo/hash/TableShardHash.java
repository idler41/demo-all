package com.lfx.demo.hash;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-20 15:57:47
 */
public class TableShardHash extends StringConsistentHash {

    public TableShardHash(int realNodeAmount, int virtualNodeAmount) {
        super(realNodeAmount, virtualNodeAmount);
    }

    @Override
    protected String buildNodeName(int virtualNode, int realNode) {
        return "virtual-" + virtualNode + "-table-" + realNode;
    }
}
