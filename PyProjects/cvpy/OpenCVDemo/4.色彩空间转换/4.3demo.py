import cv2
import numpy as np
img1 = np.zeros([300,300,3],dtype=np.uint8)
img1[:,:,0]=255
cv2.imshow("img1",img1)
img2 = cv2.cvtColor(img1,cv2.COLOR_BGR2HSV)
cv2.imshow("img2",img2)
cv2.waitKey(0)