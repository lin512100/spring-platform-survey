package com.jtang.base.utils;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 远程操作工具类
 * @author lin512100
 * @date 2020/9/22
 */
@Component
@Slf4j
public class RemoteExecuteCommand {

    private static final String DEFAULT_CHART = "UTF-8";

    private static final String DEFAULT_IP = "127.0.0.1";

    private static final String DEFAULT_USERNAME = "root";

    private static final String DEFAULT_PASSWORD = "xxx";

    /**
     * 登录主机
     *
     * @return 登录成功返回true，否则返回false
     */
    public static Connection login(String ip, String userName, String userPwd) {

        if (ip == null) {
            ip = DEFAULT_IP;
            userName = DEFAULT_USERNAME;
            userPwd = DEFAULT_PASSWORD;
        }

        boolean flg = false;
        Connection conn = null;
        try {
            conn = new Connection(ip);
            //连接
            conn.connect();
            //认证
            flg = conn.authenticateWithPassword(userName, userPwd);
            if (flg) {
                log.info("=========登录成功=========" + conn.getHostname());
                return conn;
            }
        } catch (IOException e) {
            log.error("=========登录失败=========" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 远程执行shll脚本或者命令
     *
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     */
    public static Boolean execute(Connection conn, String cmd) {
        log.info("算法命令：" + cmd);
        String result = "";
        try {
            if (conn != null) {
                Session session = conn.openSession();
                //执行命令
                session.execCommand(cmd);
                result = processStdout(session.getStdout(), DEFAULT_CHART);
                //如果为得到标准输出为空，说明脚本执行出错了
                if (StringUtils.isBlank(result)) {
                    log.info("得到标准输出为空,链接conn:" + conn + ",执行的命令：" + cmd);
                    result = processStdout(session.getStderr(), DEFAULT_CHART);
                } else {
                    log.info("执行命令成功,链接conn:" + conn + ",执行的命令：" + cmd);
                }
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            log.info("执行命令失败,链接conn:" + conn + ",执行的命令：" + cmd + "  " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("执行结果~~~~~~~~~~~~~~~~~" + result);
        return !StringUtils.isBlank(result);
    }

    /**
     * 解析脚本执行返回的结果集
     *
     * @param in      输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     */
    private static String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuilder buffer = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
