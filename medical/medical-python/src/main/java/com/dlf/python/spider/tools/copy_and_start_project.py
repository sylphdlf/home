# encoding=utf-8
import subprocess,signal,os


def find_port_by_name():
    p = subprocess.Popen("ps -ef | grep tomcat", shell=True, stdout=subprocess.PIPE)
    out, err = p.communicate()
    for line in out.splitlines():
        if 'tomcat' in line:
            # pid = int(line.split(None, 1)[0])
            pid = line.split(None, 1)[0]
            print(pid)

    # os.kill(pid, signal.SIGKILL)
if __name__ == '__main__':
    find_port_by_name()
