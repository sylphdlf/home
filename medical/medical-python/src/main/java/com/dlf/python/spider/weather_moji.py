# encoding=utf-8
from urllib import request

# with request.urlopen('https://tianqi.moji.com/weather/china/shanghai/pudong-new-district') as f:
#     data = f.read()
#     print('Status:', f.status, f.reason)
#     for k, v in f.getheaders():
#         print('%s: %s' % (k, v))
#     print('Data:', data.decode('utf-8'))
# Status: 200 OK
# Server: ASERVER/1.8.0-3
# Date: Mon, 21 Aug 2017 03:46:07 GMT
# Content-Type: text/html; charset=UTF-8
# Transfer-Encoding: chunked
# Connection: close
# X-Powered-By: PHP/7.1.0
# Set-Cookie: moji_setting=%7B%22internal_id%22%3A626%7D; path=/
# Set-Cookie: liveview_page_cursor=eyJtaW5JZCI6ODE0MDY2OTMsIm1heElkIjo4MTQwODE1NCwibWluQ3JlYXRlVGltZSI6MTUwMzEzMzA0NTEzMywibWF4Q3JlYXRlVGltZSI6MTUwMzEzMzQ3MDAwMH0%3D; path=/
# Set-Cookie: __ads_session=sftlLIzV9QiDrNamJgA=; domain=.tianqi.moji.com; path=/
# X-Powered-By-Anquanbao: MISS from pon-bj-mj-ib2

req = request.Request('https://tianqi.moji.com/weather/china/shanghai/pudong-new-district')
# req = request.Request('https://tianqi.moji.com/weather/china/jiangxi/jiujiang')
with request.urlopen(req) as f:
    # if f.status == 200:
    #     print("ok")
    html_data = f.read().decode('utf-8')
    # print(html_data)
    parser = MoJiHtmlParser.MoJiHtmlParser()
    parser.feed(html_data)
    print(parser.value_dict)


