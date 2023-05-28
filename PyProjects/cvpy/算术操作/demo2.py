import cv2
import numpy as np

i1 = cv2.imread("img2.jpg")
# 获取图像的行列大小
rows, cols = i1.shape[:2]
# 创建变换矩阵
# 原来图像三点的位置
np1 = np.float32([[50, 50], [200,50],[50,200]])
# 变换后图像三点的位置
np2 = np.float32([[100, 100], [200,50],[100,250]])
M = cv2.getAffineTransform(np1,np2)
i2 = cv2.warpAffine(i1,M,(cols,rows))

cv2.imshow("image", i2)
cv2.waitKey(0)