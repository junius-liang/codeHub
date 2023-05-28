import cv2
# 去掉珊格线 2023/02/03
a2 = cv2.imread("a2.jpg",0)
a3 = cv2.imread("a2.jpg")
retval,dst = cv2.threshold(a2,29,255,cv2.THRESH_BINARY)
wi,hi = dst.shape
for w in range(wi):
    for h in range(hi):
        if dst[w][h]==255:
            a3[w][h]=0
cv2.imshow("a2",a3)
cv2.imshow("dst",dst)
cv2.imwrite("a3.jpg",a3)
cv2.waitKey(0)