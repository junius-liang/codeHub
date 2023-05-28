import cv2
import numpy as np

# 读取原图像
img1 = cv2.imread("./2.png")
cv2.imshow("img1", img1)
# 将原图像转化成灰度图像
gray = cv2.cvtColor(img1, cv2.COLOR_BGR2GRAY)
# cv2.imshow("灰度图", gray)
# 将灰度图阈值处理转成二值图像
ret, binary = cv2.threshold(gray, 127, 255, cv2.THRESH_BINARY_INV)
# cv2.imshow("img2", binary)
contours, hierarchy = cv2.findContours(binary, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
mask = np.zeros(img1.shape, np.uint8)
mask = cv2.drawContours(mask, contours, -1, (255, 255, 255), -1)
cv2.imshow("img2", mask)
cv2.waitKey(0)