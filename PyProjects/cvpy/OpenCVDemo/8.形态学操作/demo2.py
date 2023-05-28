import cv2 as cv
import numpy as np
# 膨胀操作能对图像的边界进行扩张。膨胀操作将与当前对象（前景）接触到的背景点合并到当前对象内，从而实现将图像的边界点向外扩张
a1 = cv.imread("a1.jpg")
kernel = np.ones((1,1),dtype=np.uint8)
res = cv.dilate(a1,kernel,iterations=1)
cv.imshow("a1",res)
cv.waitKey(0)