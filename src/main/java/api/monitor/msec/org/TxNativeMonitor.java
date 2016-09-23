package api.monitor.msec.org;

/**
 * @author evan
 * @Date 2016年09月23日T12:01
 */
public class TxNativeMonitor implements TxMonitor {


    @Override
    public boolean initialize(String filename) {
        return AccessMonitor.initialize(filename);
    }

    @Override
    public boolean initializeServiceName(String serviceName) {
        AccessMonitor.initServiceName(serviceName);
        return true;
    }

    @Override
    public boolean add(String attrName, int value) {

        if (AccessMonitor.getInstance().serviceName == null){
            throw new IllegalArgumentException(" class initializeServiceName first ");
        }

        return AccessMonitor.add(attrName,value);
    }

    @Override
    public boolean set(String attrName, int value) {

        if (AccessMonitor.getInstance().serviceName == null){
            throw new IllegalArgumentException(" class initializeServiceName first ");
        }
        return AccessMonitor.set(attrName,value);
    }


}
