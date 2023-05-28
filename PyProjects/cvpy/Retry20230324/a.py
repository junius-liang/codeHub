import cv2
img1 = cv2.imread("2.png")
# cv2.imshow("img1",img1)

img2  = img1[:, :, 0]
# cv2.imshow("img2",img2)
wi, hi = img2.shape
for w in range(wi):
    for h in range(hi):
        if(img2[w][h]<200):
            img2[w][h]=200


img3  = img1[:, :, 1]
img4  = img1[:, :, 2]

bgr = cv2.merge([img2, img3, img4])
cv2.imshow("bgr",bgr)
cv2.imwrite("newlight.png",bgr)
cv2.waitKey(0)