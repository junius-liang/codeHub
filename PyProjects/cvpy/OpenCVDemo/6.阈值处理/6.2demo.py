# 自适应阈值处理
import cv2
img = cv2.imread("./img3.jpg")
res = cv2.adaptiveThreshold(img,255,cv2.ADAPTIVE_THRESH_MEAN_C,cv2.THRESH_BINARY,5,3)
cv2.imshow("res",res)
cv2.waitKey(0)