/**
 * Created by zhangzixu on 2017/1/3.
 * 所有需要使用JS-SDK的页面必须先注入配置信息，否则将无法调用
 * （同一个url仅需调用一次，对于变化url的SPA的web app可在每次
 * url变化时进行调用,目前Android微信客户端不支持pushState的
 * H5新特性，所以使用pushState来实现web app的页面会导致签名
 * 失败，此问题会在Android6.2中修复）。
 */
$.ajax({
    type: 'GET',
    url: '/wechat/jssdk',
    data: {url: encodeURIComponent(location.href.split('#')[0])},
    dataType: 'json',
    timeout: 3000,
    success: function (data) {
        //console.log("data.appid:" + data.appid + "    data.timestamp：" + data.timestamp + "    data.noncestr：" + data.noncestr + "    data.signature：" + data.signature);
        wx.config({
            debug: false,
            appId: data.appid,
            timestamp: data.timestamp,
            nonceStr: data.noncestr,
            signature: data.signature,
            jsApiList: [
                'checkJsApi',
                'chooseImage',
                'previewImage',
                'uploadImage',
                'downloadImage',
                'getNetworkType',
                'openLocation',
                'getLocation',
                'hideOptionMenu',
                'showOptionMenu',
                'closeWindow'
            ]
        });
    },
    error: function (xhr, type) {
        console.log("ajax error");
    }
})



