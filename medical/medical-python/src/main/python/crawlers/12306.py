import urllib.request

def query_train_list():
    url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2017-07-31&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=JJG&purpose_codes=ADULT"
    headers = {
        'Accept': '*/*',
        'Accept-Encoding':'gzip, deflate, sdch, br',
        'Accept-Language':'zh-CN,zh;q=0.8',
        'Cache-Control': 'no-cache',
        'Connection': 'keep-alive',
        'Cookie': 'JSESSIONID=7F00000192FCDCE078B35CEA9E30FBF006F461BFD7; __NRF=3B7FF194A4F21D2D909B869FC0317225; route=9036359bb8a8a461c164a04f8f50b252; BIGipServerotn=401605130.24610.0000; current_captcha_type=Z; _jc_save_fromStation=%u4E0A%u6D77%2CSHH; _jc_save_toStation=%u4E5D%u6C5F%2CJJG; _jc_save_fromDate=2017-07-31; _jc_save_toDate=2017-07-04; _jc_save_wfdc_flag=dc',
        'Host': 'kyfw.12306.cn',
        'If-Modified-Since': '0',
        'Referer': 'https://kyfw.12306.cn/otn/leftTicket/init',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3103.400 QQBrowser/9.6.11372.400',
        'X-Requested-With':'XMLHttpRequest'
    }
    data = urllib.request.urlopen(url).read()
    data = data.decode("UTF-8")
    print(data)

if __name__ == '__main__':
    query_train_list()
