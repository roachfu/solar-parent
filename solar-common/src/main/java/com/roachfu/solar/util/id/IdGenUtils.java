package com.roachfu.solar.util.id;


import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * (a) id构成: 42位的时间前缀 + 10位的节点标识 + 12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 * 注意这里进行了小改动: snowkflake是5位的datacenter加5位的机器id; 这里变成使用10位的机器id
 * (b) 对系统时间的依赖性非常强，需关闭ntp的时间同步功能。当检测到ntp时间调整后，将会拒绝分配id
 *
 * @author roach
 */

@Slf4j
public class IdGenUtils {

    private final long workerId;
    /** 时间起始标记点，作为基准，一般取系统的最近时间 */
    private static final long EPOCH = 1530_958_262_854L;
    /** 机器标识位数 */
    private static final long WORKER_ID_BITS = 10L;
    /** 机器ID最大值: 1023 */
    private static final long MAX_WORKER_ID = -1L ^ -1L << WORKER_ID_BITS;
    /** 毫秒内自增位 */
    private static final long SEQUENCE_BITS = 12L;
    /** 12 */
    private static final long WORK_ID_SHIFT = SEQUENCE_BITS;
    /** 22 */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    /** 4095,111111111111,12位 */
    private static final long SEQUENCE_MASK = -1L ^ -1L << SEQUENCE_BITS;

    /** 0，并发控制 */
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private static IdGenUtils flowIdWorker = new IdGenUtils(getWorkerHostIp());

    private IdGenUtils(long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
                    MAX_WORKER_ID));
        }
        this.workerId = workerId;
    }
    
    /**
     * 获取下一个id/序列号
     * @return
     */
    public synchronized long nextId(){
        long timestamp = IdGenUtils.timeGen();
        // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1 & SEQUENCE_MASK;
            if (this.sequence == 0) {
                // 重新生成timestamp
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        
        if (timestamp < this.lastTimestamp) {
            log.error("clock moved backwards.Refusing to generate id for {} milliseconds", this
                    .lastTimestamp - timestamp);
        }
        
        this.lastTimestamp = timestamp;
        return timestamp - EPOCH << TIMESTAMP_LEFT_SHIFT | this.workerId << WORK_ID_SHIFT | this.sequence;
    }
    
    public static IdGenUtils getFlowIdWorkerInstance() {
        return flowIdWorker;
    }
    
    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = IdGenUtils.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = IdGenUtils.timeGen();
        }
        return timestamp;
    }
    
    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }
    
    /**
     * @return int
     */
    public static int getWorkerHostIp() {
        try {
            byte[] bytes = InetAddress.getLocalHost().getAddress();
            return Integer.valueOf(bytes[3] & 0xFF);
        } catch (UnknownHostException e) {
            log.error("un know host exception. . . ", e);
            return 1;
        }
    }
}
