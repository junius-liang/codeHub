import cv2
import numpy as np
# hsv颜色区间 蓝色：[110,50,50]-[130,255,255] 绿色：[50,50,50]-[70,255,255]
img1 = cv2.imread("img2.jpg")
img = cv2.cvtColor(img1,cv2.COLOR_BGR2HSV)
minVal = np.array([0,50,50])
maxVal = np.array([30,255,255])
mask = cv2.inRange(img,minVal,maxVal)
res = cv2.bitwise_and(img,img,mask=mask)
cv2.imshow("res",res)
cv2.waitKey(0)
