import numpy as np
import matplotlib.pyplot as plt
import cv2 as cv
image = cv.imread('img_63.png')
# plt.imshow(image[:,:,::-1])

image2 = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
plt.imshow(image2)
plt.show()
