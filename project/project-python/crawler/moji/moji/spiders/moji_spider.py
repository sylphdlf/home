import scrapy

from ..items import MojiItem


class MojiSpider(scrapy.Spider):
    name = "moji"
    allowed_domains = ["tianqi.moji.com"]
    start_urls = [
        "https://tianqi.moji.com/weather/china/shanghai/pudong-new-district",
    ]

    def parse(self, response):
        item = MojiItem()
        # 省市区
        addr_select = response.xpath('//div[@class="search_default"]/em/text()').extract()
        addr = addr_select[0].split('，', 2)
        item['city'] = addr[0].strip()
        item['province'] = addr[1].strip()
        item['country'] = addr[2].strip()
        # pm25
        pm25_select = response.xpath('//div[@class="wea_alert clearfix"]/ul/li/a/em/text()').extract()
        pm25 = pm25_select[0].split(" ", 1)[0].strip()
        item['pm25'] = pm25
        # 温度
        temperature_select = response.xpath('//div[@class="wea_weather clearfix"]/em/text()').extract()
        item['temperature'] = temperature_select[0]
        # 天气
        weather_select = response.xpath('//div[@class="wea_weather clearfix"]/b/text()').extract()
        item['weather'] = weather_select[0]
        # 更新时间
        update_time_select = response.xpath('//div[@class="wea_weather clearfix"]/strong/text()').re('\\d{2}:\\d{2}')
        item['data_update_time'] = update_time_select[0]
        # 湿度
        humidity_select = response.xpath('//div[@class="wea_about clearfix"]/span/text()').re('\\d{2}')
        item['humidity'] = humidity_select[0]
        # 风向
        wind_select = response.xpath('//div[@class="wea_about clearfix"]/em/text()').extract()
        item['wind'] = wind_select[0]
        # 提示
        remarks_select = response.xpath('//div[@class="wea_tips clearfix"]/em/text()').extract()
        item['remarks'] = remarks_select[0]

        print(item)



