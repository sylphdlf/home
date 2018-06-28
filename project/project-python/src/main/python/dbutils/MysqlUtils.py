# encoding=utf-8
import mysql.connector


class MysqlUtils:

    @staticmethod
    def get_connection():
        conn = mysql.connector.connect(host='139.224.133.52', user='root', password='root1989128', database='dcrawler')
        cursor = conn.cursor()
        return conn, cursor

    @staticmethod
    def insert(sql_list):
        conn, cursor = MysqlUtils.get_connection()
        try:
            if len(sql_list) != 0:
                for index, sql in enumerate(sql_list):
                    cursor.execute(sql)
                    # if index % 10 == 0:
                    conn.commit()
            cursor.close()
        except Exception as e:
            print(e)
        finally:
            cursor.close()