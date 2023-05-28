from pandas import Series,DataFrame

import pandas as pd

a = [[1, 2, 3],[1, 2, 3],[1, 2, 3]]

arr1 = pd.Series(a)
# 键
print(arr1.index)
# 值
print(arr1.values)

