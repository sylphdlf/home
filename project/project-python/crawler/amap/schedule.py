import threading
import os


def start_crawl():
    os.system("scrapy crawl amap")
    threading.Timer(60, start_crawl).start()


if __name__ == '__main__':
    print("开始执行定时任务 scrapy crawl amap")
    start_crawl()
