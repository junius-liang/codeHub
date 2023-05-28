import cv2 as cv
img = cv.imread("./img3.png")
a = img[11:358,10:360]
cv.imshow("1",a)
cv.waitKey(0)