# encoding=utf-8

import mysql.connector


def get_connection():
    conn = mysql.connector.connect(host='122.112.236.194', port=3306, user='root', password='root1989128', database='project')
    cursor = conn.cursor()
    cursor.execute('SELECT * FROM city')
    values = cursor.fetchall()
    for this_value in values:
        print(this_value)

if __name__ == '__main__':
    get_connection()