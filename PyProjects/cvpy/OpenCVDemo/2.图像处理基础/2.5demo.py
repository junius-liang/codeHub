import cv2 as cv
import numpy as np
img = np.random.randint(0,255,size=[300,300,3],dtype=np.uint8)
cv.imshow("1",img)
cv.waitKey(0)