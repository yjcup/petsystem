## 适合二开的Ruoyi
需要修改的地方：
1. 文件上传路径
2. 日志文件存放路径
3. yml文件中的logouturl等等
4. 二开的时候只需要注意加上前缀就好了

修改过的路径：
1. SystemIndexContronller
2. SysLoginController

shiro框架通过拦截功能来实现对用户访问权限的控制和拦截，shiro里面支持Ant风格的通配符。
？：匹配任意的一个字符，例如："/admin?"可以匹配“/admin1”,"/admin2"。
*：匹配一个或者多个任意的字符。
**：匹配零个或者多个目录。