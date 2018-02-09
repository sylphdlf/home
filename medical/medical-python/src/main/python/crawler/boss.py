# encoding=utf-8
from urllib import request
import chardet
import json
import time




# url_page_1 = "https://www.zhipin.com/geek/recommend/jobs.json?page=100&districtCode=0&businessId=0&expectId=54d3c422c0f82cc40nR53ty5Fg~~&cityCode=101020100"
# url_job_detail = "https://www.zhipin.com/geek/job/detail.json?jobId=77a96f7510c6dc9c1nB939W6FVs~&lid=8a088e75-aa7f-44f0-b267-7aae8f1fb141.phoenix.1&expectId=54d3c422c0f82cc40nR53ty5Fg~~"
# 获取列表链接
from src.main.python.dbutils.MysqlUtils import MysqlUtils


def get_page_url(page_cur):
    page_url = "https://www.zhipin.com/geek/recommend/jobs.json?page=" + str(page_cur) + "&districtCode=0&businessId=0&expectId=54d3c422c0f82cc40nR53ty5Fg~~&cityCode=101020100"
    return page_url


# 获取职位详细信息
def get_detail_url(job_id, lid, expect_id):
    url_job_detail = "https://www.zhipin.com/geek/job/detail.json?jobId=" + str(job_id) + "&lid=" + str(lid) + "&expectId=" + str(expect_id)
    return url_job_detail


# 通过url获取接口数据
def get_html(url):
    # java 上海 18~19k
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
    req = request.Request(url, headers=head)
    response = request.urlopen(req)
    html = response.read()
    charset = chardet.detect(html)
    print(charset.get("encoding"))
    data = html.decode(charset.get("encoding"))
    return data


# 分页循环
def get_page_loop(page_cur):
    json_data = get_html(get_page_url(page_cur))
    json_obj = json.loads(json_data)
    # 第一层，获取data下的数据
    data = json_obj['data']
    # 获取每页数量
    page_size = data['pageSize']
    # 当前页数
    page_cur = data['page']
    # 是否还有更多数据(判断执行条件)
    has_more = data['hasMore']
    # 获取地区列表
    # district = data['businessDistrict']
    # 获取职位列表
    job_list = data['jobSearchInfoList']
    insert_job_list = []
    if len(job_list) != 0:
        for job in job_list:
            # 获取职位详细信息
            # detail_json_data = get_job_detail(job['jobId'], job['lid'], job['expectId'])
            insert_job_list.append(job)
        insert_db(insert_job_list)

    # 是否有下一页
    if has_more == 1:
        # 停一秒（防止被封）
        time.sleep(1)
        new_page_index = int(page_cur) + 1
        get_page_loop(new_page_index)


# 获取职位详细信息
def get_job_detail(job_id, lid, expect_id):
    detail_url = get_detail_url(job_id, lid, expect_id)
    return_data = get_html(detail_url)
    json_obj = json.loads(return_data)
    return json_obj['data']


def insert_db(job_list):
    sql_list = []
    if len(job_list) != 0:
        for job in job_list:
            company_id = job['brandId']
            company_name = job['brandName']
            city_area = job['cityAndArea']
            scale_name = job['scaleName']
            stage_name = job['stageName']
            industry_name = job['industryName']
            sql = "insert into job_company (company_id, company_name, city_area, scale_name, stage_name, industry_name) " \
                  "values ('" + str(company_id) + "', '" + str(company_name) + "', '" + str(city_area) + "', '" + str(scale_name) + "', '" + str(stage_name) + "', '" + str(industry_name) + "')"
            sql_list.append(sql)
        MysqlUtils.insert(sql_list)


if __name__ == '__main__':
    get_page_loop(1)
