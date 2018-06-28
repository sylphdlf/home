# encoding=utf-8

from html.parser import HTMLParser


class MyHTMLParser(HTMLParser):
    # 是否获取文本
    get_text = False
    value_dict = {}
    temp_father_tag = ""
    temp_key = ''

    def error(self, message):
        print(message)
        pass

    def handle_starttag(self, tag, attr):
        if tag == 'div':
            if len(attr) == 0:
                pass
            else:
                for(variable, value) in attr:
                    # pm25
                    if variable == "class" and value == "wea_alert clearfix":
                        self.get_text = True
                        self.temp_father_tag = "wea_alert"
                    # weather
                    if variable == "class" and value == "wea_weather clearfix":
                        self.get_text = True
                        self.temp_father_tag = "wea_weather"
                    # detail
                    if variable == "class" and value == "wea_about clearfix":
                        self.get_text = True
                        self.temp_father_tag = "wea_about"
                    # tips
                    if variable == "class" and value == "wea_tips clearfix":
                        self.get_text = True
                        self.temp_father_tag = "wea_tips"

    def handle_endtag(self, tag):
        if tag == 'div':
            self.get_text = False

    # def handle_startendtag(self, tag, attr):
    #     # print('<%s/>' % tag)
    #
    def handle_data(self, data):
        if self.get_text and data.strip() != "":
            if self.temp_father_tag == "wea_alert" and self.lasttag == "em":
                self.value_dict["pm25"] = data
            if self.temp_father_tag == "wea_weather":
                if self.lasttag == "em":
                    self.value_dict["tempCurrent"] = data
                if self.lasttag == "b":
                    self.value_dict["weatherCurrent"] = data
                if self.lasttag == "strong":
                    self.value_dict["websiteUpdateTime"] = data
            if self.temp_father_tag == "wea_about":
                if self.lasttag == "span":
                    self.value_dict["humidity"] = data
                if self.lasttag == "em":
                    self.value_dict["wind"] = data
            if self.temp_father_tag == "wea_tips":
                if self.lasttag == "em":
                    self.value_dict["remark"] = data
    # def handle_comment(self, data):
    #     print('<!--', data, '-->')
    #
    # def handle_entityref(self, name):
    #     print('&%s;' % name)
    #
    # def handle_charref(self, name):
    #     print('&#%s;' % name)

