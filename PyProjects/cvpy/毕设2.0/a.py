import cv2
import numpy as np
import re
# 提取感兴趣区域
def findROI(img1):
    # 将原图像转化成灰度图像
    gray = cv2.cvtColor(img1, cv2.COLOR_BGR2GRAY)
    # 将灰度图阈值处理转成二值图像
    ret, binary = cv2.threshold(gray, 120, 255, cv2.THRESH_BINARY_INV)
    # cv2.imshow("二值化", binary)
    # 查找绘制图像轮廓
    # contours, hierarchy = cv2.findContours(binary, cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)
    contours, hierarchy = cv2.findContours(binary, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    cv2.imshow("图像的轮廓",contours)
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

    while res <= 0:
        thresholdVal += 30
        a4 = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        retval, dst = cv2.threshold(a4, 135, 255, cv2.THRESH_BINARY)
        wi, hi = dst.shape
        for w in range(wi):
            for h in range(hi):
                if dst[w][h] == 255:
                    img[w][h] = 0
        # k = np.ones((3, 3), np.uint8)
        # img = cv2.morphologyEx(img, cv2.MORPH_CLOSE, k, iterations=6)
        r1 = cv2.medianBlur(img, 5)

        blueChannel = r1[:, :, 0]
        # cv2.imshow("aa",blueChannel)
        w, h = blueChannel.shape
        num = 0
        sum = 0
        for i in range(w):
            for j in range(h):
                if (blueChannel[i][j] != 0):
                    sum += blueChannel[i][j]
                    num = num + 1
                    res = sum / num
    return res
# 统一图像大小
def resizeImage(img):
    size = (300, 300)
    img = cv2.resize(img, size)
    return img

if __name__ == '__main__':
    imgName = r"img_14.png"
    a = re.split(r'/', imgName)
    img0 = cv2.imread(imgName)
    cv2.imshow("读取原图像",img0)
    gray = cv2.cvtColor(img0, cv2.COLOR_BGR2GRAY)
    cv2.imshow("读取灰度图像", gray)
    ret, binary = cv2.threshold(gray, 160, 255, cv2.THRESH_BINARY_INV)
    cv2.imshow("读取二值图像", binary)
    # 查找绘制图像轮廓
    # contours, hierarchy = cv2.findContours(binary, cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)
    contours, hierarchy = cv2.findContours(binary, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    cv2.imshow("提取轮廓", contours)


    cv2.waitKey(0)
