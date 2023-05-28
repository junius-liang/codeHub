import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt

# 封装图片显示函数
def image_show(image):
    if image.ndim == 2:
        plt.imshow(img_desk)
    else:
        image = cv.cvtColor(image, cv.COLOR_BGR2RGB)
        plt.imshow(image)
    plt.xticks([])
    plt.yticks([])
    plt.show()
    plt.savefig('./nnn.png',  # ⽂件名：png、jpg、pdf
                dpi=100,  # 保存图⽚像素密度
                bbox_inches='tight')  # 保存图⽚完整


# 巴特沃斯滤波
def High_filter(N, M, D0, n):
    x, y = np.meshgrid(np.arange(M) - M // 2, np.arange(N) - N // 2)
    D = np.sqrt(x ** 2 + y ** 2)
    H = 1 / (1 + (D0 / (D + 1e-5)) ** (2 * n))
    return H


if __name__ == '__main__':
    # 读取图像
    img_desk = cv.imread('img_63.png')
    img_desk = cv.cvtColor(img_desk, cv.COLOR_BGR2GRAY)

    # 参数设置
    D0 = 20  # 滤波器参数1
    n = 4  # 滤波器参数2
    M, N = img_desk.shape

    # 二维离散傅里叶变换
    desk_fft = np.fft.fft2(img_desk)
    desk_ffts = np.fft.fftshift(desk_fft)

    # 高通滤波变换
    desk_low = High_filter(M, N, D0, n)
    desk_HF = desk_low * desk_ffts

    # 傅里叶反变换
    img_desk_rec = np.fft.ifft2(desk_HF)

    # plt.imshow(np.abs(img_desk_rec), cmap='gray')
    # plt.show()

    # 显示结果
    image_show(np.abs(img_desk_rec))

