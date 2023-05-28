from datetime import datetime

import cv2
import numpy as np
import re


# 提取感兴趣区域
def findROI(img):
    # 将原图像转化成灰度图像
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    # 将灰度图阈值处理转成二值图像
    ret, binary = cv2.threshold(gray, 135, 255, cv2.THRESH_BINARY_INV)
    # cv2.imshow("二值化", binary)
    # 查找绘制图像轮廓
    # contours, hierarchy = cv2.findContours(binary, cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)
    contours, hierarchy = cv2.findContours(binary, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    mask = np.zeros(img1.shape, np.uint8)
    mask = cv2.drawContours(mask, contours, -1, (255, 255, 255), -1)
    # cv2.imshow("轮廓", mask)
    # 通过按位与操作，提取出感兴趣区域
    a1 = cv2.bitwise_and(img1, mask)
    # cv2.imshow("a1", mask)
    return a1


# 计算灰度均值
def dealImage(img):
    thresholdVal = 0
    res = -1
    k = np.ones((3, 3), np.uint8)
    # img = cv2.morphologyEx(img, cv2.MORPH_CLOSE, k, iterations=6)
    cv2.imshow("原图像", img)
    a4 = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    # retval, dst = cv2.threshold(a4, 135, 255, cv2.THRESH_BINARY)
    dst = cv2.adaptiveThreshold(a4, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, 5, 3)
    cv2.imshow("提取电池片主栅部分",dst)
    wi, hi = dst.shape
    for w in range(wi):
        for h in range(hi):
            if dst[w][h] == 255:
                img[w][h] = 0
    return img


# 统一图像大小
def resizeImage(img):
    size = (300, 300)
    img = cv2.resize(img, size)
    return img


if __name__ == '__main__':
    p = datetime.now()
    imgName = r"img_14.png"
    img0 = cv2.imread(imgName)
    img1 = resizeImage(img0)
    img2 = findROI(img1)
    dealImage(img2)
    pw = datetime.now()
    print(p)
    print(pw)
    cv2.waitKey(0)








