import cv2
import numpy as np
import re
# 提取感兴趣区域
def findROI(img):
    # 将原图像转化成灰度图像
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    # 将灰度图阈值处理转成二值图像
    ret, binary = cv2.threshold(gray, 150, 255, cv2.THRESH_BINARY_INV)
    # 查找绘制图像轮廓
    contours, hierarchy = cv2.findContours(binary, cv2.RETR_EXTERNAL,
                                           cv2.CHAIN_APPROX_SIMPLE)
    mask = np.zeros(img.shape, np.uint8)
    mask = cv2.drawContours(mask, contours, -1, (255, 255, 255), -1)
    a1 = cv2.bitwise_and(img, mask)
    return a1
# 计算灰度均值
def dealImage(img):
    a4 = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    retval, dst = cv2.threshold(a4, 150, 255, cv2.THRESH_BINARY)
    wi, hi = dst.shape
    for w in range(wi):
        for h in range(hi):
            if dst[w][h] == 255:
                img[w][h] = 0
    r1 = cv2.medianBlur(img, 5)
    blueChannel = r1[:, :, 0]
    w, h = blueChannel.shape
    num = 0
    sum = 0
    for i in range(w):
        for j in range(h):
            if (blueChannel[i][j] != 0):
                sum += blueChannel[i][j]
                num = num + 1
                res = sum / num
    print(res)
    return res
# 统一图像大小
def resizeImage(img):
    size = (300, 300)
    img = cv2.resize(img, size)
    return img
# 显示结果
def analyse(res):
    if 125 <= res <= 145:
        imgRes1 = cv2.putText(img1, "color:DarkBlue", (0, 160),
                              cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 125), 3)
        cv2.imshow("检测结果", imgRes1)
        print("颜色为深蓝的太阳能电池片")
    if 145 < res <= 165:
        imgRes1 = cv2.putText(img1, "color:MiddleBlue", (0, 160),
                              cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 3)
        cv2.imshow("检测结果", imgRes1)
        print("色差为中蓝的太阳能电池片")
    if 165 < res <= 185:
        imgRes1 = cv2.putText(img1, "color:LightBlue", (0, 160),
                              cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 3)
        cv2.imshow("检测结果", img1)
        print("色差为浅蓝的太阳能电池片")
# 主函数
if __name__ == '__main__':
    imgName = r"3.png"
    a = re.split(r'/', imgName)
    img0 = cv2.imread(imgName)
    img1 = resizeImage(img0)
    img2 = findROI(img1)
    img3 = dealImage(img2)
    analyse(img3)
    cv2.waitKey(0)
