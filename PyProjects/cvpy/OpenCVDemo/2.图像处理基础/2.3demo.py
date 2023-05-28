import cv2 as cv
import numpy as np

# 蓝色通道
blue_img = np.zeros((300,300,3),dtype=np.uint8)
blue_img[:, :, 0] = 255 # 行：列：通道数-0是b通道，1是g通道，2是r通道
print("blue=\n",blue_img)
cv.imshow("blue",blue_img)

green_img = np.zeros((300,300,3), dtype= np.uint8)
green_img[:,:,1] = 255
print("green=\n",green_img)
cv.imshow("green",green_img)

red_img = np.zeros((300,300,3), dtype= np.uint8)
red_img[:,:,2] = 255
print("red=\n",red_img)
cv.imshow("red",red_img)

cv.waitKey(0)
