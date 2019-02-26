# -*- coding: utf-8 -*-
import scrapy


class SpiderSpider(scrapy.Spider):
    name = 'spider'
    allowed_domains = ['122.112.236.194']
    start_urls = ['http://122.112.236.194/project-web/user/queryListByParams']
    login_url = 'https://github.com/login'

    def parse(self, response):
        form_data = {
            'username': 'admin',
            'password': 'admin'
        }
        # captcha_url = response.css('img#captcha_image::attr(src)').get()  # 获取验证码
        # if captcha_url:  # 判断是否存在验证码
        #     captcha = self.regonize_captcha(captcha_url)  # 识别验证码
        #     formdata['captcha-solution'] = captcha  # 获取captcha-solution表单字段
        #     captcha_id = response.xpath('//input[@name = "captcha-id"]/@value').get()  # 获取captcha_id表单字段
        #     formdata['captcha-id'] = captcha_id

pass
