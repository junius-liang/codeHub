import cv2
img = cv2.imread("../../images/img_65.png")
res = cv2.Canny(img,150,300)
cv2.imshow("img",res)
cv2.waitKey(0)