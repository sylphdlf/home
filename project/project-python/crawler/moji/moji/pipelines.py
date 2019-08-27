# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
import datetime
import time

import pymysql as pymysql


class MojiPipeline(object):

    def __init__(self):
        # 连接数据库
        self.connect = pymysql.connect(
            host='118.25.197.159',
            port=3306,
            db='project',
            user='root',
            passwd='root1989128',
            charset='utf8',
            use_unicode=True)

        # 通过cursor执行增删查改
        self.cursor = self.connect.cursor()

    def process_item(self, item, spider):
        # 将爬取的信息保存到mysql
        try:
            data_update_time = datetime.datetime.now().strftime("%Y-%m-%d") + " " + item['data_update_time'] + ":00"
            now_time = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            # 超过0点后更新，更新时间获取有问题, 需要天数-1
            if data_update_time > now_time:
                data_update_time = (datetime.datetime.now() + datetime.timedelta(days=-1)).strftime("%Y-%m-%d") + " " + item['data_update_time'] + ":00"
            # print(data_update_time)
            # convert_time = time.strptime(data_update_time, "%Y-%m-%d %H:%M:%S")
            # print(convert_time)
            # time_stamp = time.mktime(convert_time)
            # print(time_stamp)
            # 执行SQL语句
            self.cursor.execute("""insert into p_weather(country, province, city, weather, temperature, pm25, humidity, wind, data_update_time, remarks)
                    value (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)""",
                                (item['country'], item['province'], item['city'], item['weather'],
                                 item['temperature'], item['pm25'], item['humidity'], item['wind'],
                                 data_update_time, item['remarks']))
            # 提交修改
            self.connect.commit()
        except Exception as error:
            # 出现错误时打印错误日志
            print(error)
        # finally:
            # 关闭连接
            # self.connect.close()
        return item
