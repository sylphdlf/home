# encoding=utf-8
from urllib import request


class RequestUtils:

    @staticmethod
    def get_html(url):
        head = {
            "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
            "Accept-Language": "zh-CN,zh;q=0.9",
            "Connection": "keep-alive",
            "Cookie": "t=KhPajywokhVtLmss; wt=KhPajywokhVtLmss; sid=sem_pz_bdpc_index; JSESSIONID=""; __c=1518140098; __g=sem_pz_bdpc_index; __l=l=%2Fwww.zhipin.com%2F%3Fsid%3Dsem_pz_bdpc_index&r=http%3A%2F%2Fbzclk.baidu.com%2Fadrc.php%3Ft%3D06KL00c00f7owkb0e1up0KZEgsASSMkp00000ADyb-b00000IrfSFW.THdBULP1doZA80K85yF9pywdpAqVuNqsusK15ym1rjDvn103nj0snvRvnWb0IHYdfRwAf1FarjFAPbfdPWDYPYD4f1fsP16kPHfvnj77wfK95gTqFhdWpyfqn103PHT4rH6zPausThqbpyfqnHm0uHdCIZwsT1CEQLILIz4lpA-spy38mvqVQ1q1pyfqTvNVgLKlgvFbTAPxpy4bug60mLFW5HRznj6v%26tpl%3Dtpl_10085_16624_12226%26l%3D1502746209%26attach%3Dlocation%253D%2526linkName%253D%2525E6%2525A0%252587%2525E9%2525A2%252598%2526linkText%253DBOSS%2525E7%25259B%2525B4%2525E8%252581%252598%2525EF%2525BC%25259A%2525E6%25258D%2525A2%2525E5%2525B7%2525A5%2525E4%2525BD%25259C%2525E5%2525B0%2525B1%2525E6%252598%2525AF%2525E6%25258D%2525A2Boss%2526xp%253Did(%252522m50107b1c%252522)%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FH2%25255B1%25255D%25252FA%25255B1%25255D%2526linkType%253D%2526checksum%253D220%26wd%3Dboss%25E7%259B%25B4%25E8%2581%2598%26issp%3D1%26f%3D8%26ie%3Dutf-8%26rqlang%3Dcn%26tn%3Dbaiduhome_pg%26inputT%3D2509&g=%2Fwww.zhipin.com%2F%3Fsid%3Dsem_pz_bdpc_index; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1517994837,1518053593,1518140098; lastCity=101020100; __a=37338408.1517994835.1518053593.1518140098.35.3.24.24; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1518140934",
            "Host": "www.zhipin.com",
            "Upgrade-Insecure-Requests": "1",
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"
        }
        req = request.Request(url, headers=head)
        response = request.urlopen(req)
        html = response.read().decode("utf-8")
        return html

    @staticmethod
    def get_html(head, url):
        req = request.Request(url, headers=head)
        response = request.urlopen(req)
        html = response.read().decode("utf-8")
        return html