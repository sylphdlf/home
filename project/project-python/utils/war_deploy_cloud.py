# 此脚本需要在windows系统中双击执行
# 1、登录服务器
# 2、跳转到项目地址，执行git pull更新代码
# 3、执行mvn clean install命令
# 4、杀tomcat进程
# 5、备份当前war包
# 6、清空webapps文件夹
# 7、复制war包到webapps下,修改文件名
# 8、启动tomcat
import hashlib
import time
import paramiko as paramiko
import os
# 服务器
host = "118.25.197.159"
# 端口
port = 22
# 用户名
username = 'root'
# 密码
password = 'Dlf1989128'
ssh = None
# 项目地址
root_path = "/data/project/office-cloud/project"
git_pull_path = ["/data/project/office-cloud/project/mat-registry",
                 "/data/project/office-cloud/project/mat-router",
                 "/data/project/office-cloud/project/mat-web-api",
                 "/data/project/office-cloud/project"]
# 打包地址
package_path = set([])
    # ["/data/project/office-cloud/project",
    #             "/data/project/office-cloud/project/mat-registry",
    #             "/data/project/office-cloud/project/mat-router",
    #             "/data/project/office-cloud/project/mat-web-api"]
# 目标路径
relate_path = {root_path + "/mat-registry/target/mat-registry-1.0-SNAPSHOT.jar": "mat-registry-1.0-SNAPSHOT.jar",
               root_path + "/mat-router/target/mat-router-1.0-SNAPSHOT.jar": "mat-router-1.0-SNAPSHOT.jar",
               root_path + "/mat-service/target/mat-service-1.0-SNAPSHOT.jar": "mat-service-1.0-SNAPSHOT.jar",
               root_path + "/mat-web-api/target/mat-web-api-1.0-SNAPSHOT.jar": "mat-web-api-1.0-SNAPSHOT.jar"}

prepare_start = {}
# jar包复制路径
copy_path = "/data/code"


# 打印错误信息
def print_error_info(error_input):
    if len(error_input) > 0:
        print(error_input)


# 打印输出信息
def print_success_info(success_input):
    if len(success_input) > 0:
        for this_list in success_input:
            print(this_list.strip())


# 1、登录
def login():
    global ssh
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(host, port=port, username=username, password=password, timeout=5)


# 2、git pull
def git_pull():
    global ssh
    for this_path in git_pull_path:
        cmd_pull = "cd " + this_path + ";git pull"
        print("git pull: " + cmd_pull)
        stdin, stdout, stderr = ssh.exec_command(cmd_pull)
        output_list = stdout.readlines()
        if len(output_list) > 0:
            for this_line in output_list:
                print(this_line)
                if "Already up-to-date." not in this_line:
                    package_path.add(this_path)
                    break


# 3、mvn_clean_install
def mvn_package():
    global ssh
    for this_path in package_path:
        cmd_clean_install = "cd " + this_path + ";source /etc/profile;mvn clean install"
        print("maven clean install...：" + cmd_clean_install)
        stdin, stdout, stderr = ssh.exec_command(cmd_clean_install)
        print_success_info(stdout.readlines())
        print_error_info(stderr.readlines())


# 4、对比md5和复制
def md5_check():
    global ssh
    for this_path in relate_path:
        cmd_line = "md5sum -b " + this_path + " /data/code/" + relate_path[this_path]
        stdin, stdout, stderr = ssh.exec_command(cmd_line)
        output_list = stdout.readlines()
        if len(output_list) > 0:
            target_md5 = ""
            for this_list in output_list:
                split_md5 = this_list.strip().split(None, 1)[0]
                if len(target_md5) == 0:
                    target_md5 = split_md5
                else:
                    if split_md5 != target_md5:
                        copy_jar(this_path, "/data/code/" + relate_path[this_path])
                        prepare_start[relate_path[this_path]] = "/data/code/" + relate_path[this_path]


# 5、杀进程
def kill_process():
    global ssh
    if len(prepare_start) != 0:
        for this_jar in prepare_start:
            cmd_jps = "source /etc/profile; ps -ef|grep " + this_jar
            stdin, stdout, stderr = ssh.exec_command(cmd_jps)
            print_error_info(stderr.readlines())
            output_list = stdout.readlines()
            if len(output_list) > 0:
                for this_list in output_list:
                    process_name = this_list.strip().split(None, 1)[1]
                    if "java -jar" in process_name:
                        print(process_name)
                        process_pid = process_name.strip().split(None, 1)[0]
                        cmd_kill = "kill -9 " + process_pid
                        print("杀进程：" + cmd_kill)
                        ssh.exec_command(cmd_kill)


# 8、启动
def start_jar():
    global ssh
    for jar in prepare_start:
        cmd_start = "source /etc/profile;java -jar " + prepare_start[jar] + " > /dev/null &"
        print("启动：" + cmd_start)
        stdin, stdout, stderr = ssh.exec_command(cmd_start)
        print_error_info(stderr.readlines())
        print_success_info(stdout.readlines())


# 7、复制
def copy_jar(source_path, target_path):
    global ssh
    cmd_copy = "cp -f " + source_path + " " + target_path
    stdin, stdout, stderr = ssh.exec_command(cmd_copy)
    print_error_info(stderr.readlines())
    print_success_info(stdout.readlines())


def get_md5(file_path):
    f = open(file_path,'rb')
    md5_obj = hashlib.md5()
    while True:
        d = f.read(8096)
        if not d:
          break
        md5_obj.update(d)
    hash_code = md5_obj.hexdigest()
    f.close()
    md5 = str(hash_code).lower()
    return md5


if __name__ == '__main__':
    # 顺序不能乱
    login()
    git_pull()
    mvn_package()
    md5_check()
    kill_process()
    start_jar()
    print(prepare_start)
    ssh.close()
    input("按任意键退出。。。")

