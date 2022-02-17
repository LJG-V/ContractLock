# ContractLock
用 JAVA 实现一个基于 HTTP 协议的简易文件服务器 Server 端和 Client 端

软件架构说明

- 项目主体为Maven构建，共有Server端、Client端两个模块。

- Server端采用Springboot搭建后台服务，提供后台服务接口。

- Client端采用Maven构建，分别对Server端的上传（upload）、下载（down）、文件元（fileinfo）进行单元测试。利用HttpURLConnection模拟浏览器表单式上传文件，以此减少对第三方库的依赖

使用说明

上传接口访问：http://localhost:8088/file/upload

下载接口访问：http://localhost:8088/file/download

获取元数据接口访问：http://localhost:8088/file/getFileInfo
