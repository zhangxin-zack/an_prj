# 登陆注册流程 #

## 1.获取验证码：

(IP:PORT/EDU/account/get_code)

参数：手机号

```
requestParam

key: phone  value:1581234578
```

返回：验证码

```
{
    "result_message": "success",
    "result_code": 0
}
```



## 2.注册：

(IP:PORT/EDU/account/register)

参数：手机号，密码，验证码

```
{
	"phone":"1582742700",
	"password":"abc123",
	"validateCode":"870106"
}
```

返回：token，用户名，用户图像，用户身份，用户昵称，手机号码，性别，生日

```
{
    "result_message": "success",
    "result_data": {
        "menuList": {},
        "account": {
            "id": 4,
            "username": null,
            "password": "abc123",
            "nickname": null,
            "email": null,
            "phone": "1582742700",
            "registerDate": null,
            "address": null,
            "imgUrl": null,
            "totalScore": 0,
            "score": 0,
            "scoreId": 0,
            "catetgoryId": 0,
            "categoryName": null,
            "validateCode": "870106",
            "sex": null
        },
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjQsIkxvZ2luVGltZSI6MTU2NTUxNjMyNTMwMn0.B0ml1po-vsFVQHi9wM0_YehTTl16d7TQEjYjYzbuGyA"
    },
    "result_code": 0
}
```



## 3.登陆：

(IP:PORT/EDU/account/login)

参数：手机号，密码

```
{
	"phone":"1582742700",
	"password":"abc123"
}
```



返回：token，用户名，用户图像，用户身份，用户昵称，手机号码，性别，生日

```
{
    "result_message": "success",
    "result_data": {
        "id": 3,
        "username": null,
        "password": "abc123",
        "nickname": null,
        "email": null,
        "phone": "1582742700",
        "registerDate": "2019-08-10T16:00:00.000+0000",
        "address": null,
        "imgUrl": null,
        "totalScore": 0,
        "score": 0,
        "scoreId": 0,
        "catetgoryId": 0,
        "categoryName": null,
        "validateCode": null,
        "sex": null
    },
    "result_code": 0
}
```



## 4.修改密码

(IP:PORT/EDU/account/update_password)

参数：原密码，新密码

```
{
	"id":3,
	"password":"46456",
	"oldPassword":"abc123"
}
```

返回：成功或失败

```
{
    "result_message": "success",
    "result_code": 0
}
```

## 5.重置密码：

(IP:PORT/EDU/account/update_reset_pwd)

参数：手机号，验证码，新密码

```
{
	"phone":"15827427000",
	"password":"dsdsd13",
	"validateCode":"224530"
}
```

返回：成功或失败

```
{
    "result_message": "success",
    "result_code": 0
}
```



# 我的栏目 #

## 6.修改用户资料：

(IP:PORT/EDU/account/update)

参数：（昵称，性别或生日）这个定义字段，传什么就修改什么

```
{
	"id":3,
	"nickname":"zack",
	"email": "dasdas@qq.com",
	"phone":"15827427000",
	"address":"武汉市光谷",
	"sex":1,
	"password":"dsdsd13",
	"imgUrl":"dadasd/dasda.jpg"
}
```

返回：成功或失败

```
{
    "result_message": "success",
    "result_code": 0
}
```

## 7.获取宝贝信息：

(IP:PORT/EDU/student/list )

参数: 

```
{
	"searchs": {
		"accountId": "3"
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}

```

返回：宝贝数量，宝贝信息（头像，姓名，昵称，性别，生日，学校，班级，手表号，身高，体重，手表二维码，亲属关系）

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "accountId": "3"
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 1,
        "rows": [
            {
                "id": 11,
                "studentName": "student2",
                "nickname": null,
                "sex": 0,
                "cardNo": "456",
                "registerNo": "100002",
                "height": 160.0,
                "weight": 45.0,
                "ringNo": "ABC1002",
                "schoolId": null,
                "schoolName": null,
                "classId": 6,
                "className": "6班",
                "birthday": "2019-01-03",
                "bind": 1,
                "status": null,
                "relationName": null,
                "deleteFlag": null
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 8.添加宝贝：

(IP:PORT/EDU/account/add_baby)

参数：宝贝姓名，昵称，性别，身高，体重，关系

```
{
	"studentName": "anyiming",
	"nickname": "anlaoban",
	"sex": 1,
	"cardNo": "10021",
	"registerNo": "20002",
	"height": "100",
	"weight": "100",
	"ringNo": "ABC1003",
	"schoolId": "1",
	"classId": "7",
	"birthday": "2019-09-01",
	"accountId": 3,
	"relationName": "爸爸"
}
```

返回：成功或失败

```
{
    "result_message": "success",
    "result_code": 0
}
```

## 9.添加学校

(IP:PORT/EDU/account/update_baby)

参数：学校，班级，孩子id

```
{
	"id": 23,
	"schoolId": "1",
	"classId": "7"
}
```

返回：成功或失败

```
{
    "result_message": "success",
    "result_code": 0
}
```

## 10.修改学校

(IP:PORT/EDU/account/update_baby)

参数：学校，班级，孩子id

```
{
	"id": 23,
	"schoolId": "2",
	"classId": "8"
}
```

返回：成功或失败

```
{
    "result_message": "success",
    "result_code": 0
}
```

## 11.修改宝贝资料

(IP:PORT/EDU/account/update_baby)

参数：宝贝Id，（姓名，昵称，性别，生日，身高，体重，亲属关系）这个定义字段，传什么就修改什么

```
{
	"id":23,
	"studentName": "anyiming1",
	"nickname": "anlaoban1",
	"sex": 1,
	"height": "150",
	"weight": "130",
	"birthday": "2019-01-30"
}
```

返回：成功或失败

```
{
    "result_message": "success",
    "result_code": 0
}
```

## 12.获取宝贝课程表

(IP:PORT/EDU/classes/list_timetable)

参数：

```
{
	"searchs": {
		"studentId": 23
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：课程表

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "studentId": 23,
            "classId": 8
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 1,
        "rows": [
            {
                "id": 1,
                "classId": 8,
                "timetable": "[[\"劳动\", \"英语\", \"美术\", \"语文\", \"数学\", \"语文\", null, \"数学\"], [\"体育\", \"体育\", \"数学\", \"语文\", \"劳动\", \"劳动\", \"美术\", \"语文\"], [\"英语\", \"英语\", \"体育\", \"劳动\", \"英语\", \"英语\", \"数学\", \"语文\"], [\"数学\", \"美术\", \"美术\", \"劳动\", \"美术\", \"美术\", \"语文\", \"体育\"], [\"美术\", \"体育\", \"语文\", null, \"语文\", \"语文\", \"语文\", \"英语\"], [\"劳动\", \"语文\", \"美术\", \"语文\", \"劳动\", \"英语\", \"英语\", \"体育\"], [\"劳动\", \"美术\", \"英语\", \"语文\", \"数学\", \"语文\", \"英语\", \"数学\"]]",
                "startDate": "2019-08-04T11:59:37.000+0000"
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 13.绑定手表(TODO)

参数：token，宝贝id，手表二维码信息或IMEI号

返回：手表号，手表二维码

# 首页相关 #

## 14.当前宝贝信息(TODO)

参数：token，宝贝id

返回：姓名，图像，手表电量，心跳，温度，定位，今日课表（如果没法返回就新增一个获取当日课表的接

口）

## 15.最新通知：（最新两条通知）

(IP:PORT/EDU/notice/list)

参数：token，宝贝id

```
{
	"searchs": {
		"studentId": 23,
		"formTo": 0
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：通知内容

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "studentId": 23,
            "formTo": 0,
            "classId": 8
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 1,
        "rows": [
            {
                "id": 1,
                "noticTitle": "DASDA",
                "noticeContent": "DSDASDAASDASD",
                "noticeDate": "2019-08-11T12:30:52.000+0000",
                "classId": 8,
                "fromTo": 0
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 16.每日推荐

(IP:PORT/EDU/notice/list_daily_recommend )

参数：无（不管登陆是否都可以查看）

```
{
	"searchs": {
		"type": 0      //0:每日推荐
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：图片路径，跳转链接

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "type": 0
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 2,
        "rows": [
            {
                "id": 1,
                "content": "每日推荐1",
                "imgPath": "www.111.com",
                "imgUrl": "a/b/1.png",
                "publishDate": "2019-08-10T16:00:00.000+0000",
                "type": 0
            },
            {
                "id": 2,
                "content": "每日推荐2",
                "imgPath": "www.222.com",
                "imgUrl": "a/b/2.png",
                "publishDate": "2019-08-10T16:00:00.000+0000",
                "type": 0
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```



## 17.亲子知识

(IP:PORT/EDU/notice/list_child_knowledge)

参数：无（不管登陆是否都可以查看）

```
{
	"searchs": {
		"type": 0      //1:亲子知识
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：图片路径，跳转链接

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "type": 0
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 2,
        "rows": [
            {
                "id": 1,
                "content": "每日推荐1",
                "imgPath": "www.111.com",
                "imgUrl": "a/b/1.png",
                "publishDate": "2019-08-10T16:00:00.000+0000",
                "type": 0
            },
            {
                "id": 2,
                "content": "每日推荐2",
                "imgPath": "www.222.com",
                "imgUrl": "a/b/2.png",
                "publishDate": "2019-08-10T16:00:00.000+0000",
                "type": 0
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```



# 首页跳转 #

## 18.班级通知：

(IP:PORT/EDU/notice/list)

参数：token，宝贝id（需要分页）

```
{
	"searchs": {
		"studentId": 23,
		"formTo": 0
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：通知内容

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "studentId": 23,
            "formTo": 0,
            "classId": 8
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 1,
        "rows": [
            {
                "id": 1,
                "noticTitle": "DASDA",
                "noticeContent": "DSDASDAASDASD",
                "noticeDate": "2019-08-11T12:30:52.000+0000",
                "classId": 8,
                "fromTo": 0
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 19.手表通知：

(IP:PORT/EDU/report/alarm_list)

参数：token，宝贝id（需要分页）

```
{
	"searchs": {
		"studentId": 11
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：通知内容

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "studentId": 11
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 2,
        "rows": [
            {
                "id": 1,
                "alarmTime": "2019-08-13T17:29:15.000+0000",
                "alarmType": 1,
                "alarmData": "心跳报警",
                "studentId": 11,
                "studentName": "student2",
                "classId": null,
                "className": "6班",
                "longitude": 31.11,
                "latitude": 32.3,
                "alarmBeginTime": null,
                "alarmEndTime": null
            },
            {
                "id": 2,
                "alarmTime": "2019-08-22T17:29:15.000+0000",
                "alarmType": 2,
                "alarmData": "位置报警",
                "studentId": 11,
                "studentName": "student2",
                "classId": null,
                "className": "6班",
                "longitude": 31.11,
                "latitude": 32.3,
                "alarmBeginTime": null,
                "alarmEndTime": null
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```



# 班级栏目 #

## 20.班级内容（轮播图）

(IP:PORT/EDU/classes/list_class_content )

参数：无（不管登陆是否都可以查看）

```
{
	"searchs": {
		"classId": 8
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：图片路径，跳转链接

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "classId": 8
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 2,
        "rows": [
            {
                "id": 1,
                "classId": 8,
                "content": "asdas",
                "imgPath": "www.xxx.com",
                "imgUrl": "111.png"
            },
            {
                "id": 2,
                "classId": 8,
                "content": "vxvxcv",
                "imgPath": "www.xxx.com",
                "imgUrl": "222.png"
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 21.获取老师管理班级

(IP:PORT/EDU/classes/)

参数：token

```

```

返回：班级数量，班级名，班级id

```

```

## 22.获取班级状况(TODO)

参数：token，班级id

返回：班级人数，绑定手表人数，未绑定人数，到校人数，未到校人数，心率正常人数，今日课表（如果没法

返回就新增一个获取当日课表的接口）



## 23.最新通知：（最新两条通知，学校发给老师的通知）

(IP:PORT/EDU/notice/list)

参数：token，班级id

```

{
	"searchs": {
		"studentId": 23,
		"formTo": 1     //1:学校发给老师的通知
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：通知内容

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "studentId": 23,
            "formTo": 1,
            "classId": 8
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 1,
        "rows": [
            {
                "id": 2,
                "noticTitle": "春游",
                "noticeContent": "带领学生春游",
                "noticeDate": "2019-08-11T13:04:43.000+0000",
                "classId": 8,
                "fromTo": 1
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 24.最新审批（最新两条审批）

(IP:PORT/EDU/student/list )

参数：token，班级id

```
{
	"searchs": {
		"status": 0
	},
	"limit": 2,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}

```

返回：审批内容

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "status": 0
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 2,
        "rows": [
            {
                "id": 23,
                "studentName": "anyiming1",
                "nickname": null,
                "sex": 1,
                "cardNo": "10021",
                "registerNo": "20002",
                "height": 150.0,
                "weight": 130.0,
                "ringNo": "ABC1003",
                "schoolId": null,
                "schoolName": null,
                "classId": 8,
                "className": "3年1班2",
                "birthday": "2019-01-30",
                "bind": null,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            },
            {
                "id": 11,
                "studentName": "student2",
                "nickname": null,
                "sex": 0,
                "cardNo": "456",
                "registerNo": "100002",
                "height": 160.0,
                "weight": 45.0,
                "ringNo": "ABC1002",
                "schoolId": null,
                "schoolName": null,
                "classId": 6,
                "className": "6班",
                "birthday": "2019-01-03",
                "bind": 1,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            }
        ],
        "limit": 2,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```



# 班级页跳转 #

## 25.班级成员（列表）

(IP:PORT/EDU/classes/list_class_content )

参数：token，班级id

```
{
	"searchs": {
		"classId": 8
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：学生信息列表

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "classId": 8
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 4,
        "rows": [
            {
                "id": 23,
                "studentName": "anyiming1",
                "nickname": null,
                "sex": 1,
                "cardNo": "10021",
                "registerNo": "20002",
                "height": 150.0,
                "weight": 130.0,
                "ringNo": "ABC1003",
                "schoolId": null,
                "schoolName": null,
                "classId": 8,
                "className": "3年1班2",
                "birthday": "2019-01-30",
                "bind": null,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            },
            {
                "id": 11,
                "studentName": "student2",
                "nickname": null,
                "sex": 0,
                "cardNo": "456",
                "registerNo": "100002",
                "height": 160.0,
                "weight": 45.0,
                "ringNo": "ABC1002",
                "schoolId": null,
                "schoolName": null,
                "classId": 8,
                "className": "3年1班2",
                "birthday": "2019-01-03",
                "bind": 1,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            },
            {
                "id": 11,
                "studentName": "student2",
                "nickname": null,
                "sex": 0,
                "cardNo": "456",
                "registerNo": "100002",
                "height": 160.0,
                "weight": 45.0,
                "ringNo": "ABC1002",
                "schoolId": null,
                "schoolName": null,
                "classId": 8,
                "className": "3年1班2",
                "birthday": "2019-01-03",
                "bind": 1,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            },
            {
                "id": 11,
                "studentName": "student2",
                "nickname": null,
                "sex": 0,
                "cardNo": "456",
                "registerNo": "100002",
                "height": 160.0,
                "weight": 45.0,
                "ringNo": "ABC1002",
                "schoolId": null,
                "schoolName": null,
                "classId": 8,
                "className": "3年1班2",
                "birthday": "2019-01-03",
                "bind": 1,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 26.学校通知列表：

(IP:PORT/EDU/notice/list)

参数：token，班级id（需要分页）

```
{
	"searchs": {
		"classId": 1,
		"fromTo": 1
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}
```

返回：通知内容

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "classId": 1,
            "fromTo": 1
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 1,
        "rows": [
            {
                "id": 3,
                "noticTitle": "放假通知",
                "noticeContent": "由于台风, 放假一天",
                "noticeDate": "2019-08-11T13:23:25.000+0000",
                "classId": 1,
                "classIds": null,
                "fromTo": 0
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 27.审批列表：

(IP:PORT/EDU/student/list )

参数：token，班级id（需要分页）

```
{
	"searchs": {
		"status": 0
	},
	"limit": 4,
	"paging":1,
	"sort": [{"field":"id", "type":"desc"}]
}

```

返回：审批内容

```
{
    "result_message": "success",
    "result_data": {
        "token": null,
        "desc": null,
        "searchs": {
            "status": 0
        },
        "sort": [
            {
                "field": "id",
                "type": "desc"
            }
        ],
        "total": 4,
        "rows": [
            {
                "id": 23,
                "studentName": "anyiming1",
                "nickname": null,
                "sex": 1,
                "cardNo": "10021",
                "registerNo": "20002",
                "height": 150.0,
                "weight": 130.0,
                "ringNo": "ABC1003",
                "schoolId": null,
                "schoolName": null,
                "classId": 8,
                "className": "3年1班2",
                "birthday": "2019-01-30",
                "bind": null,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            },
            {
                "id": 11,
                "studentName": "student2",
                "nickname": null,
                "sex": 0,
                "cardNo": "456",
                "registerNo": "100002",
                "height": 160.0,
                "weight": 45.0,
                "ringNo": "ABC1002",
                "schoolId": null,
                "schoolName": null,
                "classId": 6,
                "className": "6班",
                "birthday": "2019-01-03",
                "bind": 1,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            },
            {
                "id": 11,
                "studentName": "student2",
                "nickname": null,
                "sex": 0,
                "cardNo": "456",
                "registerNo": "100002",
                "height": 160.0,
                "weight": 45.0,
                "ringNo": "ABC1002",
                "schoolId": null,
                "schoolName": null,
                "classId": 6,
                "className": "6班",
                "birthday": "2019-01-03",
                "bind": 1,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            },
            {
                "id": 11,
                "studentName": "student2",
                "nickname": null,
                "sex": 0,
                "cardNo": "456",
                "registerNo": "100002",
                "height": 160.0,
                "weight": 45.0,
                "ringNo": "ABC1002",
                "schoolId": null,
                "schoolName": null,
                "classId": 6,
                "className": "6班",
                "birthday": "2019-01-03",
                "bind": 1,
                "status": 0,
                "relationName": null,
                "deleteFlag": null,
                "accountId": null
            }
        ],
        "limit": 4,
        "paging": 1,
        "pageSize": 4,
        "startIndex": 0
    },
    "result_code": 0
}
```

## 28.发布通知

(IP:PORT/EDU/notice/add)

参数：token，班级id，标题内容

```
{
	"noticTitle":"放假通知",
	"noticeContent":"由于台风, 放假一天", 
    "fromTo":"0",                      //0:老师发给学生 1:学校发给老师
    "classIds":[1,2,3]
}
```

返回：成功是否

```
{
    "result_message": "success",
    "result_code": 0
}
```

