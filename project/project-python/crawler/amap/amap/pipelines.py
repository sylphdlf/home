# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
import time

import pymysql


class AmapPipeline(object):
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
            self.cursor.execute("""insert into p_amap(search_code, origin, destination, waypoints, time_cost, distance, remarks)
                    value (%s, %s, %s, %s, %s, %s, %s)""",
                                (item['search_code'], item['origin'], item['destination'], item['waypoints'], item['time_cost'],
                                 item['distance'], item['remarks']))
            # 提交修改
            self.connect.commit()
        except Exception as error:
            # 出现错误时打印错误日志
            print(error)
        # finally:
            # 关闭连接
            # self.connect.close()
        return item
