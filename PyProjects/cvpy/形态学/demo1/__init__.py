import cv2
import numpy as np

myavatar = cv2.imread("1.jpg")
# 定义核结构
kernel = np.ones((10, 10), np.uint8)
res = cv2.morphologyEx(myavatar, cv2.MORPH_TOPHAT, kernel)
cv2.imshow("image",res)
cv2.waitKey(0)
