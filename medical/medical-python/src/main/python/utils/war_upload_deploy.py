# encoding=utf-8
import paramiko
import os

tomcat_uat = '/usr/local/tomcat-uat/'  # 远程文件或目录，与本地一致，当前为linux目录格式
# tomcat_uat = '/usr/local/src/wars/'  # 远程文件或目录，与本地一致，当前为linux目录格式
tomcat_uat_admin = '/usr/local/admin-tomcat-uat/'
# tomcat_uat_admin = '/usr/local/src/wars/'
# 前后台tomcat路径
ftp_dict_1 = {'front.war': tomcat_uat, 'xiaoxiangweb.war': tomcat_uat_admin}
ftp_dict_2 = {'front.war': tomcat_uat}
# 服务器地址
host_1 = "139.196.28.23"
host_2 = "139.196.40.91"
# 服务器对应的tomcat地址
host = {host_1: ftp_dict_1, host_2: ftp_dict_2}  # 主机
port = 58422  # 端口
username = 'deployer'  # 用户名
password = 'deployer.123'  # 密码
local = 'D:\\ftpLocal\\'  # 本地文件或目录，与远程一致，当前为windows目录格式，window目录中间需要使用双斜线


# 杀进程&删war包
def kill_and_remove_war(current_host, war_name):
    path = host[current_host].get(war_name, 0)
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
    print(path + "webapps 下的文件已删除")
    ssh.close()


def restart_tomcat(current_host, war_name):
    path = host[current_host].get(war_name, 0)
    if path == 0:
        return
    print("重启 " + current_host + " " + war_name)
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(current_host, port=port, username=username, password=password, timeout=20)
    # cmd = "sh " + path + "bin/startup.sh"
    cmd = "ll " + path
    stdin, stdout, stderr = ssh.exec_command(cmd, get_pty=True)
    print(stdout.readlines)
    print(stderr.readlines)
    print("tomcat重启成功")
    ssh.close()


def file_upload(current_host, war_name):
    path = host[current_host].get(war_name, 0)
    if path == 0:
        return
    # 上传目录中的文件
    sf = paramiko.Transport((current_host, port))
    sf.connect(username=username, password=password)
    try:
        print("开始上传 " + war_name + " 到 " + path + "webapps")
        sftp = paramiko.SFTPClient.from_transport(sf)
        sftp.put(os.path.join(local + war_name), os.path.join(path + "webapps/" + war_name))
        print(war_name + "上传成功")
    except Exception as e:
        print('exception:', e)
    sf.close()


def deploy(current_host):
    # 判断本地参数是目录还是文件
    if os.path.isdir(local):
        # 遍历本地目录
        for name in os.listdir(local):
            # 杀进程和删文件
            # kill_and_remove_war(current_host, name)
            # 文件上传
            # file_upload(current_host, name)
            # 重启tomcat
            restart_tomcat(current_host, name)


def modify_property_file():
    return ""


def start_tomcat():
    return ""


if __name__ == '__main__':
    for this_host in host:
        deploy(this_host)
        print("--------------------------------------------------")

