import cv2
import numpy as np
img_blue = np.zeros([1,1,3],dtype=np.uint8)
img_blue[0,0,1]=255
blue = img_blue
blue_hsv = cv2.cvtColor(blue,cv2.COLOR_BGR2HSV)
print(blue)
print(blue_hsv)