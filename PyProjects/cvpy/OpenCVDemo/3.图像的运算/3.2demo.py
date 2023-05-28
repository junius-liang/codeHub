# 掩膜
import cv2 as cv
import numpy as np
img1 = np.ones([4,4],dtype=np.uint8)*3
img2 = np.ones([4,4],dtype=np.uint8)*5
mask = np.zeros([4,4],dtype=np.uint8)
mask[2:4,2:4] = 1
img3 = cv.add(img1,img2,mask=mask)
print(img3)