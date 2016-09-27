# 使用方法

```
//
compile ('com.ddb.mugen:access-monitor:1.0-SNAPSHOT')

```

调用方式:


```



```

## 调整内容

*  增加 window 平台兼容
*  修复wildfly下面  ClassLoader.getInputStream() 后获取不到 so 文件问题呢
*  别名AgentMonitor 成为 Monitor, 避免直接调用 native 方法
