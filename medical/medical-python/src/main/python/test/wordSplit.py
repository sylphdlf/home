# encoding=utf-8
import jieba


def word_split(string):
    split_string = jieba.cut(string, cut_all=False)
    # split_string = jieba.cut(string)
    # split_string = jieba.cut_for_search(string)
    return ",".join(split_string)


if __name__ == '__main__':
    print(word_split("工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作"))
    print(word_split("我们中出了一个叛徒"))
    job_desc = '''岗位职责：
                1. 与海外的技术团队进行沟通及协同开发
                2. 熟悉海外项目并在技术层面提出改进/优化方案
                3. 配合进行国内技术团队的建设工作
                岗位要求：
                1. 3年以上Java开发经验
                2. 熟练掌握JSP/Servlet开发；熟悉Tomcat应用服务器配置及部署；熟悉HTTP协议及基本的前端技术（HTML）；有爬虫开发经验者优先
                3. 熟悉SQL语言，熟练使用MySQL；有数据库设计及优化经验者优先
                4. 熟悉设计模式、高可用性部署、有性能调优经验者优先
                5. 良好的沟通及团队合作能力，主动的学习意愿及良好的学习习惯，认真负责的工作态度
                6. 良好的英文读写能力
                7. 有广告相关行业工作背景者优先
                8. 有其它语言开发经验者优先'''





