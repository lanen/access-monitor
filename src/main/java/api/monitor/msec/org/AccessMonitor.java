package api.monitor.msec.org;

import java.io.*;
import java.util.Arrays;

/**
 * Created by even on 2016/9/29.
 */
class AccessMonitor {

    public static boolean LOAD_SO = false;

    static {
        load("/sofiles/libjni_monitor.so");
    }

    private AccessMonitor() {
    }

    private static AccessMonitor s_instance = new AccessMonitor();


    private static boolean isOsSupported(){
        return ! System.getProperty("os.name").toUpperCase().contains("WINDOWS");
    }

    /**
     *
     * @param soFile
     */
    private static void load(String soFile){

        if (LOAD_SO)return;
        if ( ! isOsSupported() )return;

        InputStream in = AccessMonitor.class.getClass().getResourceAsStream(soFile);

        try {
            if (null != in){
                System.out.println("copy so file from jar resource: "+ soFile);
                File tmpFile = File.createTempFile("libjni_monitor", ".so");
                OutputStream out = new FileOutputStream(tmpFile);
                byte[] buf = new byte[10240];
                while (true) {
                    int len = in.read(buf);
                    if (len <= 0) {
                        break;
                    }
                    out.write(buf, 0, len);
                }
                out.close();
                System.load(tmpFile.getAbsolutePath());
                LOAD_SO = true;
                tmpFile.delete();

            }else {
                loadSystemDefaulMonitorSo();
                LOAD_SO = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            loadSystemDefaulMonitorSo();
            LOAD_SO = true;
        }finally {
            if (null != in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private static boolean LOAD_DEFAULT = false;

    public static final String DEFAULT_SO_FILE = "/data/wildfly/native/libjni_monitor.so";
    /**
     * 加载系统默认
     */
    private static void loadSystemDefaulMonitorSo(){

        if (LOAD_DEFAULT)return;

        try{
            File testExists = new File(DEFAULT_SO_FILE);

            if (!testExists.exists()){
                System.out.println("file :" + DEFAULT_SO_FILE + "not exits");
                return;
            }

            System.out.println("system load jni file: "+DEFAULT_SO_FILE);
            System.load(DEFAULT_SO_FILE);
            LOAD_DEFAULT = true;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static AccessMonitor getInstance() {
        return s_instance;
    }

    //Native methods:
    public native boolean init(String fileName);

    public native boolean add(String serviceName, String attrName, int value);

    public native boolean set(String serviceName, String attrName, int value);

    public static boolean initialize(String filename) {
        return s_instance.init(filename);
    }

    protected String serviceName;

    public static void initServiceName(String serviceName) {
        s_instance.serviceName = serviceName;
    }

    //Simplified methods:
    public static boolean add(String attrName, int value) {
        if (s_instance.serviceName == null || s_instance.serviceName.isEmpty())
            return false;

        return s_instance.add(s_instance.serviceName, attrName, value);
    }

    public static boolean add(String attrName) {
        return add(attrName, 1);
    }

    public static boolean set(String attrName, int value) {
        if (s_instance.serviceName == null || s_instance.serviceName.isEmpty())
            return false;

        return s_instance.set(s_instance.serviceName, attrName, value);
    }

    public static void main(String[] args) {
        //Initialize
        AccessMonitor.initialize("/msec/agent/monitor/monitor.mmap");
        AccessMonitor.initServiceName("TestModuleName");

        AccessMonitor.add("test", 1);

    }
}
