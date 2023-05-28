import cv2 as cv
import numpy as np
a = cv.imread("./1.jpg")

# 通道的拆分
r,g,b = cv.split(a)



