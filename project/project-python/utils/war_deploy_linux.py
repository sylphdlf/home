# 此脚本需要在windows系统中双击执行
# 1、登录服务器
# 2、跳转到项目地址，执行git pull更新代码
# 3、执行mvn clean install命令
# 4、杀tomcat进程
# 5、备份当前war包
# 6、清空webapps文件夹
# 7、复制war包到webapps下,修改文件名
# 8、启动tomcat
import time
import paramiko as paramiko
import os
# 服务器
host = "122.112.236.194"
# 端口
port = 22
# 用户名
username = 'root'
# 密码
password = 'Dlf1989128'
ssh = None
# 项目地址
project_root_path = "/data/code/home/project"
# tomcat地址
project_tomcat_path = ["/data/tomcat/tomcat-9.0.12-8081"]
# project_tomcat_path = ["/data/tomcat/tomcat-9.0.12-8081", "/data/tomcat/tomcat-9.0.12-8082"]
# war包原始名称
source_war_name = "project-web-1.0-SNAPSHOT.war"
# 项目war包名称
target_war_name = "project-web.war"
# 原包路径
source_war_path = project_root_path + "/project-web/target/" + source_war_name


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
    cmd_pull = "cd " + project_root_path + ";git pull"
    print("git pull: " + cmd_pull)
    stdin, stdout, stderr = ssh.exec_command(cmd_pull)
    print_error_info(stderr.readlines())
    print_success_info(stdout.readlines())


# 3、mvn_clean_install
def mvn_package():
    global ssh
    cmd_clean_install = "cd " + project_root_path + ";source /etc/profile;mvn clean install"
    print("maven clean install...：" + cmd_clean_install)
    stdin, stdout, stderr = ssh.exec_command(cmd_clean_install)
    print_error_info(stderr.readlines())
    print_success_info(stdout.readlines())


# 4、杀tomcat进程
def kill_tomcat():
    global ssh
    cmd_jps = "source /etc/profile;jps"
    stdin, stdout, stderr = ssh.exec_command(cmd_jps)
    print_error_info(stderr.readlines())
    output_list = stdout.readlines()
    if len(output_list) > 0:
        for this_list in output_list:
            process_name = this_list.strip().split(None, 1)[1]
            if process_name == "Bootstrap":
                process_pid = this_list.strip().split(None, 1)[0]
                cmd_kill = "kill -9 " + process_pid
                print("杀tomcat进程：" + cmd_kill)
                ssh.exec_command(cmd_kill)


# 5、备份当前war包
def backup_war():
    global ssh
    # if not os.path.exists(project_tomcat_path + "/backup"):
    #     os.makedirs(os.path.exists(project_tomcat_path + "/backup"))
    format_time = time.strftime('%Y%m%d%H%M%S', time.localtime(time.time()))
    for tomcat_path in project_tomcat_path:
        cmd_backup = "mv " + tomcat_path + "/webapps/" + target_war_name \
                     + " " + tomcat_path + "/backup/" + target_war_name + "." + format_time
        print("备份war包：" + cmd_backup)
        stdin, stdout, stderr = ssh.exec_command(cmd_backup)
        print_error_info(stderr.readlines())


# 6、清空webapps文件夹
def clean_webapps():
    global ssh
    for tomcat_path in project_tomcat_path:
        cmd_clean = "cd " + tomcat_path + "/webapps; rm -rf *"
        print("清空webapps文件夹：" + cmd_clean)
        ssh.exec_command(cmd_clean)


# 7、复制war包到webapps下
def copy_war():
    global ssh
    for tomcat_path in project_tomcat_path:
        cmd_copy = "cp " + source_war_path + " " + tomcat_path + "/webapps"
        ssh.exec_command(cmd_copy)
        cmd_mv = "cd " + tomcat_path + "/webapps;mv " + source_war_name + " " + target_war_name
        print("复制war包并改名：" + cmd_mv)
        ssh.exec_command(cmd_mv)


# 8、启动tomcat
def start_tomcat():
    global ssh
    for tomcat_path in project_tomcat_path:
        cmd_start = "cd " + tomcat_path + "/bin;source /etc/profile;sh startup.sh"
        print("启动tomcat：" + cmd_start)
        stdin, stdout, stderr = ssh.exec_command(cmd_start)
        print_error_info(stderr.readlines())
        print_success_info(stdout.readlines())


if __name__ == '__main__':
    # 顺序不能乱
    login()
    git_pull()
    mvn_package()
    kill_tomcat()
    backup_war()
    clean_webapps()
    copy_war()
    start_tomcat()
    ssh.close()
    input("按任意键退出。。。")

