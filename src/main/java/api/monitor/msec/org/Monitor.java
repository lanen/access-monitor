package api.monitor.msec.org;

/**
 * @author evan
 * @Date 2016年09月23日T11:59
 */
public final class Monitor {

    private static class Singleton {
        public static final Monitor INSTSNCE = new Monitor();
    }

    static Monitor getInstance() {
      return Singleton.INSTSNCE;
    }

    private Monitor(){

        if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") < 0) {



            monitor= new TxWindowMonitor();
        }else {
            AccessMonitor.getInstance();
            if ( ! AccessMonitor.LOAD_SO){
                System.err.println("warn /sofiles/libjni_monitor.so failed ");
                monitor = null;
            }else{
                monitor = new TxNativeMonitor();
            }

        }
    }

    private TxMonitor monitor;

    /**
     *
     * @param fileName
     * @return
     */
    public static boolean init(String fileName){
        if (null!=getInstance().monitor && null != fileName){
            return getInstance().monitor.initialize(fileName);
        }
        return false;
    }

    /**
     *
     * @param serviceName
     * @return
     */
    public static boolean initializeServiceName(String serviceName){

        if (null!=getInstance().monitor && null != serviceName){
            return getInstance().monitor.initializeServiceName(serviceName);
        }

        return false;
    }

    /**
     *
     * @param attrName
     * @param value
     * @return
     */
    public static boolean add(String attrName, int value){
        if (null!=getInstance().monitor && null != attrName){
            return getInstance().monitor.add(attrName,value);
        }
        return false;
    }

    /**
     *
     * @param attrName
     * @param value
     * @return
     */
    public static boolean set(String attrName, int value){
        if (null!=getInstance().monitor && null != attrName){
            return getInstance().monitor.set(attrName,value);
        }
        return false;
    }


}
