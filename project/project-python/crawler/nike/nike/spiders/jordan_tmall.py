# -*- coding: utf-8 -*-
import scrapy


class JordanTmallSpider(scrapy.Spider):
    name = 'jordan_tmall'
    allowed_domains = ['jordan.tmall.com']
    start_urls = ['https://jordan.tmall.com/i/asynSearch.htm?_ksTS=1551888293847_127&callback=jsonp128&mid=w-16722952378-0&wid=16722952378&path=/category-1328804988.htm&spm=a1z10.1-b-s.w5003-18734961396.8.89d454e10rPaf7&scene=taobao_shop&tbpm=3&catId=1328804988&scid=1328804988']
    headers = {
        ':authority': 'jordan.tmall.com',
        ':method': 'GET',
        ':path': '/i/asynSearch.htm?_ksTS=1551888293847_127&callback=jsonp128&mid=w-16722952378-0&wid=16722952378&path=/category-1328804988.htm&spm=a1z10.1-b-s.w5003-18734961396.8.89d454e10rPaf7&scene=taobao_shop&tbpm=3&catId=1328804988&scid=1328804988',
        ':scheme': 'https',
        'accept': 'text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01',
        'accept-encoding': 'gzip, deflate, br',
        'accept-language': 'zh-CN,zh;q=0.9,en;q=0.8',
        # 'cookie': 'hng=CN%7Czh-CN%7CCNY%7C156; lid=%E9%A3%8E%E4%B9%8Bsylph; cna=O335FIESo3ICAWVQCNjMwwRz; otherx=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0; x=__ll%3D-1%26_ato%3D0; enc=akHrKX2b53MTFn%2FrFEQDpjOc6zNBguI3QWUui9EqLYEDMQgDYDHsHMp78EsaEhsYE8%2BOmIRAtNt5WAB4wvPPUA%3D%3D; pnm_cku822=; t=fd2570eacdb17c7870253a42ece75295; tracknick=%5Cu98CE%5Cu4E4Bsylph; lgc=%5Cu98CE%5Cu4E4Bsylph; _tb_token_=39835b036eebe; cookie2=1d0c0d242b319321975ea567926f09f2; swfstore=240744; dnk=%5Cu98CE%5Cu4E4Bsylph; _m_h5_tk=a43841d1f2b16f0ca6942079983907aa_1551892508023; _m_h5_tk_enc=56f1e780bee37421cee97384a997e82d; whl=-1%260%260%260; cq=ccp%3D0; uc1=cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&cookie21=UtASsssme%2BBq&cookie15=WqG3DMC9VAQiUQ%3D%3D&existShop=false&pas=0&cookie14=UoTZ5bsa%2BkHaAw%3D%3D&tag=10&lng=zh_CN; uc3=vt3=F8dByEvwY4v%2B54aII8c%3D&id2=UNiE58eBRWOf&nk2=1CAkbzDRHEda&lg2=WqG3DMC9VAQiUQ%3D%3D; _l_g_=Ug%3D%3D; ck1=""; unb=380556390; cookie1=AH4IViV95sE5FeN1L3SCwhXrjWt02C5wBv0l9Lgkcd8%3D; login=true; cookie17=UNiE58eBRWOf; _nk_=%5Cu98CE%5Cu4E4Bsylph; uss=""; csg=c0e7c59f; skt=fc3ad6569d1e59ef; isg=BN_f40SY_-nSbfv1jmGubWwUbjOp7DDkdMPUaXEseA7VAP-CeRYHNEpWwtDbmAte',
        'referer': 'https://jordan.tmall.com/category-1328804988.htm?spm=a1z10.1-b-s.w5003-18734961396.8.89d454e10rPaf7&scene=taobao_shop&tbpm=3',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36',
        'x-requested-with': 'XMLHttpRequest',
    }


    def start_requests(self):
        for url in self.start_urls:
            yield scrapy.Request(url, headers=self.headers, callback=self.parse)

    def parse(self, response):
        print(response.request.headers)
        print(response.body)
        pass
