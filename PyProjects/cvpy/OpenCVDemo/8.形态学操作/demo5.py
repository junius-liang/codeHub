import cv2 as cv
a = cv.imread("a3.jpg")
# a2  = cv.blur(a,(3,3))
r1 = cv.medianBlur(a,5)
blueChannel = r1[:,:,0]
cv.imshow("r1",r1)
# 计算灰度均值
w,h = blueChannel.shape
num = 0
sum = 0
for i in range(w):
    for j in range(h):
       if(blueChannel[i][j]!=255):
           sum += blueChannel[i][j]
           num = num+1
print(sum/num)
cv.waitKey(0)