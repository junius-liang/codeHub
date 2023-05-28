import cv2

grayImg = cv2.imread("img3.png", 1);
color_img = cv2.cvtColor(grayImg,cv2.COLOR_BGR2RGB)
cv2.imshow("image", color_img)
cv2.waitKey(0)
