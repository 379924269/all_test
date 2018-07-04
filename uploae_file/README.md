## 文件上传

可以参考的地址：https://www.cnblogs.com/ghq120/p/8312944.html

- 文件上传servlet、struts2和springmvc都不一样（同样文件上传需要导入的包是：commons-fileupload-1.2.1.jar、
  commons-io-1.4.jar）

- servlet直接是文件流
- struts2直接封装file对象，直接用
- springmvc 直接用MultipartFile
