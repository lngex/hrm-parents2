axios.interceptors.request.use(config => {

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