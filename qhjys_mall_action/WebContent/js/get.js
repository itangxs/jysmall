/**
 * Created by Administrator on 2017/6/12.
 */
//$(document).ready(function(){
//    $(".active-title").toggle(function(){
//        $(this).next(".active-content").animate({height: 'toggle', opacity: 'toggle'}, "slow");
//    },function(){
//        $(this).next(".active-content").animate({height: 'toggle', opacity: 'toggle'}, "slow");
//    });
//});


$(function(){
//    var timeOut = function(){  //��ʱ����
//        $("#lotteryBtn").rotate({
//            angle:0,
//            duration: 10000,
//            animateTo: 2160, //��������������ʱ�󷵻صĽǶȣ�����Ӧ�û��ǻص���ԭʼ��λ�ã�2160����Ϊ��Ҫ����ת6Ȧ������360*6������
//            callback:function(){
//                alert('�ף�û���н�Ŷ����')
//            }
//        });
//    };
    var rotateFunc = function(awards,angle,text){  //awards:���angle:�����Ӧ�ĽǶ�
        $('#lotteryBtn').stopRotate();
        $("#lotteryBtn").rotate({
            angle:0,
            duration: 8000,
            animateTo: angle+3960, //angle��ͼƬ�ϸ������Ӧ�ĽǶȣ�1440����Ҫ��ָ����ת4Ȧ���������Ľ����ĽǶȾ���������^^
            callback:function(){
                //alert(text)
                $("#zhongjiang").fadeIn();
                //setTimeout("top.location.href='http://fuwu.taobao.com/ser/assembleParam.htm?spm=a1z2j.6989585.0.0.o9dyAn&tracelog=qianniu&subParams=itemCode:FW_GOODS-1884032-1,cycleNum:7,cycleUnit:3,freeTry:1;'",5000);
            }
        });
    };

    $("#lotteryBtn").rotate({
        bind:
        {
            click: function(){
                var time = [1];
                time = time[Math.floor(Math.random()*time.length)];
                if(time==0){
                    timeOut(); //���糬ʱ
                }
                if(time==1){
                    var data = [1,2,3,4]; //���ص�����
                    data = data[Math.floor(Math.random()*data.length)];
                    if(data==1){
                        rotateFunc(1,157,'��ϲ�����е�һ�Ƚ�')
                    }
                    if(data==2){
                        rotateFunc(2,247,'��ϲ�����е�һ�Ƚ�')
                    }
                    if(data==3){
                        rotateFunc(3,22,'��ϲ�����е�һ�Ƚ�')
                    }
                    if(data==4){
                        rotateFunc(4,300,'��ϲ�����е�һ�Ƚ�')
                    }
                    /*if(data==0){
                     var angle = [67,112,202,292,337];
                     angle = angle[Math.floor(Math.random()*angle.length)]
                     rotateFunc(0,angle,'���ź��������δ���н�')
                     }*/
                }
            }
        }

    });

});
