import scrapy

from ..items import MojiItem
import redis


class MojiSpider(scrapy.Spider):
    name = "moji"
    allowed_domains = ["tianqi.moji.com"]
    start_urls = [
        "https://tianqi.moji.com/weather/china/shanghai/pudong-new-district",
        "https://tianqi.moji.com/weather/china/jiangxi/jiujiang",
    ]

    def parse(self, response):
        self.log('A response from %s just arrived!' % response.url)
        item = MojiItem()
        # 更新时间 个位数补成十位数
        update_time_select = response.xpath('//div[@class="wea_weather clearfix"]/strong/text()').re('\\d{1,2}:\\d{2}')
        time_temp = update_time_select[0]
        time_temp_split = time_temp.split(":", 1)
        if len(time_temp_split[0]) == 1:
            time_temp_split = "0" + time_temp_split[0] + ":" + time_temp_split[1]
        else:
            time_temp_split = time_temp
        item['data_update_time'] = time_temp_split
        # 从缓存中查询更新时间，如果相同则退出
        conn = redis.Redis(host='127.0.0.1', port=6379)
        # decode不然前面会有个b， 例如 b'10:00'
        data_update_time_temp = conn.get("moji_data_update_time:" + response.url.rsplit("/", 1)[1])
        if data_update_time_temp is not None:
            data_update_time_temp = data_update_time_temp.decode()
        print(data_update_time_temp)
        if data_update_time_temp == item['data_update_time']:
            return
        else:
            # 把更新时间放入缓存
            conn.set('moji_data_update_time:' + response.url.rsplit("/", 1)[1], time_temp_split)
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
            # 湿度
            humidity_select = response.xpath('//div[@class="wea_about clearfix"]/span/text()').re('\\d{2}')
            item['humidity'] = humidity_select[0]
            # 风向
            wind_select = response.xpath('//div[@class="wea_about clearfix"]/em/text()').extract()
            item['wind'] = wind_select[0]
            # 提示
            remarks_select = response.xpath('//div[@class="wea_tips clearfix"]/em/text()').extract()
            item['remarks'] = remarks_select[0]
            yield item



