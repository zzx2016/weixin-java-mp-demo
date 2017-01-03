/**
 * Created by zhangzixu on 2017/1/3.
 * jssdk提供的原生功能，用于index.html页面
 */
wx.ready(function () {
    console.log("wx.ready");
    // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
    document.querySelector('#checkJsApi').onclick = function () {
        wx.checkJsApi({
            jsApiList: [
                'getNetworkType',
                'previewImage'
            ],
            success: function (res) {
                alert(JSON.stringify(res));
            }
        });
    };

    // 5 图片接口
    // 5.1 拍照、本地选图
    var images = {
        localId: [],
        serverId: []
    };
    document.querySelector('#chooseImage').onclick = function () {
        wx.chooseImage({
            success: function (res) {
                images.localId = res.localIds;
                console.log('已选择 ' + res.localIds);
            }
        });
    };

    // 5.2 图片预览
    document.querySelector('#previewImage').onclick = function () {
        wx.previewImage({
            current: images.localId,
            urls: images.serverId
        });
    };

    // 5.3 上传图片
    document.querySelector('#uploadImage').onclick = function () {
        if (images.localId.length == 0) {
            console.log('请先使用 chooseImage 接口选择图片');
            return;
        }
        var i = 0, length = images.localId.length;
        images.serverId = [];
        function upload() {
            wx.uploadImage({
                localId: images.localId[i],
                success: function (res) {
                    i++;
                    //alert('已上传：' + i + '/' + length);
                    images.serverId.push(res.serverId);
                    if (i < length) {
                        upload();
                    }
                },
                fail: function (res) {
                    alert(JSON.stringify(res));
                }
            });
        }
        upload();
    };

    // 5.4 下载图片
    document.querySelector('#downloadImage').onclick = function () {
        if (images.serverId.length === 0) {
            alert('请先使用 uploadImage 上传图片');
            return;
        }
        var i = 0, length = images.serverId.length;
        images.localId = [];
        function download() {
            wx.downloadImage({
                serverId: images.serverId[i],
                success: function (res) {
                    i++;
                    alert('已下载：' + i + '/' + length);
                    images.localId.push(res.localId);
                    if (i < length) {
                        download();
                    }
                }
            });
        }

        download();
    };

    // 6 设备信息接口
    // 6.1 获取当前网络状态
    document.querySelector('#getNetworkType').onclick = function () {
        wx.getNetworkType({
            success: function (res) {
                alert(res.networkType);
            },
            fail: function (res) {
                alert(JSON.stringify(res));
            }
        });
    };

    // 7 地理位置接口
    // 7.1 查看地理位置
    document.querySelector('#openLocation').onclick = function () {
        wx.openLocation({
            latitude: 23.099994,
            longitude: 113.324520,
            name: 'TIT 创意园',
            address: '广州市海珠区新港中路 397 号',
            scale: 14,
            infoUrl: 'http://weixin.qq.com'
        });
    };

    // 7.2 获取当前地理位置
    document.querySelector('#getLocation').onclick = function () {
        wx.getLocation({
            success: function (res) {
                alert(JSON.stringify(res));
            },
            cancel: function (res) {
                alert('用户拒绝授权获取地理位置');
            }
        });
    };
});

wx.error(function (res) {
    console.log(res);
});
