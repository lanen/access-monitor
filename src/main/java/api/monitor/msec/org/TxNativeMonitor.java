package api.monitor.msec.org;

/**
 * @author evan
 * @Date 2016年09月23日T12:01
 */
public class TxNativeMonitor implements TxMonitor {


    @Override
    public boolean initialize(String filename) {
        try{
            return AccessMonitor.initialize(filename);
        }catch (Throwable e){
        }
        return false;
    }

    @Override
    public boolean initializeServiceName(String serviceName) {
        try{
            AccessMonitor.initServiceName(serviceName);
            return true;
        }catch (Throwable e){
        }
        return false;
    }

    @Override
    public boolean add(String attrName, int value) {

        if (AccessMonitor.getInstance().serviceName == null){
            throw new IllegalArgumentException(" class initializeServiceName first ");
        }
        try{
            return AccessMonitor.add(attrName,value);
        }catch (Throwable e){
        }
        return false;
    }

    @Override
    public boolean set(String attrName, int value) {

        if (AccessMonitor.getInstance().serviceName == null){
            throw new IllegalArgumentException(" class initializeServiceName first ");
        }
        try{
            return AccessMonitor.set(attrName,value);
        }catch (Throwable e){
        }
        return false;
    }


}
