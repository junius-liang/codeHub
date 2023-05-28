import cv2
import numpy as np
import matplotlib.pyplot as plt

# 图像的基本操作
## 读取图像 imread(路径,0-灰|1-彩|-1-包含alpha通道(透明度))
### a1 = cv2.imread("1.jpg",-1);

# 图像的IO操作，读取以及保存
##显示图像 imshow(窗口名称,要显示的图像)
### cv2.imshow("头像",a1)
### cv2.waitKey(0) # 等待绘图时间

## 保存图像 imwrite(文件名,要保存的图像)
### cv2.imwrite("d:\\av.jpg",a1)

# 图像绘制几何图形
## 绘制直线 line(img,start,end,color,LineWidth)
### cv2.line(a1,(0,0),(100,100),(0,255,0),3)

## 绘制圆形 circle(图像,圆心,半径,颜色,线的宽度)
### cv2.circle(a1,(100,100),20,(0,255,0),3)

## 绘制矩形 rectangle(图像,左上角坐标,右下角坐标,颜色,线的宽度)
### cv2.rectangle(a1,(100,100),(300,300),(0,255,0),3)

## 创建空白图像
### zeros(宽，高，通道数)
a2 = np.zeros((512, 512, 3), np.uint8)
a2[100, 100] = [255, 255, 255]
cv2.imshow("a",a2)
cv2.waitKey(0)

## 存放文字 putText(图像，存放内容,文本存放的位置，字体，字体大小)
###cv2.putText(a2,'ikun',(10,300),cv2.FONT_HERSHEY_COMPLEX,5,(255,255,255),3)
# cv2.imshow("a",a2)
# cv2.waitKey(0)
