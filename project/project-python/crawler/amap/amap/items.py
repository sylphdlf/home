# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class AmapItem(scrapy.Item):
    # define the fields for your item here like:
    search_code = scrapy.Field()
    time_cost = scrapy.Field()
    distance = scrapy.Field()
    origin = scrapy.Field()
    remarks = scrapy.Field()
    destination = scrapy.Field()
    waypoints = scrapy.Field()
