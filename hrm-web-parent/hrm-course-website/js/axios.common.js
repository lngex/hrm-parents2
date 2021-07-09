axios.interceptors.request.use(config => {
    /*获取当前时间的时间戳*/
    var dtime = new Date().getTime();
    /* 获取过期时间 */
    var etime = $.cookie('etime');
    var time = etime-dtime
    /* 过期时间戳 */
    if( time < 0 ){
        location.href = "http://user.lngex.cn:8082/login.html"
    }
    if( 0 < time && time < 60 * 5 * 1000){
        var rf={
            rtoken:$.cookie('rtoken'),
            type:1
        }
        this.$http.post("http://127.0.0.1:1020/nb/auth/loginUser/reftoken",rf).then(res=>{
            res = res.data
            if(res.success){
                var token = res.resultObj.access_token;
                /*document.cookie = "token="+token+";Domain=.hrm.com";*/
                $.cookie('the_cookie',token,{
                    expires:1,
                    path:'/',
                    domain:'lngex.cn',
                    secure:false
                })
                time = ajaxResult.resultObj.expires_in + new Date().getTime();
                $.cookie('etime',time,{
                    expires:1,
                    path:'/',
                    domain:'lngex.cn',
                    secure:false
                })
            }
        })
    }
    var token = $.cookie('the_cookie');
    //如果已经登录了,每次都把token作为一个请求头传递过程
    if (token && token.length > 0) {

        // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
        config.headers['Authorization'] = "Bearer " + token;
    }
    return config
}, error => {
    Promise.reject(error)
})