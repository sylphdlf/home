import time
import os
import schedule


def job_morning():
    count = 0
    while count < 10:
        print("schedule_amap:" + count)
        start_crawl()
        count += 1
        time.sleep(60)


def job_evening():
    count = 0
    while count < 60:
        print("schedule_amap:" + count)
        start_crawl()
        count += 1
        time.sleep(60)


def start_crawl():
    os.system("scrapy crawl amap")


schedule.every().day.at("12:00").do(job_morning)
schedule.every().day.at("18:00").do(job_evening)


while True:
    schedule.run_pending()

