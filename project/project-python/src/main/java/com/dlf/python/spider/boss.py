# encoding=utf-8
import urllib.request
import re
import jieba


def get_detail_page():
    target_url = "http://www.zhipin.com/job_detail/1412494002.html?ka=search_list_2"
    # target_url = "http://www.baidu.com"
    headers={
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate, sdch',
        'Accept-Language': 'zh-CN,zh;q=0.8',
        'Cache-Control':'max - age = 0',
        'Connection': 'keep-alive',
        # 'Cookie': '__c=1499079806; __g=-; toUrl=http%3A%2F%2Fwww.zhipin.com%2Fc101020100%2Fh_101020100%2F%3Fquery%3Djava%26page%3D30%26ka%3Dpage-30; lastCity=101020100; __l=r=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DSs339l5aw446TycZrWaKfokzIbVVNEyDOucbt68SHxW%26wd%3D%26eqid%3D8054095b000012af00000003595a247c&l=%2F; __a=34114269.1499079806..1499079806.33.1.33.33; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1499080132; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1499086789',
        'Host': 'www.zhipin.com',
        'Referer': 'http://www.zhipin.com/job_detail/?query=java&scity=101020100&source=2',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3103.400 QQBrowser/9.6.11372.400'
    }
    page_data = urllib.request.urlopen(target_url).read()
    page_data = page_data.decode("UTF-8")
    job_desc = re.findall(r'<div class="job-sec">.*?<h3>职位描述</h3>.*?<div class="text">(.*?)</div>', page_data, re.S)
    job_desc_str = "".join(job_desc)
    job_desc_str = job_desc_str.replace("<br/>","")
    job_desc_str = re.sub(r'[\s+\.\!\;\；\/_,$%^*(\"\']+|[+—！，。？、~@#￥%…&*（）]+', ' ', job_desc_str)
    # word split
    word_split = jieba.cut(job_desc_str, cut_all=False)
    return ",".join(word_split)


if __name__ == '__main__':
    print(get_detail_page())




