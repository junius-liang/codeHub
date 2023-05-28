import cv2
def resizeImage(img):
    size = (300, 300)
    img = cv2.resize(img, size)
    return img

if __name__ == '__main__':
    img1 = cv2.imread("img_63.png")
    img1 = resizeImage(img1)
    cv2.imshow("灰色图像",img1)
    cv2.waitKey(0)