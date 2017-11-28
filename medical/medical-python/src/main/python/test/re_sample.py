# encoding=utf-8
import re

if __name__ == '__main__':
    string = "<a href='http：// www.baidu.com'>百度首页</a>"
    pattern = "(http)(s)?.*[.com|.cn]"
    result = re.search(pattern, string)
    print(result)
