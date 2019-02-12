# My Shop API 档案
## contents
### 【GET】 http:localhost:8081/v1/contents/{category_id}
功能说明：获取指定父级类目下的所有内容  
请求方式：GET  
参数说明：  
category_id : 要查询的父级类目的 id 。 <必填>  
响应内容： 
``` 
{  
    status:200  
    data:[  
        {  
         id:...  
         title:....
         ...  
        }      
    ]
    message:"成功"  
}
```
失败响应
```
{
    status:500
    data:null
    message:"没有找到该内容分类"
}
```
## users
### 【POST】 http:localhost:808/v1/users/login
功能说明：通过提供的邮箱跟密码判断是否能登录  
请求方式：POST  
参数说明：  
email : 用户的邮箱。 <必填>  
password : 用户的密码。 <必填>  
响应内容：
```
{
    status:200
    data:{
        id:...
        ...
    }
    message:"登录成功"
}
```
失败响应
```
{
    status:500
    data:null
    message:"账号或密码错误"
}
```
