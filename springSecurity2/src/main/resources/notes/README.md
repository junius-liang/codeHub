# 基于数据库的认证
```sql
# 创建用户表
create table user(
    user_id int not null auto_increment,
    password varchar(255),
    sex varchar(2),
    enabled int,
    account_not_expired int,
    credentials_no_expired int,
    account_not_locked int,
    primary key(user_id)
);

# 创建角色表
create table role(
                     id int not null auto_increment,
                     role_name varchar(32),
                     remark varchar(32),
                     primary key(id)
);

# 创建用户所属角色表
create table sys_role_user(
                              uid int,
                              rid int
);


# 查询权限
select distinct sm.code from sys_role_user sru
                                 inner join sys_role_menu srm on sru.rid=srm.rid inner join menu sm on srm.mid=sm.id where sru.uid=1

```
