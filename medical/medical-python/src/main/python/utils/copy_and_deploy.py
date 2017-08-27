# encoding=utf-8
import os,subprocess,signal,sys

from pip import commands

war_input_list = sys.argv[1:]
war_src = "/data/war/"
war_dict = {'medical-web': '/data/tomcat-8081'}


def find_port_and_kill(server_address):
    # 杀进程
    p = subprocess.Popen(['ps -ef|grep ' + server_address], shell=True, stdout=subprocess.PIPE)
    out, err = p.communicate()
    for line in out.splitlines():
        pid = int(line.split(None, 2)[1])
        try:
            os.kill(pid, signal.SIGKILL)
        except:
            print('No such process:' + str(pid))
        else:
            print("服务：" + server_address + " |进程号：" + str(pid) + " 已经被kill")


def copy_file_and_deploy(war_name):
    server_address = war_dict[war_name];
    # 删除原始文件
    print("正在删除 " + server_address + " 下对应的文件")
    subprocess.Popen(['rm -rf ' + war_name + ' ' + war_name + '.war'], shell=True)
    # check war exists
    p = subprocess.Popen(['ls | grep ' + war_name], shell=True, stdout=subprocess.PIPE)
    out, err = p.communicate()
    if len(out.splitlines()) == 0:
        print("删除成功，正在复制文件：" + war_name + ".war")
        # 复制文件到webapps下
        subprocess.Popen(['cp ' + war_src + war_name + '.war' + ' ' + server_address + '/webapps'], shell=True)
    else:
        print("删除失败，请手动删除 " + server_address + "/webapps" + " 下的文件")
    print("开始启动：" + server_address)
    # 检查catalina.out是否存在
    log_exist = subprocess.Popen(['ls ' + server_address + '/logs' + ' |grep catalina.out'], shell=True, stdout=subprocess.PIPE)
    log_out, log_error = log_exist.communicate();
    if len(log_out.splitlines()) == 0:
        # 创建一个catalina.out文件
        subprocess.Popen(['cd ' + server_address + '/logs' + ' && ' + 'touch catalina.out'], shell=True)
    # 启动tomcat
    start_up = subprocess.Popen(['sh ' + server_address + '/bin/startup.sh'], shell=True, stdout=subprocess.PIPE)



def get_file(war_input_list):
    # copy selected files
    if len(war_input_list) > 0:
        for war_name in war_input_list:
            if war_name in war_dict:
                find_port_and_kill(war_dict[war_name])
                copy_file_and_deploy(war_name)
    # TODO copy all files
    else:
        print("all do")

if __name__ == '__main__':
    get_file(war_input_list)