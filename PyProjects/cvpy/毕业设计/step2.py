import re

import cv2
import numpy as np

res = -1;
thresholdVal=0
imgName = '2.png'
a = re.split(r'/',imgName)

imgRes = cv2.imread(imgName)
while res<=0:
    thresholdVal += 30
    # 读取原图像
    # img1 = cv2.imread("./images/image2.png")
    img1 = cv2.imread(imgName)
    # cv2.imshow("img1", img1)
    # 统一图片大小
    size = (300, 300)
    img1 = cv2.resize(img1, size)

    # 将原图像转化成灰度图像
    gray = cv2.cvtColor(img1, cv2.COLOR_BGR2GRAY)
    # cv2.imshow("灰度图", gray)
    # 将灰度图阈值处理转成二值图像
    ret, binary = cv2.threshold(gray, 127, 255, cv2.THRESH_BINARY_INV)
    # cv2.imshow("二值化", binary)
    # 查找绘制图像轮廓
    contours, hierarchy = cv2.findContours(binary, cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)
    mask = np.zeros(img1.shape, np.uint8)
    mask = cv2.drawContours(mask, contours, -1, (255, 255, 255), -1)
    # cv2.imshow("轮廓", mask)
    # 通过按位与操作，提取出感兴趣区域
    a1 = cv2.bitwise_and(img1, mask)
    # cv2.imshow("提取感兴趣区域结果", a1)
    # cv2.imwrite(r"./res.jpg",res)

    # 腐蚀是最基本的形态学操作之一，它能够将图像的边界点消除，使图像沿着边界向内收缩，也可以将小于指定结构体元素的部分去除。
    kernel = np.ones((2, 2), dtype=np.uint8)
    a2 = cv2.erode(a1, kernel, iterations=2)
    # cv2.imshow("a2", a2)

    # 闭运算
    k = np.ones((3, 3), np.uint8)
    a3 = cv2.morphologyEx(a2, cv2.MORPH_CLOSE, k, iterations=6)
    cv2.imshow("a3", a3)

    a4 = cv2.cvtColor(a3, cv2.COLOR_BGR2GRAY)
    retval, dst = cv2.threshold(a4, thresholdVal, 255, cv2.THRESH_BINARY)
    wi, hi = dst.shape
    for w in range(wi):
        for h in range(hi):
            if dst[w][h] == 255:
                a3[w][h] = 0
    cv2.imshow("dst", a3)

    r1 = cv2.medianBlur(a3, 5)
    blueChannel = r1[:, :, 0]
    cv2.imshow("r1", r1)
    # 计算灰度均值
    w, h = blueChannel.shape
    num = 0
    sum = 0
    for i in range(w):
        for j in range(h):
            if (blueChannel[i][j] != 255):
                sum += blueChannel[i][j]
                num = num + 1
    res = sum // num

if res>0 and res<=30:
    imgRes1 = cv2.putText(imgRes,"color:DarkBlue",(0,160),cv2.FONT_HERSHEY_SIMPLEX,1,(0,0,255),3)
    imgRes1 = cv2.putText(imgRes, "name:"+a[2], (0, 80), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 3)
    cv2.imshow("检测结果",imgRes1)
    print("色差为深蓝的太阳能电池片")
elif res<=60:
    print("色差为中蓝的太阳能电池片")
elif res <= 120:
    imgRes1 = cv2.putText(imgRes, "color:LightBlue", (0, 160), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 3)
    imgRes1 = cv2.putText(imgRes, "name:" + a[2], (0, 80), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 3)
    cv2.imshow("检测结果", imgRes)
    print("色差为浅蓝的太阳能电池片")
else:
    cv2.imshow("检测结果", imgRes)
    print("系统异常，请稍后重试")
    imgRes1 = cv2.putText(imgRes, "color:MediumBlue", (0, 160), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 3)
    imgRes1 = cv2.putText(imgRes, "name:" + a[2], (0, 80), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 3)
    cv2.imshow("检测结果", imgRes)

cv2.waitKey(0)

