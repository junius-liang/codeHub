# Canny边缘检测
import cv2
from matplotlib import pyplot as plt
import numpy as np

myavatar = cv2.imread("1.jpg", 0)
# 参数：灰度图；较小的阈值将间段的边缘连接起来；较大的边缘检测图像中明显的边缘
a = cv2.Canny(myavatar,0,100)
cv2.imshow("image", a)
cv2.waitKey(0)
