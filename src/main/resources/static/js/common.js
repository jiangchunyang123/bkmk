Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "S+": this.getMilliseconds()
    };

    //因位date.getFullYear()出来的结果是number类型的,所以为了让结果变成字符串型，下面有两种方法：

    if (/(y+)/.test(fmt)) {
        //第一种：利用字符串连接符“+”给date.getFullYear()+""，加一个空字符串便可以将number类型转换成字符串。
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            //第二种：使用String()类型进行强制数据类型转换String(date.getFullYear())，这种更容易理解。
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(String(o[k]).length)));
        }
    }
    return fmt;
}
Date.prototype.isToday = function () {
  return this.Format("yyyyMMdd") == new Date().Format("yyyyMMdd");
}
Date.prototype.isTomorrow = function () {
    var todayStr =  new Date().Format("yyyyMMdd");
    var dayStr = new Date().getDate();
    return this.Format("yyyyMMdd") == new Date().Format("yyyyMMdd");
}
$.put = function(url,data,callback){
    $.ajax({
        url:url,
        type:"put",
        contentType:"application/json",
        dataType:"json",
        data:data,
        timeout:20000,
        success:function(msg){
            callback(msg);
        },
        error:function(xhr,textstatus,thrown){

        }
    });
};