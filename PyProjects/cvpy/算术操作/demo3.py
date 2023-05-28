import cv2
import numpy as np
import matplotlib.pyplot as plt
i1 = cv2.imread("img2.jpg") #600
i2 = cv2.pyrUp(i1) #1200
i3 = cv2.pyrDown(i1) #300

plt.show()