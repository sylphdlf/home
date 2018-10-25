# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class MojiItem(scrapy.Item):
    # define the fields for your item here like:
    country = scrapy.Field()
    province = scrapy.Field()
    city = scrapy.Field()
    weather = scrapy.Field()
    temperature = scrapy.Field()
    pm25 = scrapy.Field()
    humidity = scrapy.Field()
    wind = scrapy.Field()
    data_update_time = scrapy.Field()
    remarks = scrapy.Field()
