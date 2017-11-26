# encoding=utf-8
import subprocess
import os
import signal
import sys

from pip import commands
# war包路径
war_src = "/usr/local/src/wars"
# war包对应tomcat路径
war_dict = {"front": "/usr/local/tomcat-test", "xiaoxiangweb": "/usr/local/admin-tomcat-test"}


def find_port_and_kill(war_name):
    server_address = war_dict[war_name]
    # 杀进程
    p = subprocess.Popen(['ps -ef|grep ' + server_address], shell=True, stdout=subprocess.PIPE)
    out, err = p.communicate()
    for line in out.splitlines():
        pid = int(line.split(None, 2)[1])
        try:
            os.kill(pid, signal.SIGKILL)
        except:
            pass
        else:
            print("服务：" + server_address + " |进程号：" + str(pid) + " 已经被kill")


def copy_file_and_deploy(war_name):
    server_address = war_dict[war_name]
    # 删除原始文件
    print("正在删除 " + server_address + "/webapps 下对应的文件")
    rm_log = subprocess.Popen(['cd ' + server_address + '/webapps && rm -rf ' + war_name + ' ' + war_name + '.war'], shell=True, stdout=subprocess.PIPE)
    rm_log.wait()
    # check war exists
    p = subprocess.Popen(['cd ' + server_address + '/webapps && ls | grep ' + war_name], shell=True, stdout=subprocess.PIPE)
    out = p.stdout.readlines()
    if len(out) == 0:
        print("删除成功，正在复制文件：" + war_name + ".war")
        # 复制文件到webapps下
        copy_log = subprocess.Popen(['cp ' + war_src + '/' + war_name + '.war' + ' ' + server_address + '/webapps'], shell=True, stdout=subprocess.PIPE)
        copy_log.wait()
    else:
        print("删除失败，请手动删除 " + server_address + "/webapps" + " 下的文件")
    print("开始启动：" + server_address)
    # 检查catalina.out是否存在
    log_exist = subprocess.Popen(['ls ' + server_address + '/logs' + ' |grep catalina.out'], shell=True, stdout=subprocess.PIPE)
    log_out, log_error = log_exist.communicate()
    if len(log_out.splitlines()) == 0:
        # 创建一个catalina.out文件
        subprocess.Popen(['cd ' + server_address + '/logs' + ' && ' + 'touch catalina.out'], shell=True)
    # 启动tomcat
    tomcat_log = subprocess.Popen(['sh ' + server_address + '/bin/startup.sh'], shell=True, stdout=subprocess.PIPE)
    tomcat_log.wait()


def get_file(war_list):
    # copy input files
    if len(war_input_list) > 0:
        for war_name in war_list:
            if war_name in war_dict:
                find_port_and_kill(war_name)
                copy_file_and_deploy(war_name)
            else:
                print(war_name + ".war" + "不在部署列表中，请检查配置")
    # copy all files
    else:
        # 遍历文件夹下的所有war文件
        list_log = subprocess.Popen(['ls -l ' + war_src + '| grep war'], shell=True, stdout=subprocess.PIPE)
        # list_out, list_error = list_log.communicate()
        lines = list_log.stdout.readlines()
        if len(lines) == 0:
            print("在 " + war_src + "下没有找到war文件")
        else:
            for line in lines:
                # 以空格切分8段取最后一段，再用空格切分一段
                war_name = (line.split(None, 8)[8]).split(".", 1)[0]
                if war_name in war_dict:
                    find_port_and_kill(war_name)
                    copy_file_and_deploy(war_name)
                    print(war_name + ".war 部署完毕")
                else:
                    print(war_name + ".war" + "不在部署列表中，请检查配置")


if __name__ == '__main__':
    try:
        war_input_list = sys.argv[1:]
    except:
        war_input_list = []
        print("使用默认部署方式，遍历 " + war_src + " 下的war文件")
    else:
        get_file(war_input_list)



