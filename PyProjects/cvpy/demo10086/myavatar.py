import cv2
pig = cv2.imread("245.png")
size = (300, 300)
res = cv2.resize(pig, size)
cv2.imshow("pig",res)
b = res[:, :, 0]
g = res[:, :, 1]
r = res[:, :, 2]
cv2.imshow("b通道合并图像", b)
cv2.imshow("g通道合并图像", g)
cv2.imshow("r通道合并图像", r)
bgr = cv2.merge([b, g, r])
rgb = cv2.merge([r, g, b])
cv2.imshow("bgr通道合并图像", bgr)
cv2.imshow("rgb通道合并图像", rgb)

cv2.waitKey(0)