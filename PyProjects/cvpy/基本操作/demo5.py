import cv2
import numpy as np
img = cv2.imread("./img2.jpg")
img[:, :, 0] = 0
cv2.imshow('b', img)
cv2.waitKey(0)