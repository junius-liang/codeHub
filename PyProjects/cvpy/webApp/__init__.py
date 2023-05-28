import json

from flask import Flask, request
import webApp
from webApp.User import User

app = Flask(__name__)

test1List = ['java', 'c', 'c++']
userMap = {}

# https://blog.csdn.net/weixin_44801979/article/details/124712309?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-124712309-blog-122275643.pc_relevant_recovery_v2&spm=1001.2101.3001.4242.2&utm_relevant_index=2

# 起步欢迎语
@app.route('/')
def hello():
    return 'Hello, World!'


# 定义路径变量
@app.route('/test1/<id>')
def test1(id):
    a = int(id)
    return test1List[a]

# 获取GET，POST方法
@app.route('/test2', methods=['GET','POST'])
def test2():
    u = User(1, 'zs', 18)
    id = int(u.uid)
    userMap[id] = u
    print(userMap)
    return json.dumps(userMap)

# 添加

# 删除

# 修改

# 查询

if __name__ == '__main__':
    app.run()
