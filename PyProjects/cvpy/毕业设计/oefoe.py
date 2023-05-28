import cv2
import numpy as np
def resizeImage(img):
    size = (300, 300)
    img = cv2.resize(img, size)
    return img

# 读取原图像
img1 = cv2.imread("./2.png",0)
img1 = resizeImage(img1)
cv2.imshow("img1", img1)
img2 = np.zeros((300,300),dtype=np.uint8)
img2[100:200,50:100] = 255;
cv2.imshow("img2", img2)
a1 = cv2.bitwise_and(img1, img2)
cv2.imshow("a1", a1)
cv2.waitKey(0)