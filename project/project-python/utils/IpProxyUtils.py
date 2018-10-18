# encoding=utf-8
from src.main.python.utils.RequestUtils import RequestUtils
from bs4 import BeautifulSoup


class IpProxyUtils:

    ip_list = []

    def get_ip_list(self):
        url = "http://www.xicidaili.com/nn/"
        head = {
            "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
            "Accept-Language": "zh-CN,zh;q=0.9",
            "Cache-Control": "max-age=0",
            "Connection": "keep-alive",
            "Cookie": "_free_proxy_session=BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJTI4NjU5NzYxOTRhNDBjMGMzZWJmMTRlY2UwYmFkNTVhBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMWJYMURneHBMTU43QlA3Q0t3QnZJclM1UjBwcGhsdGVSSThtYkg4TTFTNHc9BjsARg%3D%3D--6087ca693c4021b0e47cb950124ca4d253a2e0a4; Hm_lvt_0cf76c77469e965d2957f0553e6ecf59=1518054041,1518156597,1518156619,1518340780; Hm_lpvt_0cf76c77469e965d2957f0553e6ecf59=1518341024",
            "Host": "www.xicidaili.com",
            "If-None-Match": 'W/"8c464c51ab3868147c311e86a46a20bc"',
            "Referer": "http://www.xicidaili.com/",
            "Upgrade-Insecure-Requests": "1",
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36",
        }
        html = RequestUtils.get_html(head, url)
        soup = BeautifulSoup(html, 'html.parser')
        ip_list = soup.find("table", id="ip_list").find_all("tr")
        if len(ip_list) > 1:
            for obj in ip_list:
                info_list = obj.find_all("td")
                if len(info_list) != 0:
                    this_ip = info_list[1].get_text()
                    this_port = info_list[2].get_text()
                    self.ip_list.append(this_ip + ":" + this_port)
        print(self.ip_list)
        return self.ip_list

t = IpProxyUtils()
t.get_ip_list()


