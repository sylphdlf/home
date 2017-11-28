# encoding=utf-8
import urllib.request
import urllib.parse
import re


def define_header():
    header = "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) " \
             "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36"
    return header


def simple_sample():
    url = "http://www.baidu.com"
    req = urllib.request.Request(url)
    req.add_header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                                 "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36")
    data = urllib.request.urlopen(req, timeout=2).read()
    print(data)


def post_sample():
    url = "http://139.196.28.23:9091/xiaoxiangweb/admin/login"
    post_data = urllib.parse.urlencode({
        "username": "admin",
        "password": "admin"
    }).encode("utf-8")
    req = urllib.request.Request(url, post_data)
    req.add_header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                                 "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36")
    data = urllib.request.urlopen(req).read()
    print(data)


def rex_sample():
    text = "hello,world"
    pattern = "hello"
    result = re.search(pattern, text)
    print(result)

if __name__ == '__main__':
    rex_sample()
