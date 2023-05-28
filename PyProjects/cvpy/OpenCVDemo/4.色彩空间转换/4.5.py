import cv2
img1 = cv2.imread("./java.png")
size = (300, 300)
img1 = cv2.resize(img1, size)
h,s,v = cv2.split(img1)
v[:,:]=0
newImg = cv2.merge([h,s,v])
cv2.imshow("img1",newImg)
cv2.imwrite("java2.png",newImg)
cv2.waitKey(0)