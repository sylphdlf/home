
#新建根组织机构
insert into u_organization(code, parent_code, name, level, remarks, create_user_id, is_deleted) values
  ("1", "0", "root", 0, "root", 0, 0);
#新建管理员角色
insert into u_role(org_id, code, name, remarks, create_user_id, is_deleted) values (1, "1", "admin", "admin", 0, 0);
#创建菜单
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("用户管理","001","0",1,"user",1,0);
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("用户列表","001-1","001",1,"user",2,0);
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("组织管理","001-2","001",1,"org",2,0);
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("角色管理","001-3","001",1,"role",2,0);
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("权限管理","001-4","001",1,"fun",2,0);

insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("物流管理","002","0",1,"tms",1,0);
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("联系人管理","002-1","002",1,"contacts",2,0);
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("车辆管理","002-2","002",1,"vehicle",2,0);
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("托运单管理","002-3","002",1,"consign",2,0);
insert into u_function (name, code, parent_code, type, path, level, is_deleted) values ("上门提货","002-4","002",1,"pickup",2,0);
