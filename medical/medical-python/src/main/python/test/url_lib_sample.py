import urllib.request


def simple_sample():
    file = urllib.request.urlopen("http://www.baidu.com")
    print(file.getcode())
    data = file.read()
    fhandle = open("E:\\baidu.html", "wb")
    fhandle.write(data)
    fhandle.close()

if __name__ == '__main__':
    simple_sample()