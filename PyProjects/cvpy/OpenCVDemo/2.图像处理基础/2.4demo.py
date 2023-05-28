import cv2 as cv
import numpy as np
# 竖彩虹条
img = np.zeros((300,300,3),dtype=np.uint8)
img[:,0:100,0]=255
img[:,100:200,1]=255
img[:,200:300,2]=255
cv.imshow("img",img)

# 横彩虹条
img1 = np.zeros((300,300,3),dtype=np.uint8)
img1[0:100,:,0]=255
img1[100:200,:,1]=255
img1[200:300,:,2]=255
cv.imshow("img1",img1)
cv.waitKey(0)
