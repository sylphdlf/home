# encoding=utf-8
import paramiko
import os
import re
import sys

local = 'D:\\ftpLocal\\'  # 本地文件或目录，与远程一致，当前为windows目录格式，window目录中间需要使用双斜线
port = 58422  # 端口
username = 'deployer'  # 用户名
password = 'deployer.123'  # 密码
# 服务器地址
host_1 = "139.196.28.23"
host_2 = "139.196.40.91"
host_list = [host_1, host_2]
property_file_dict_local = local + "xiaoxiangfront.properties"


def get_remote_tomcat_path(current_host, war_name, input_env):
    tomcat = '/usr/local/tomcat-' + input_env + "/"  # 远程文件或目录，与本地一致，当前为linux目录格式
    # tomcat_uat = '/usr/local/src/wars/'  # 远程文件或目录，与本地一致，当前为linux目录格式
    tomcat_admin = '/usr/local/admin-tomcat-' + input_env + "/"
    # tomcat_uat_admin = '/usr/local/src/wars/'
    # 前后台tomcat路径
    ftp_dict_1 = {'front.war': tomcat, 'xiaoxiangweb.war': tomcat_admin}
    ftp_dict_2 = {'front.war': tomcat}
    # 服务器对应的tomcat地址
    host = {host_1: ftp_dict_1, host_2: ftp_dict_2}  # 主机
    return host[current_host].get(war_name, 0)


def get_remote_property_path(path, input_env):
    tomcat = '/usr/local/tomcat-' + input_env + "/"  # 远程文件或目录，与本地一致，当前为linux目录格式
    tomcat_admin = '/usr/local/admin-tomcat-' + input_env + "/"
    # tomcat路径对应的配置文件地址
    property_file_dict_remote = {tomcat: "/home/deployer/" + input_env + "_properties/xiaoxiangfront.properties",
                                 tomcat_admin: "/home/deployer/" + input_env + "_admin_conf/application.properties"}

    return property_file_dict_remote.get(path, 0)


def deploy(current_host, input_env):
    # 判断本地参数是目录还是文件
    if os.path.isdir(local):
        # 遍历本地目录
        for war_name in os.listdir(local):
            # 杀进程和删文件
            kill_and_remove_war(current_host, war_name, input_env)
            # 文件上传
            file_upload(current_host, war_name, input_env)
            # 修改配置文件
            if war_name == "front.war":
                modify_property_file(current_host, war_name, input_env)
            # 重启tomcat
            restart_tomcat(current_host, war_name, input_env)


# 杀进程&删war包
def kill_and_remove_war(current_host, war_name, input_env):
    path = get_remote_tomcat_path(current_host, war_name, input_env)
    if path == 0:
        return
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(current_host, port=port, username=username, password=password, timeout=5)
    cmd = "ps -ef | grep " + path
    stdin, stdout, stderr = ssh.exec_command(cmd)
    output_list = stdout.readlines()
    if len(output_list) > 1:
        for temp_list in output_list:
            pid = int(temp_list.split(None, 2)[1])
            cmd2 = "kill -9 " + str(pid)
            ssh.exec_command(cmd2)
            print("kill进程：" + str(pid) + " " + path)
    # 删除webapps下的文件
    cmd3 = "rm -rf " + path + "webapps/*"
    ssh.exec_command(cmd3)
    print("war包已删除：" + current_host + path + "webapps")
    ssh.close()


def restart_tomcat(current_host, war_name, input_env):
    path = get_remote_tomcat_path(current_host, war_name, input_env)
    if path == 0:
        return
    print("重启 " + current_host + " " + war_name)
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(current_host, port=port, username=username, password=password, timeout=60)
    cmd = "source /etc/profile;sh " + path + "bin/startup.sh"
    ssh.exec_command(cmd)
    print("tomcat重启成功：" + current_host + path)
    ssh.close()


def file_upload(current_host, war_name, env):
    path = get_remote_tomcat_path(current_host, war_name, env)
    if path == 0:
        return
    # 上传目录中的文件
    sf = paramiko.Transport((current_host, port))
    sf.connect(username=username, password=password)
    try:
        print("开始上传 " + war_name + " 到 " + path + "webapps")
        sftp = paramiko.SFTPClient.from_transport(sf)
        sftp.put(os.path.join(local + war_name), os.path.join(path + "webapps/" + war_name))
        print("上传成功：" + current_host + ":" + war_name)
    except Exception as e:
        print('exception:', e)
    sf.close()


def modify_property_file(current_host, war_name, input_env):
    # 下载配置文件
    download_file(current_host, war_name, input_env)
    # 修改配置文件
    modify_file()
    # 上传配置文件
    upload_file(current_host, war_name, input_env)


# 文件下载
def download_file(current_host, war_name, env):
    path = get_remote_tomcat_path(current_host, war_name, env)
    if path == 0:
        return
    remote_property_path = get_remote_property_path(path, env)
    if remote_property_path == 0:
        return
    # 上传目录中的文件
    sf = paramiko.Transport((current_host, port))
    sf.connect(username=username, password=password)
    try:
        sftp = paramiko.SFTPClient.from_transport(sf)
        sftp.get(get_remote_property_path(path, env), property_file_dict_local)
        print("配置文件下载成功：" + get_remote_property_path(path, env))
    except Exception as ex:
        print('exception:', ex)
    sf.close()


# 修改文件
def modify_file():
    f_open = open(property_file_dict_local, "r", encoding="utf-8", errors="ignore")
    w_str = ""
    for line in f_open:
        if re.search("static.version=", line):
            # 获取js版本
            version = line.split("=")[1]
            # 去掉中间的句号 例如 1.2.1
            split_version = version.replace(".", "")
            new_version = int(split_version) + 1
            line = re.sub(version, str(new_version) + "\n", line)
            w_str += line
        else:
            w_str += line
    w_open = open(property_file_dict_local, "w", encoding="utf-8", errors="ignore")
    w_open.write(w_str)
    print("配置文件静态资源版本+1")
    f_open.close()
    w_open.close()


def upload_file(current_host, war_name, env):
    path = get_remote_tomcat_path(current_host, war_name, env)
    if path == 0:
        return
    remote_property_path = get_remote_property_path(path, env)
    if remote_property_path == 0:
        return
    # 上传目录中的文件
    sf = paramiko.Transport((current_host, port))
    sf.connect(username=username, password=password)
    try:
        sftp = paramiko.SFTPClient.from_transport(sf)
        sftp.put(property_file_dict_local, get_remote_property_path(path, env))
        print("配置文件重新上传成功：" + get_remote_property_path(path, env))
    except Exception as ex:
        print('exception:', ex)
    sf.close()


if __name__ == '__main__':
    try:
        input_env_list = sys.argv[1:]
        print(input_env_list)
        if len(input_env_list) == 0:
            input_env = "uat"
        else:
            input_env = input_env_list[0]
        for this_host in host_list:
            deploy(this_host, input_env)
            print("处理完毕-------------------------")
    except Exception as e:
        print(e)
        # war_input_list = []

