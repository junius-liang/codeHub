import cv2 as cv
import numpy as np
img = cv.imread("img3.jpg")
h,s,v = cv.split(img)
v[:,:]=255
newImg = cv.merge([h,s,v])
cv.imshow("img",newImg)
cv.waitKey(0)