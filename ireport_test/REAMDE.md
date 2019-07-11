## 测试ireport
- 下载地址：https://community.jaspersoft.com/project/ireport-designer/releases
- 问题：安装启动不了，注意配置jdk位置

### ireport背景设置
- 主要是要勾选Opaque,然后设置颜色就可以了。

### 测试路径： 启动springboot，访问下面地址
http://127.0.0.1/test/export.html

### 基本操作：
字体设置：
pdfFontName: STSong-Light
pdfEncoding: UniGB-UCS2-H

计算：可以参考地址https://blog.csdn.net/xuzheng_java/article/details/23045849
其实就是设置一个变量， 在变量中添加表达式  这个变量就是你要的结果，
表达式有点不好写。。。。

###图表操作，可以参考ireport文档， 在doc目录下

### 常用名词
indent：缩进
spacing： 间距
stretch： 拉伸


### 遇到的问题
