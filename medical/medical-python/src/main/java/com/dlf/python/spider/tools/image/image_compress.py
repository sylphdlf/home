# encoding=utf-8
from PIL import Image
import os

exception_list = []


def compare_file(src_path, dst_path):

    original_name = {}
    index = 0
    for filename in os.listdir(src_path):
        index = index + 1
        print(index)
        temp_file = original_name.get(filename, 0)
        if temp_file == 0:
            original_name[filename] = 1
        else:
            temp_file = temp_file + 1
            original_name[filename] = temp_file
    for k, v in original_name.items():
        # if v > 1:
        print(str(k) + "------" + str(v))

    for filename in os.listdir(dst_path):
        temp_file = original_name.get(filename, 0)
        print(temp_file)
        if temp_file == 0:
            exception_list.append(filename)
    print(exception_list)


def compress_image(src_path, dst_path):
    for filename in os.listdir(src_path):
        # 查询目标目录
        if not os.path.exists(dst_path):
                os.makedirs(dst_path)

        # 拼接完整的文件或文件夹路径
        src_file = os.path.join(src_path, filename)
        dst_file = os.path.join(dst_path, filename)

        # 如果是文件就处理
        if os.path.isfile(src_file):
            # if src_file.endswith(".jpg") or src_file.endswith(".png") or src_file.endswith(".jpeg"):
            # 获取图片大小
            # f_size = os.path.getsize(src_file)
            s_img = Image.open(src_file)
            print(src_file)
            w, h = s_img.size
            # 大于100k小于300k 压缩0.8
            # if 102400 <= f_size <= 204800:
            #     d_img = s_img.resize((int(w * 0.8), int(h * 0.8)), Image.ANTIALIAS)
            # # 大于300k小于500k压缩0.7
            # elif 204800 < f_size <= 512000:
            #     d_img = s_img.resize((int(w * 0.7), int(h * 0.7)), Image.ANTIALIAS)
            # elif 512000 < f_size <= 716800:
            #     d_img = s_img.resize((int(w * 0.6), int(h * 0.6)), Image.ANTIALIAS)
            # elif 716800 < f_size <= 1024000:
            #     d_img = s_img.resize((int(w * 0.5), int(h * 0.5)), Image.ANTIALIAS)
            # elif 1024000 < f_size <= 1536000:
            #     d_img = s_img.resize((int(w * 0.3), int(h * 0.3)), Image.ANTIALIAS)
            # elif 1536000 < f_size <= 2048000:
            #     d_img = s_img.resize((int(w * 0.2), int(h * 0.2)), Image.ANTIALIAS)
            # elif 2048000 < f_size:
            #     d_img = s_img.resize((int(w * 0.2), int(h * 0.2)), Image.ANTIALIAS)
            d_img = s_img.resize((w, h), Image.ANTIALIAS)
            # 也可以用srcFile原路径保存,或者更改后缀保存，save这个函数后面可以加压缩编码选项JPEG之类的
            try:
                if len(d_img.split()) == 4:
                    # prevent IOError: cannot write mode RGBA as JPEG
                    r, g, b, a = d_img.split()
                    d_img = Image.merge("RGB", (r, g, b))
                    d_img.save(dst_file)
                else:
                    d_img.save(dst_file)
            except Exception as e:
                exception_list.append(filename)
                print(e)
            print(dst_file+" compressed succeeded")
            # 如果是文件夹就递归
            if os.path.isdir(src_file):
                compress_image(src_file, dst_file)

if __name__ == '__main__':
    # compress_image("D:\develop\imgPath1", "D:\develop\imgPath2")
    # print(exception_list)
    # print(len(exception_list))
    compare_file("D:\develop\imgPath1", "D:\develop\imgPath2")
