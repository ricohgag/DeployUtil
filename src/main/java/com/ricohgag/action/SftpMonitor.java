package com.ricohgag.action;

import com.jcraft.jsch.SftpProgressMonitor;

import java.text.NumberFormat;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author ricohgag
 * @date 2019/2/19 0:40
 */
public class SftpMonitor implements SftpProgressMonitor, Runnable {
    private long maxCount = 0L;

    public long startTime = 0L;

    private long uploaded = 0L;

    private boolean isScheduled = false;

    ScheduledExecutorService executorService;

    public SftpMonitor(long maxCount){
        this.maxCount = maxCount;
    }

    @Override
    public void init(int i, String s, String s1, long l) {
        System.err.println("*****************文件上传开始*****************");
    }

    @Override
    public boolean count(long l) {
        System.err.println(l);
        return false;
    }

    @Override
    public void end() {
        System.err.println("*****************文件上传结束*****************");
    }

    @Override
    public void run() {
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        String value = format.format((uploaded / (double) maxCount));
        System.out.println("已传输：" + uploaded / 1024 + "KB,传输进度：" + value);
        if (uploaded == maxCount) {
            stop();
            long endTime = System.currentTimeMillis();
            System.out.println("传输完成！用时：" + (endTime - startTime) / 1000 + "s");
        }

    }

    /**
     * 停止方法
     */
    public void stop() {
        boolean isShutdown = executorService.isShutdown();
        if (!isShutdown) {
            executorService.shutdown();
        }
    }

}
