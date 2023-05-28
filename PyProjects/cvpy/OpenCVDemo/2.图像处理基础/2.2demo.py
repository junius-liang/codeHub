import cv2
img = cv2.imread("./img1.jpg")
cv2.imshow("before", img)
for i in range(200,400):
    for j in range(250,350):
        img[i, j]=255
cv2.imshow("after", img)
cv2.waitKey()
cv2.destroyAllWindows()