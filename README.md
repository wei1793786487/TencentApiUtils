#                                                        TencentApiUtils
### 开发环境 Win10、jdk1.8、Spring-boot、IntelliJ IDEA  


### 接口文档
  提供三种接口 

用于创建用户组的接口 ：Group（具体访问路径 ：http://你的主机地址:端口/Group）
- 新增用户组post请求- 
- 查看用户组get请求 
- 删除用户组delete请求

新增用户的所需参数 GroupName（人员库名称，[1,60]个字符，可修改，不可重复。） ，GroupId （人员库 ID，不可修改，不可重复。支持英文、数字、-%@#&_，长度限制64B。）

