import pandas as pd

arr = [3, 4, 6, 7]
ind = ["a", "b", "c", "d"]
res1 = pd.Series(arr, index=ind)

data = {
    "city": ["北京", "上海", "广州", "深圳"],
    "number": [1290, 2938, 3922, 902],
    "address": ["华北", "华东", "华南", "华南"]
}
res2 = pd.DataFrame(data)

# 排序 按照number排序
res3 = pd.DataFrame(data,columns=["number","city","address"])
print(res3)