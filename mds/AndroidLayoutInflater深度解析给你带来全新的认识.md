
[LayoutInflater深度解析](http://blog.csdn.net/lmj623565791/article/details/38171465)

    Inflate(resId , null ) 只创建temp ,返回temp
    Inflate(resId , parent, false )创建temp，然后执行temp.setLayoutParams(params);返回temp
    Inflate(resId , parent, true ) 创建temp，然后执行root.addView(temp, params);最后返回root