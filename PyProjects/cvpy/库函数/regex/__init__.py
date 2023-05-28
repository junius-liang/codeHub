import re
'''
正则表达式：
    . 任意字符
    {数字} 前面的字符出现了多少次
    字符串前面加r 表明不转意
    () 整体执行
    + 出现一次或多次
    
'''
p = re.compile('\d+')
res = p.search('a123')
# print(res.string)

num = '134-231-321-32'
res2 = re.sub(r'\D','',num)
print(res2)