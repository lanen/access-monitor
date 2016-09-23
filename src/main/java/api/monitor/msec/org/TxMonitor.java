package api.monitor.msec.org;

/**
 *
 * 一个进程只绑定一个服务名 serviceName
 *
 * @author evan
 * @Date 2016年09月23日T12:00
 */
public interface TxMonitor {


    /**
     *
     * @param filename
     * @return
     */
    boolean initialize(String filename);

    /**
     *
     * @param serviceName
     * @return
     */
    boolean initializeServiceName(String serviceName);


    /**
     *
     * 使用之前,必须初始化服务名, 调用 initializeServiceName
     * @param attrName
     * @param value
     * @return
     */
    boolean add(String attrName, int value);

    /**
     *
     * 使用之前,必须初始化服务名, 调用 initializeServiceName
     * @param attrName
     * @param value
     * @return
     */
    boolean set(String attrName, int value);


}
