from urllib import request
import chardet


def get_page():
    # java 上海 18~19k
    url_page_1 = "https://www.zhipin.com/geek/recommend/jobs.json?page=1&districtCode=0&businessId=0&expectId=54d3c422c0f82cc40nR53ty5Fg~~&cityCode=101020100"
    url_first_detail = "https://www.zhipin.com/geek/job/detail.json?jobId=673e4cfdb5ca76e11nZ-29S7F1A~&lid=6784fdf9-ef61-456d-853f-ea1fec45f4a6.phoenix.1&expectId=54d3c422c0f82cc40nR53ty5Fg~~"
    url_page_2 = "https://www.zhipin.com/geek/recommend/jobs.json?page=2&districtCode=0&businessId=0&expectId=54d3c422c0f82cc40nR53ty5Fg~~&cityCode=101020100"
    head = {
        "Accept": "application/json, text/plain, */*",
        "Accept-Encoding": "gzip, deflate, br",
        "Accept-Language": "zh-CN,zh;q=0.9",
        "Connection": "keep-alive",
        "Cookie": "__c=1517994835; sid=sem_top1; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1517994837; __g=sem_top1; toUrl=/; __l=r=https%3A%2F%2Fwww.baidu.com%2Fbaidu.php%3Fsc.Ks0000amqZra9B-65dSwbTuLtS73IP3Ka7IKrHgexeo1XxjZAhez_OBqRjVuHdzRAkIqq71dY_JflZhBAgjsEmBWhOQKwH-OPvUOfbVN1L64G6kBwjXcYMWChM6mc7OdkWUjiNGm7PLbqNgKDIJ40x2sSlgrLCpmeYFAOL9xg7skfuVOof.7D_NR2Ar5Od663rj6t8AGSPticnDpuCcYlmrtXFBPrM-CrkdqT7jHzk8sHfGmEukmr8a9G4I2UM3PQDrrZo6CpXy7MHWuxJBmOPtrxfOCX17MkEBk_E9xMzxjdsRP5Qal26h26klOYxkOZ9JxWx7MJHY5Bl32AM-CFhY_XEqZtxk3pM3LkQDkEvyN4QPh1-LTVvGmuCy5ZGzu70.U1Yk0ZDqmhq1TsKspynqnfKY5yFETL5y_Tp30A-V5HcsP0KM5gK1IZc0Iybqmh7GuZN_UfKspyfqn6KWpyfqPj0d0AdY5HDsnH-xnH0kPdtznjmzg1nvnjD0pvbqn0KzIjYYPWT0uy-b5HDYn1KxnWDsrj00mhbqnW0Yg1nsr7t1nj-xnHRd0AdW5HTkPjcsrj6YP7tLnHfznHcknj7xnHTknHTsPWDvg100TgKGujYs0Z7Wpyfqn0KzuLw9u1Ys0A7B5HKxn0K-ThTqn0KsTjYYPWfkPjbsrHb0UMus5H08nj0snj0snj00Ugws5H00uAwETjYs0ZFJ5H00uANv5gKW0AuY5H00TA6qn0KET1Ys0AFL5HDs0A4Y5H00TLCq0ZwdT1YknWfdrHRdnHfvnW6YPWTYPWm30ZF-TgfqnHRkP1b4Pj61n1Rzn6K1pyfqmHcYP1K9rHnsnj0znWPWn0KWTvYqfYPAPHD1wWm1PbmLPjDdw0K9m1Yk0ZK85H00TydY5H00Tyd15H00XMfqnfKVmdqhThqV5HKxn7tsg100uA78IyF-gLK_my4GuZnqn7tsg1Kxn0Ksmgwxuhk9u1Ys0AwWpyfqn0K-IA-b5iYk0A71TAPW5H00IgKGUhPW5H00Tydh5HDv0AuWIgfqn0KhXh6qn0Khmgfqn0KlTAkdT1Ys0A7buhk9u1Yk0Akhm1Ys0APzm1YvPHc4nf%26ck%3D2259.5.69.322.148.497.149.126%26shh%3Dwww.baidu.com%26sht%3D98012088_5_dg%26us%3D1.0.1.0.0.0.0%26ie%3Dutf-8%26f%3D8%26ch%3D11%26tn%3D98012088_5_dg%26wd%3Dboss%26oq%3Dboss%26rqlang%3Dcn%26bc%3D110101&l=%2Fuser%2Fsem%2F%3Fsid%3Dsem_top1%26utm_source%3Dbaidu%26utm_medium%3Dcpc%26utm_campaign%3DPC-yixian-pinpaici-2C%26utm_content%3DBOSSzhipin-hexin%26utm_term%3DBOSSzhipin%22&g=%2Fwww.zhipin.com%2Fuser%2Fsem%2F%3Fsid%3Dsem_top1%26utm_source%3Dbaidu%26utm_medium%3Dcpc%26utm_campaign%3DPC-yixian-pinpaici-2C%26utm_content%3DBOSSzhipin-hexin%26utm_term%3DBOSSzhi; t=KhPajywokhVtLmss; wt=KhPajywokhVtLmss; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1517994880; __a=37338408.1517994835..1517994835.4.1.4.4",
        "Host": "www.zhipin.com",
        "Referer": "https://www.zhipin.com/geek/new/index/recommend",
        "User-Agent": 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'
    }
    req = request.Request(url_page_1, headers=head)
    response = request.urlopen(req)
    html = response.read()
    charset = chardet.detect(html)
    print(charset.get("encoding"))
    html = html.decode(charset.get("encoding"))
    print(html)

if __name__ == '__main__':
    print(get_page())