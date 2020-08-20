package com.lfx.demo.generator;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

import static com.lfx.demo.util.StringUtil.isBlank;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-18 14:03:26
 */
public class TraceIdGenerator {

    private static String IP_16 = "ffffffff";
    private static final AtomicInteger COUNT = new AtomicInteger(1000);
    private static final String EMPTY_STRING = "";

    private static String P_ID_CACHE = null;

    static {
        try {
            String ipAddress = getInetAddress();
            if (ipAddress != null) {
                IP_16 = getIP_16(ipAddress);
            }
        } catch (Throwable e) {
            /*
             * empty catch block
             */
        }
    }

    public static String generate() {
        return getTraceId(IP_16, System.currentTimeMillis(), getNextId());
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(getInetAddress());
        System.out.println(IP_16);
        System.out.println(generate());
        System.out.println(generate().length());
        System.out.println(Integer.toHexString(1));
    }

    private static String getTraceId(String ip, long timestamp, int nextId) {
        return timestamp + nextId + getPID() + ip;
    }

    private static String getIP_16(String ip) {
        String[] ips = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String column : ips) {
            String hex = Integer.toHexString(Integer.parseInt(column));
            if (hex.length() == 1) {
                sb.append('0').append(hex);
            } else {
                sb.append(hex);
            }

        }
        return sb.toString();
    }

    private static int getNextId() {
        for (; ; ) {
            int current = COUNT.get();
            int next = (current > 9000) ? 1000 : current + 1;
            if (COUNT.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    private static String getInetAddress() {

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress address;
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && !address.getHostAddress().contains(":")) {
                        return address.getHostAddress();
                    }
                }
            }
            return null;
        } catch (Throwable t) {
            return null;
        }

    }

    private static String getPID() {
        //check pid is cached
        if (P_ID_CACHE != null) {
            return P_ID_CACHE;
        }
        String processName = ManagementFactory.getRuntimeMXBean().getName();

        if (isBlank(processName)) {
            return EMPTY_STRING;
        }

        String[] processSplitName = processName.split("@");

        if (processSplitName.length == 0) {
            return EMPTY_STRING;
        }

        String pid = processSplitName[0];

        if (isBlank(pid)) {
            return EMPTY_STRING;
        }
        P_ID_CACHE = pid;
        return pid;
    }
}
