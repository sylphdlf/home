# encoding=utf-8

import mysql.connector


def get_connection():
    conn = mysql.connector.connect(host='127.0.0.1', port=3306, user='root', password='root', database='world')
    cursor = conn.cursor()
    cursor.execute('SELECT * FROM city')
    values = cursor.fetchall()
    for this_value in values:
        print(this_value)

if __name__ == '__main__':
    get_connection()