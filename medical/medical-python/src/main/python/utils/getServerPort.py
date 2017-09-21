#!/usr/bin/env python
#coding:utf-8
#filename:tcp.py
'''
author: gavingeng
date:  2011-12-14 09:35:59
'''
import socket
import sys
NORMAL=0
ERROR=1
TIMEOUT=5
port_list = []

def ping(ip,port,timeout=TIMEOUT):
  try:
    cs=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    address=(str(ip),port)
    cs.settimeout(timeout)
    status = cs.connect_ex((address))
    #this status is returnback from tcpserver
    if status != NORMAL :
      print (ERROR)
    else:
      port_list.append(port)
      print (NORMAL)
  except Exception as e:
    print(ERROR)
    print ("error:%s" %e)
    return (ERROR)
  return NORMAL
if __name__=='__main__':
  # if len(sys.argv) < 3 :
  #   print ('请按照如下格式使用: ./tcp.py www.jb51.net 80')
  #   sys.exit(1)
  # ip = sys.argv[1]
  # port = sys.argv[2]
  # try:
  #   timeout = sys.argv[3]
  # except IndexError as e:
  #   timeout=TIMEOUT
  for i in range(60000,65535):
    print(i)
    ping("139.196.28.23",i,1)
  print(port_list)