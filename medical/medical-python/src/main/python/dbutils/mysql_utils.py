import mysql.connector


def get_connector():
    conn = mysql.connector.connect(host='139.224.133.52', user='root', password='root1989128', database='dcrawler')
    cursor = conn.cursor()
    return cursor, conn


# def insert(table_name, columns, values):
#     cursor, conn = get_connector()
#     place_holder = []
#     for x in values:
#         place_holder.append("%s")
#     if len(place_holder) != 0:
#         place_holder_str = ",".join(place_holder)
#         sql = 'insert into ' + table_name + ' (' + columns + ') values (' + place_holder_str + ')'
#         cursor.execute(sql, values)
#         conn.commit()
#     cursor.close()

# if __name__ == '__main__':
    # table_name = 'job_keyword'
    # columns = 'batch_num, website, keyword, keyword_num'
    # values = [1, 'https://www.baidu.com', '工作', '2']
    # insert('job_keyword', columns, values)
