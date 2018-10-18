# encoding=utf-8
from urllib import request
import chardet
import json
import time
import re
from bs4 import BeautifulSoup
from src.main.python.dbutils.MysqlUtils import MysqlUtils

url_prefix = "https://www.zhipin.com"


# 静态页面链接
def get_html_page_url(page_cur):
    url = "https://www.zhipin.com/c101020100-p100101/e_105-h_101020100/?page=" + str(page_cur) + "&ka=page-" + str(page_cur)
    return url


def get_json_page_url(page_cur):
    url = "https://www.zhipin.com/geek/recommend/jobs.json?page=" + str(page_cur) + "&districtCode=0&businessId=0&expectId=54d3c422c0f82cc40nR53ty5Fg~~&cityCode=101020100"
    return url


# 获取职位详细信息
def get_json_detail_url(job_id, lid, expect_id):
    url = "https://www.zhipin.com/geek/job/detail.json?jobId=" + str(job_id) + "&lid=" + str(lid) + "&expectId=" + str(expect_id)
    return url


# 通过url获取接口数据(json格式)
def get_json(url):
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


# 获取html格式数据
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
    # charset = chardet.detect(html)
    # print(charset.get("encoding"))
    # decode_html = html.decode("gzip")
    # return decode_html


# 分页循环
def get_json_page_loop(page_cur):
    json_data = get_json(get_json_page_url(page_cur))
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
        get_json_page_loop(new_page_index)


# 获取职位详细信息
def get_json_job_detail(job_id, lid, expect_id):
    detail_url = get_json_detail_url(job_id, lid, expect_id)
    return_data = get_json(detail_url)
    json_obj = json.loads(return_data)
    return json_obj['data']


# 静态页面循环
def get_html_page_loop(page_cur):
    html = get_html(get_html_page_url(page_cur))
    # 创建bs对象
    soup = BeautifulSoup(html, 'html.parser')
    job_list = soup.find_all('div', class_='job-primary')
    if len(job_list) != 0:
        sql_list = []
        for job in job_list:
            obj = {}
            # 获取公司地址(简易)
            address_summary = job.find_all("p")[1].contents[0]
            obj["address_summary"] = address_summary
            # 获取公司链接
            company_node = job.find("a", href=re.compile(r'/gongsi/\d+\.html'))
            company_url = company_node.get("href")
            company_url_suffix = company_node.get("ka")
            # https://www.zhipin.com/gongsi/376.html?ka=search_list_company_1
            sql = get_html_company_detail(url_prefix + company_url + "?ka=" + company_url_suffix, obj)
            sql_list.append(sql)
            # 等待1秒
            time.sleep(1)
            # 获取职位链接
            # job_detail_url = job.find('a', href=re.compile(r'/job_detail/\d+\.html')).get("href")
            # get_html_job_detail(job_detail_url)
        # 入库
        if len(sql_list) != 0:
            MysqlUtils.insert(sql_list)
        # 循环分页(等待2秒)
        time.sleep(2)
        page_cur = int(page_cur) + 1
        get_html_page_loop(page_cur)


# 获取公司详细信息
def get_html_company_detail(url, obj):
    print(url)
    html = get_html(url)
    company_id = url[url.rindex("/")+1: url.rindex("html")-1]
    # 创建BS对象
    soup = BeautifulSoup(html, "html.parser")
    # 获取公司标签
    company_banner = soup.find("div", class_="company-banner")
    # 公司名称
    company_name = company_banner.find("h1", class_="name").get_text()
    # 获取公司属性,并用分隔符截断
    company_properties = company_banner.find("p")
    company_properties_list = str(company_properties).split('<em class="vline"></em>')
    # 融资情况
    print(company_properties_list)
    if len(company_properties_list) > 0:
        temp1 = company_properties_list[0].lstrip("<p>")
        # 如果第一个是数字,则为公司人数
        if re.match(r'\d+', temp1):
            stage_name = ""
            scale_name = company_properties_list[0].lstrip("<p>")
            # 如果列表长度为2则有行业参数
            if len(company_properties_list) == 2:
                # 行业
                industry_name = company_properties_list[1].rstrip("</p>")
            else:
                industry_name = ""
        else:
            if len(company_properties_list) == 1:
                # 融资情况
                stage_name = temp1
                scale_name = ""
                industry_name = ""
            else:
                if len(company_properties_list) == 2:
                    # 判断第二个元素是否包含数字
                    temp2 = company_properties_list[1]
                    if re.match(r'\d+', temp2):
                        stage_name = ""
                        # 人数
                        scale_name = temp2
                        # 行业
                        industry_name = company_properties_list[2].rstrip("</p>")
                    else:
                        stage_name = ""
                        scale_name = ""
                        # 行业
                        industry_name = company_properties_list[1].rstrip("</p>")
                else:
                    stage_name = company_properties_list[0].lstrip("<p>")
                    scale_name = company_properties_list[1]
                    industry_name = company_properties_list[2].rstrip("</p>")
    # 公司注册信息
    bus_detail = soup.find("div", class_="business-detail").find_all("li")
    # 注册资本
    company_asset = ""
    if len(bus_detail[1].contents) > 1:
        company_asset = re.findall(r'\d+', str(bus_detail[1].contents[1]))[0]
    # 成立时间
    company_established = ""
    if len(bus_detail[2].contents) > 1:
        company_established = bus_detail[2].contents[1]
    # 公司描述
    if soup.find("div", class_="text fold-text"):
        company_info = soup.find("div", class_="text fold-text").contents[0]
    else:
        company_info = ""
    # 公司详细地址
    address_detail = soup.find("div", class_="location-item show-map").get_text()
    # 公司简介
    address_summary = obj["address_summary"]
    # 拼接sql
    sql = "insert into job_company (company_id, company_name, company_info, company_asset, company_established, address_summary, address_detail, scale_name, stage_name, industry_name)" \
              "VALUES ('" + str(company_id) + "', '" + str(company_name) + "', '" + str(company_info) + "', '" + str(company_asset) + "', '" + str(company_established) + "','" \
              + str(address_summary) + "', '" + str(address_detail).strip() + "', '" + str(scale_name) + "', '" + str(stage_name) + "', '" + str(industry_name) + "')"
    print(sql)
    return sql


# 获取静态页面职位详细信息
def get_html_job_detail(url):
    print(1)


def insert_db(job_list):
    sql_list = []
    if len(job_list) != 0:
        for job in job_list:
            company_id = job['companyId']
            company_name = job['companyName']
            city_area = job['cityAndArea']
            scale_name = job['scaleName']
            stage_name = job['stageName']
            industry_name = job['industryName']
            # 插入公司信息
            sql = "insert into job_company (company_id, company_name, company_info, company_asset, company_established, address_summary, address_detail, scale_name, stage_name, industry_name) " \
                  "values ('" + str(company_id) + "', '" + str(company_name) + "', '" + str(city_area) + "', '" + str(scale_name) + "', '" + str(stage_name) + "', '" + str(industry_name) + "')"
            sql_list.append(sql)
        MysqlUtils.insert(sql_list)


if __name__ == '__main__':
    # get_html_page_loop(2)

    obj = {}
    obj["address_summary"] = "12121212"
    sql = get_html_company_detail("https://www.zhipin.com/gongsi/1170048.html?ka=search_list_company_65", obj)
    print(sql)
    sql_list = [sql]
    MysqlUtils.insert(sql_list)

    # str = "https://www.zhipin.com/gongsi/376.html?ka=search_list_company_1"
    # print(str.rindex("/"))
    # print(str.rindex("html"))
    # print(str[str.rindex("/")+1:str.rindex("html")-1])

    # dt = "1987-12-22 00:00:00"
    # timeArray = time.strptime(dt, "%Y-%m-%d %H:%M:%S")
    # print(timeArray)
