### **
import cv2
import numpy as np
img1 = cv2.imread("./image1.png")
cv2.imshow("img1",img1)
gray = cv2.cvtColor(img1,cv2.COLOR_BGR2GRAY)
cv2.imshow("灰度图",gray)
ret,binary = cv2.threshold(gray,127,255,cv2.THRESH_BINARY_INV)
cv2.imshow("二值化",binary)
contours, hierarchy = cv2.findContours(binary, cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)
mask = np.zeros(img1.shape,np.uint8)
mask = cv2.drawContours(mask,contours,-1,(255,255,255),-1)
cv2.imshow("轮廓",mask)
res = cv2.bitwise_and(img1,mask)
cv2.imshow("提取结果",res)
cv2.imwrite(r"./res.jpg",res)
cv2.waitKey(0)
