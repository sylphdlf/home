# -*- coding: utf-8 -*-
import re

import scrapy
from ..items import StockItem


class StockListSpider(scrapy.Spider):
    name = 'stock_list'
    allowed_domains = ['quote.eastmoney.com']
    start_urls = ['http://quote.eastmoney.com/stock_list.html']
    headers = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate',
        'Accept-Language': 'zh-CN,zh;q=0.9,en;q=0.8',
        'Cache-Control': 'max-age=0',
        'Connection': 'keep-alive',
        'Cookie': 'qgqp_b_id=ef82b0b670819533ed99fea97dbdbf29; st_si=83200752620193; st_asi=delete; HAList=l-sh-201000-R003%2Cl-sh-204091-GC091; st_pvi=02565958927821; st_sp=2019-03-21%2014%3A12%3A00; st_inirUrl=https%3A%2F%2Fwww.baidu.com%2Flink; st_sn=3; st_psi=20190322103211857-113200301358-1554615339',
        'Host': 'quote.eastmoney.com',
        # 'If-Modified-Since': 'Mon, 28 Jan 2019 10:02:27 GMT',
        # 'If-None-Match': '"a3541b93f0b6d41:0"',
        'Pragma': 'akamai-x-cache-on, akamai-x-cache-remote-on, akamai-x-check-cacheable, akamai-x-get-cache-key, akamai-x-get-extracted-values, akamai-x-get-ssl-client-session-id, akamai-x-get-true-cache-key, akamai-x-serial-no, akamai-x-get-request-id,akamai-x-get-nonces,akamai-x-get-client-ip,akamai-x-feo-trace',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36',
    }

    def start_requests(self):
        for url in self.start_urls:
            yield scrapy.Request(url, headers=self.headers, callback=self.parse)

    def parse(self, response):
        # update_time_select = response.xpath('//ul[@class="wea_weather clearfix"]/strong/text()').re('\\d{1,2}:\\d{2}')
        # ul_list = response.body
        print(response.body)
        item = StockItem()
        li_list = response.xpath("//ul/li/a[@target='_blank'][contains(@href,'quote.eastmoney.com')][re:test(text(), '.*(.{6})')]")
        for li in li_list:
            market_code = li.re("\w{2}\d{6}")[0]
            item['market'] = re.search("\w{2}", market_code).group()
            item['code'] = re.search("\d{6}", market_code).group()
            item['name'] = li.xpath(".//text()").extract()[0].split("(", 1)[0]
            yield item

