import os
import time

import schedule


def start_crawl():
    os.system("scrapy crawl moji")


schedule.every(10).minutes.do(start_crawl)


while True:
    schedule.run_pending()
    # 不加的话CPU占用率100%
    time.sleep(0.1)

