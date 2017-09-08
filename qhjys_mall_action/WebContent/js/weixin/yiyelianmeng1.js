window.onload=function(){
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${appId}', // 必填，公众号的唯一标识
        timestamp:'${datetime}', // 必填，生成签名的时间戳
        nonceStr: '${nonceStr}', // 必填，生成签名的随机串
        signature: '${sha}',// 必填，签名，见附录1
        jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    $(function(){
        var rotateFunc = function(awards,angle,text,cfg){  //awards:奖项，angle:奖项对应的角度
            $('#lotteryBtn').stopRotate();
            $("#lotteryBtn").rotate({
                angle:0,
                duration: 5000,
                animateTo: angle+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
                callback:function(){
                    if(cfg=="2"){
                        $("#zhongjiang h1").html("【折扣劵】"+text);
                        $("#zhongjiang p > img").attr("src","/images/get-pic3.png");

                        $("#mashangduihuan .title span").html(text);
                        $("#mashangduihuan #img").attr("src","/images/get-pic3.png");
                        $("#zjtbdiv").html("恭喜您获得折扣劵<p>【"+text+"】</p>")
                    }else if(cfg=="1"){
                        $("#zhongjiang h1").html("【代金券】"+text);
                        $("#zhongjiang p > img").attr("src","/images/get-pic2.png");

                        $("#mashangduihuan .title span").html(text);
                        $("#mashangduihuan #img").attr("src","/images/get-pic2.png");
                        $("#zjtbdiv").html("恭喜您获得代金券<p>【"+text+"】</p>")
                    }else if(cfg=="0"){
                        $("#zhongjiang h1").html("【礼品券】"+text);
                        $("#zhongjiang p > img").attr("src","/images/get-pic1.png");

                        $("#mashangduihuan .title span").html(text);
                        $("#mashangduihuan #img").attr("src","/images/get-pic1.png");
                        $("#zjtbdiv").html("恭喜您获得礼品券<p>【"+text+"】</p>")
                    }
                    document.getElementById('zhongjiang').style.display='block';
                    document.getElementById('fade').style.display='block';
                }
            });
        };
        $("#lotteryBtn").rotate({
            bind:
            {
                tap: function(){
                    var userLotteryId = $("input[name='userLotteryId']").val();
                    var statusCfg = $("input[name='statusCfg']").val();
                    var sellerId = $("input[name='sellerId']").val();
                    var orderId = $("input[name='orderId']").val();
                    var forOpenId = $("input[name='forOpenId']").val();
                    $.ajax({
                        type: 'GET',
                        url: '/wxMall/lottery/getCardCoupon.do',
                        data: {statusCfg:statusCfg,sellerId:sellerId,userLotteryId:userLotteryId,orderId:orderId,forOpenId:forOpenId},
                        dataType: 'json',
                        success: function(obj){
                            if(obj.flag==0||obj.flag=="0"){
                                $("input[name='id']").val(obj.data.cardTemplateId);
                                if(obj.data.index=="0"){
                                    rotateFunc(3,300,obj.data.name,obj.data.couponCfg);
                                }else if(obj.data.index=="1"){
                                    rotateFunc(0,22,obj.data.name,obj.data.couponCfg);
                                }else if(obj.data.index=="2"){
                                    rotateFunc(2,247,obj.data.name,obj.data.couponCfg);
                                }else if(obj.data.index=="3"){
                                    rotateFunc(1,157,obj.data.name,obj.data.couponCfg);
                                }
                            }else if(obj.flag==2||obj.flag=="2"){
                                //已抽过奖
                                if(obj.data.statusCfg=="0"||obj.data.statusCfg==0){
                                    $("#yichouguo #duihuan_btn").show();
                                }else{
                                    $("#yichouguo #duihuan_btn").hide();
                                }
                                $("input[name='id']").val(obj.data.cardTemplateId);
                                document.getElementById('yichouguo').style.display='block';
                                document.getElementById('fade').style.display='block';
                            }else{
                                alert(obj.reason);
                            }
                        },
                        error: function(xhr, type){
                            $("#lotteryBtn").rotate({
                                angle:0,
                                duration: 10000,
                                animateTo: 2160, //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，2160是因为我要让它转6圈，就是360*6得来的
                                callback:function(){
                                    alert('亲！没有中奖哦！！')
                                }
                            });
                        }
                    });
                }
            }

        });

    });
    function doExchange(hiDivId){
        var cardTemplateId = $("input[name='id']").val();
        var statusCfg = $("input[name='statusCfg']").val();
        var userLotteryId = $("input[name='userLotteryId']").val();
        var isguanzhu = $("input[name='isguanzhu']").val();
        $.ajax({
            type: 'GET',
            url: '/wxMall/receiveCardCoupon.do',
            data: {id:cardTemplateId,source:statusCfg,userLotteryId:userLotteryId},
            dataType: 'json',
            success: function(obj){
                if(obj.flag==0||obj.flag=="0"){
                    document.getElementById(hiDivId).style.display='none';

                    if(isguanzhu=="1"||isguanzhu==1){
                        document.getElementById('mashangduihuan').style.display='block';
                    }else{
                        document.getElementById('weiguanzhu').style.display='block';
                    }

                    document.getElementById('fade').style.display='block';
                }else{
                    alert(obj.reason);
                }
            },
            error: function(xhr, type){
                alert("请求网络失败!");
            }
        });
    }
    function duihuan(name,templateId,couponCfg,lotteryId,forOpenId){
        var statusCfg = $("input[name='statusCfg']").val();
        $.ajax({
            type: 'GET',
            url: '/wxMall/receiveCardCoupon.do',
            data: {id:templateId,source:statusCfg,userLotteryId:lotteryId,isFriend:"1",forOpenId:forOpenId},
            dataType: 'json',
            success: function(obj){
                if(obj.flag==0||obj.flag=="0"){
                    if(couponCfg=="2"){
                        $("#duihuan .title span").html(name);
                        $("#duihuan #img").attr("src","/images/weixin/cardcoupon/get-pic3.png");
                    }else if(couponCfg=="1"){
                        $("#duihuan .title span").html(name);
                        $("#duihuan #img").attr("src","/images/weixin/cardcoupon/get-pic2.png");
                    }else if(couponCfg=="0"){
                        $("#duihuan .title span").html(name);
                        $("#duihuan #img").attr("src","/images/weixin/cardcoupon/get-pic1.png");
                    }
                    document.getElementById('duihuan').style.display='block';
                    document.getElementById('fade').style.display='block';
                }else{
                    alert(obj.reason);
                }
            },
            error: function(xhr, type){
                alert("请求网络失败!");
            }
        })
    }
};
wx.ready(function () {
    wx.checkJsApi({
        jsApiList: [
            'onMenuShareTimeline','onMenuShareAppMessage'
        ]
    });

    wx.onMenuShareTimeline({
        title: '我运气不够，帮我拿下好吃的就看你的了！', // 分享标题
        link: 'http://jysmall.wjznz.com/wxMall/jump/getCardCoupon.do?flag=${statusCfg }&sellerId=${sellerId}&orderId=${orderId}&forOpenId=${selfOpenId}', // 分享链接
        imgUrl: '${ul.portrait}', // 分享图标
        success: function () {
            var orderId = $("input[name='orderId']").val();
            $.ajax({
                type: 'GET',
                url: 'wxMall/updateCardCouponShareCount.do',
                data: {orderId:orderId},
                dataType: 'json',
                success: function(obj){
                },
                error: function(xhr, type){
                }
            });
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    wx.onMenuShareAppMessage({
        title: '我运气不够，帮我拿下好吃的就看你的了！', // 分享标题
        desc: '听说运气好的能拿好吃的哟！', // 分享描述
        link: 'http://jysmall.wjznz.com/wxMall/jump/getCardCoupon.do?flag=${statusCfg }&sellerId=${sellerId}&orderId=${orderId}&forOpenId=${selfOpenId}', // 分享链接
        imgUrl: '${ul.portrait}', // 分享图标
        type: '', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            var orderId = $("input[name='orderId']").val();
            $.ajax({
                type: 'GET',
                url: 'wxMall/updateCardCouponShareCount.do',
                data: {orderId:orderId},
                dataType: 'json',
                success: function(obj){
                },
                error: function(xhr, type){
                }
            });
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
});