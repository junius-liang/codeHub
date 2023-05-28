import cv2
# 阈值分割
img = cv2.imread("./img3.jpg",0)
cv2.imshow("img",img)
retval,dst = cv2.threshold(img,127,255,cv2.THRESH_BINARY)
retval2,dst2 = cv2.threshold(img,127,255,cv2.THRESH_BINARY_INV)
retval3,dst3 = cv2.threshold(img,127,255,cv2.THRESH_TRUNC)
retval4,dst4 = cv2.threshold(img,127,255,cv2.THRESH_TOZERO_INV)
retval5,dst5 = cv2.threshold(img,127,255,cv2.THRESH_TOZERO)

cv2.imshow("img2",dst)
cv2.imshow("img3",dst2)
cv2.imshow("img4",dst3)
cv2.imshow("img5",dst4)
cv2.imshow("img6",dst5)

cv2.waitKey(0)