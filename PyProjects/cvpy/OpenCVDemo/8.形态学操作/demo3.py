import cv2 as cv
import numpy as np

a1 = cv.imread("a1.jpg")
k = np.ones((3,3),np.uint8)
a2 = cv.morphologyEx(a1,cv.MORPH_CLOSE,k,iterations=6)
cv.imshow("a2",a2)
# 补充，需要对该图像内部进行滤波处理
# a2  = cv.blur(a2,(3,3))
# r1 = cv.medianBlur(a2,5)
# cv.imshow("r1",r1)
# cv.imshow("r1",r1)
# cv.imwrite("a2.jpg",a2)
cv.waitKey(0)