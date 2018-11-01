import scrapy
from ..items import AmapItem


class AmapSpider(scrapy.Spider):
    name = "amap"
    allowed_domains = ["restapi.amap.com"]
    start_urls = [
        # 上班路线-华夏中路
        "https://restapi.amap.com/v3/direction/driving?origin=121.65325,31.210777&destination=121.606848,31.191422&waypoints=121.662697,31.19469;121.622949,31.183812&extensions=base&output=xml&key=28d06fb2be2b3693d7ee8f4b3ed14f18",
    ]

    def parse(self, response):
        self.log('A response from %s just arrived!' % response.url)
        item = AmapItem()
        item['time_cost'] = response.xpath('//paths[@type="list"]/path/duration/text()').extract()[0]
        item['distance'] = response.xpath('//paths[@type="list"]/path/distance/text()').extract()[0]
        item['origin'] = '家'
        item['destination'] = '公司'
        item['waypoints'] = '华夏中路'
        item['remarks'] = '上班路线-华夏中路'
        item['search_code'] = 'home-hxzl-office'
        yield item


