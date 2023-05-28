import cv2 as cv
import numpy as np
a = cv.imread("./1.jpg")

# b = cv.cvtColor(a,cv.COLOR_BGR2GRAY)
b = cv.cvtColor(a,cv.COLOR_BGR2HSV)
cv.imshow("img",b)
cv.waitKey(0)

