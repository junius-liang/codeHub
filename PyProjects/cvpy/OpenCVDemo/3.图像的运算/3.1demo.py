import cv2
import numpy
img1 = cv2.imread("./3.jpg")
img2 = numpy.zeros((640,640,3),dtype=numpy.uint8)
img2[:,100:250] = 255
img3 = cv2.bitwise_and(img1,img2)
cv2.imshow("img3",img3)
cv2.waitKey(0)