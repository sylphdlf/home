import os
import schedule


def start_crawl():
    os.system("scrapy crawl moji")


schedule.every(10).minutes.do(start_crawl)


while True:
    schedule.run_pending()
