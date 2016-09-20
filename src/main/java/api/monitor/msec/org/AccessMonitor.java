package api.monitor.msec.org;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/5/19.
 */
public class AccessMonitor {
    static {
        if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") < 0) {
            try {
                InputStream in = AccessMonitor.class.getClass().getResourceAsStream("/sofiles/libjni_monitor.so");
                File f = File.createTempFile("libjni_monitor", ".so");
                OutputStream out = new FileOutputStream(f);
                byte[] buf = new byte[10240];
                while (true) {
                    int len = in.read(buf);
                    if (len <= 0) {
                        break;
                    }
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                System.load(f.getAbsolutePath());
                f.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private AccessMonitor() {
    }

    private static AccessMonitor s_instance = new AccessMonitor();

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
