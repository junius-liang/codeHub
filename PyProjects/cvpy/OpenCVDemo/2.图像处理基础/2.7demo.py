# 通过索引拆分通道
import cv2
img =  cv2.imread("img3.png")
b = img[:,:,0]
g = img[:,:,1]
r = img[:,:,2]

# 通过函数拆分
b,g,r = cv2.split(img)

# cv2.imshow("img",img)
# cv2.imshow("img_b",b)
# cv2.imshow("img_g",g)
# cv2.imshow("img_r",r)

a1 = cv2.merge([b,g,r])
a2 = cv2.merge([r,g,b])
a3 = cv2.merge([b,r,b])
cv2.imshow("img_b",a1)
cv2.imshow("img_g",a2)
cv2.imshow("img_r",a3)


cv2.waitKey(0)
