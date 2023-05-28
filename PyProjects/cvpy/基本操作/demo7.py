import cv2
import numpy as np
img1 =cv2.imread("1.jpg")

b,g,r = cv2.split(img1)
r[:,:]=0
g[:,:]=0
b[:,:]=114
img2 = cv2.merge([b,g,r])
cv2.imshow("img2",img2)



cv2.waitKey(0)