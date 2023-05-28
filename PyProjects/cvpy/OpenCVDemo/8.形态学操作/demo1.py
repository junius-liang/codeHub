import cv2 as cv
import numpy as np
# 腐蚀是最基本的形态学操作之一，它能够将图像的边界点消除，使图像沿着边界向内收缩，也可以将小于指定结构体元素的部分去除。
a1 = cv.imread("a.jpg")
kernel = np.ones((2,2),dtype=np.uint8)
res = cv.erode(a1,kernel,iterations=2)
cv.imshow("a1",res)
cv.imwrite("a1.jpg",res)
cv.waitKey(0)