import scrapy
from ..items import AmapItem


class AmapSpider(scrapy.Spider):
    name = "amap"
    allowed_domains = ["restapi.amap.com"]
    start_urls = [
        # 上班路线
        "https://restapi.amap.com/v3/direction/driving?origin=121.65325,31.210777&destination=121.606848,31.191422&waypoints=121.662697,31.19469;121.622949,31.183812&extensions=base&output=xml&key=28d06fb2be2b3693d7ee8f4b3ed14f18",
        "https://restapi.amap.com/v3/direction/driving?origin=121.660837,31.200691&destination=121.661792,31.194276&extensions=all&output=xml&key=28d06fb2be2b3693d7ee8f4b3ed14f18",
        "https://restapi.amap.com/v3/direction/driving?origin=121.642773,31.18875&destination=121.629577,31.186139&extensions=all&output=xml&key=28d06fb2be2b3693d7ee8f4b3ed14f18",
    ]

    def parse(self, response):
        self.log('A response from %s just arrived!' % response.url)
        item = AmapItem()
        item['time_cost'] = response.xpath('//paths[@type="list"]/path/duration/text()').extract()[0]
        item['distance'] = response.xpath('//paths[@type="list"]/path/distance/text()').extract()[0]
        if item['distance'] == '9333':
            item['origin'] = '家'
            item['destination'] = '公司'
            item['waypoints'] = '华夏中路'
            item['remarks'] = '上班路线-华夏中路'
            item['search_code'] = 'home-hxzl-office'
        elif item['distance'] == '802':
            item['origin'] = '唐陆公路'
            item['destination'] = '华夏中路'
            item['waypoints'] = ''
            item['remarks'] = '上班路线-唐陆公路拥堵情况'
            item['search_code'] = 'tlgl-hxzl'
        elif item['distance'] == '1289':
            item['origin'] = '华夏中路'
            item['destination'] = '张江路'
            item['waypoints'] = ''
            item['remarks'] = '上班路线-华夏中路到张江路拥堵情况'
            item['search_code'] = 'hxzl-zjl'
        yield item


