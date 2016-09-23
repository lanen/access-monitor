package api.monitor.msec.org;

/**
 * @author evan
 * @Date 2016年09月23日T12:13
 */
public class TxWindowMonitor implements TxMonitor {

    @Override
    public boolean initialize(String filename) {
        return true;
    }

    @Override
    public boolean initializeServiceName(String serviceName) {
        return true;
    }

    @Override
    public boolean add(String attrName, int value) {
        return true;
    }

    @Override
    public boolean set(String attrName, int value) {
        return true;
    }
}
