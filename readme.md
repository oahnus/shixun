# 实训管理系统
## 登陆
### TOKEN
前台登陆时传递用户名和密码,后台在校验成功后,将随机生成TOKEN(格式：UUID+"-"+AUTH_TYPE)
登陆状态使用redis保存，将TOKEN和USER_ID作为键值对保存在redis中

user_auth table
id,user_id,password,user_type

student table

company table

teacher table