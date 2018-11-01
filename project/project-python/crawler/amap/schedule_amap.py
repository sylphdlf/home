import time
import os
import schedule


def job_morning():

    count = 0
    while count < 120:
        try:
            print("schedule_amap_morning:" + str(count))
            start_crawl()
            count += 1
            time.sleep(60)
        except Exception as error:
            print(error)


def job_evening():
    count = 0
    while count < 60:
        try:
            print("schedule_amap_morning:" + str(count))
            start_crawl()
            count += 1
            time.sleep(60)
        except Exception as error:
            print(error)


def start_crawl():
    os.system("scrapy crawl amap")


schedule.every().day.at("09:55").do(job_morning)


while True:
    schedule.run_pending()

