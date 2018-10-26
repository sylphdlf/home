import threading
import os


def start_moji_crawl():
    os.system("scrapy crawl moji")
    threading.Timer(5, start_moji_crawl).start()


if __name__ == '__main__':
    print("开始执行定时任务 scrapy crawl moji")
    start_moji_crawl()
