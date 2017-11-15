# encoding=utf-8
from bs4 import BeautifulSoup
import random
import requests


# 获取天气预报url
def get_url_by_name(name):
    city = {
        "上海": "http://www.weather.com.cn/weather/101020100.shtml",
        "九江": "http://www.weather.com.cn/weather/101240201.shtml"
    }
    weather_url = city[name]
    return weather_url


# 获取网页的源代码
def get_html_text(url):
    header = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate, sdch',
        'Accept-Language': 'zh-CN,zh;q=0.8',
        'Connection': 'keep-alive',
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.235'
    }
    # 设定超时时间，取随机数是因为防止被网站认为是爬虫
    timeout = random.choice(range(80, 180))
    rep = requests.get(url, headers=header, timeout=timeout)
    rep.encoding = "utf-8"
    return rep.text


# 解析html代码
def get_7days_temp(html_text):
    final_data_7d = []
    bsoup = BeautifulSoup(html_text, "html.parser")
    body = bsoup.body
    data = body.find("div", {"id": "7d"})
    ul = data.find("ul")
    li = ul.find_all("li")

    for day in li:
        temp = []
        date = day.find("h1").string
        temp.append(date)
        info = day.find_all("p")
        temp.append(info[0].string)
        if info[1].find("span") is None:
            high_temp = None
        else:
            high_temp = info[1].find("span").string
            high_temp = high_temp.replace("℃", "")
        low_temp = info[1].find("i").string
        low_temp = low_temp.replace("℃","")
        temp.append(high_temp)
        temp.append(low_temp)
        final_data_7d.append(temp)
    return final_data_7d


def get_hours_temp(html_text):
    final_hours_data = []
    bsoup = BeautifulSoup(html_text, "html.parser")
    body = bsoup.find("body")
    script = body.find_all("script")
    print(body)

    # hour3data
    # hours_data = data.find("div", {"class", "time"})
    # hours = hours_data.find_all("em")
    #
    # print(hours)


if __name__ == "__main__":
    url = get_url_by_name("上海")
    html = get_html_text(url)
    data = get_7days_temp(html)
    for line in data:
        print(line)




