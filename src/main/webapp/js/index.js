/**
 * Created by zhangzixu on 2017/1/3.
 * jssdk提供的原生功能，用于index.html页面
 */
wx.ready(function () {

    /* 图片自动上传 */
    var uploadCount = 0;
    var uploadCountDom = document.getElementById("uploadCount");
    weui.uploader('#uploader', {
        url: 'http://localhost:8002/upload',
        auto: false,
        type: 'file',
        fileVal: 'fileVal',
        compress: {
            width: 1600,
            height: 1600,
            quality: .8
        },
        onBeforeQueued: function (files) {
            if (["image/jpg", "image/jpeg", "image/png", "image/gif"].indexOf(this.type) < 0) {
                weui.alert('请上传图片');
                return false;
            }
            if (this.size > 10 * 1024 * 1024) {
                weui.alert('请上传不超过10M的图片');
                return false;
            }
            if (files.length > 9) { // 防止一下子选中过多文件
                weui.alert('最多只能上传9张图片，请重新选择');
                return false;
            }
            if (uploadCount + 1 > 9) {
                weui.alert('最多只能上传9张图片');
                return false;
            }

            ++uploadCount;
            uploadCountDom.innerHTML = uploadCount;
        },
        onQueued: function () {
            console.log(this);
        },
        onBeforeSend: function (data, headers) {
            console.log(this, data, headers);
            // $.extend(data, { test: 1 }); // 可以扩展此对象来控制上传参数
            // $.extend(headers, { Origin: 'http://127.0.0.1' }); // 可以扩展此对象来控制上传头部

            // return false; // 阻止文件上传
        },
        onProgress: function (procent) {
            console.log(this, procent);
        },
        onSuccess: function (ret) {
            console.log(this, ret);
        },
        onError: function (err) {
            console.log(this, err);
        }
    });

    // 缩略图预览
    $('#uploaderFiles').on('click', function (e) {
        var target = e.target;

        while (!target.classList.contains('weui-uploader__file') && target) {
            target = target.parentNode;
        }
        if (!target) return;

        var url = target.getAttribute('style') || '';
        var id = target.getAttribute('data-id');

        if (url) {
            url = url.match(/url\((.*?)\)/)[1];
        }
        var gallery = weui.gallery(url, {
            onDelete: function () {
                weui.confirm('确定删除该图片？', function () {
                    var index;
                    for (var i = 0, len = uploadCustomFileList.length; i < len; ++i) {
                        var file = uploadCustomFileList[i];
                        if (file.id == id) {
                            index = i;
                            break;
                        }
                    }
                    if (index) uploadCustomFileList.splice(index, 1);

                    target.remove();
                    gallery.hide();
                });
            }
        });
    });

    //console.log("wx.ready");
    // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
    /*document.querySelector('#checkJsApi').onclick = function () {
        wx.checkJsApi({
            jsApiList: [
                'getNetworkType',
                'previewImage'
            ],
            success: function (res) {
                alert(JSON.stringify(res));
            }
        });
     };*/

    // 5 图片接口
    // 5.1 拍照、本地选图
    /*var images = {
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
     };*/

    // 5.2 图片预览
    /*document.querySelector('#previewImage').onclick = function () {
        console.log("localId" + images.localId);
        wx.previewImage({
            current: images.serverId,
            urls: images.serverId
        });
     };*/

    // 5.3 上传图片
    /*document.querySelector('#uploadImage').onclick = function () {
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
                    console.log("serverId" + images.serverId);
                    images.serverId.push(res.serverId);
                    if (i < length) {
                        upload();
                    }
                },
                fail: function (res) {
                    alert("上传图片失败" + JSON.stringify(res));
                }
            });
        }
        upload();
     };*/

    // 5.4 下载图片
    /*document.querySelector('#downloadImage').onclick = function () {
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
     };*/

    // 6 设备信息接口
    // 6.1 获取当前网络状态
    /*document.querySelector('#getNetworkType').onclick = function () {
        wx.getNetworkType({
            success: function (res) {
                alert(res.networkType);
            },
            fail: function (res) {
                alert(JSON.stringify(res));
            }
        });
     };*/

    // 7 地理位置接口
    // 7.1 查看地理位置
    /*document.querySelector('#openLocation').onclick = function () {
        wx.openLocation({
            latitude: 23.099994,
            longitude: 113.324520,
            name: 'TIT 创意园',
            address: '广州市海珠区新港中路 397 号',
            scale: 14,
            infoUrl: 'http://weixin.qq.com'
        });
     };*/

    // 7.2 获取当前地理位置
    /*document.querySelector('#getLocation').onclick = function () {
        wx.getLocation({
            success: function (res) {
                alert(JSON.stringify(res));
            },
            cancel: function (res) {
                alert('用户拒绝授权获取地理位置');
            }
        });
     };*/
});

wx.error(function (res) {
    console.log(res);
});
