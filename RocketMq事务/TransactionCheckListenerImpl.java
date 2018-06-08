package com.xianjinxia.bigdata.MQ;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.common.message.MessageExt;

public class TransactionCheckListenerImpl implements TransactionCheckListener {
    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
        return null;
    }
}
