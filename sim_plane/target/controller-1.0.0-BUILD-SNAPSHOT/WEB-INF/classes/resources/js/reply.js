console.log("Reply Module............");

//즉시 실행 함수
let replyService = (function(){

    function add(reply, callback){  //비동기 통신
        console.log("reply.....................");

        $.ajax({  //앞에도 따옴표가 붙으면 json 객체, 없으면 js객체
            type : 'post',
            url : '/replies/new',
            data : JSON.stringify(reply),  //reply(js객체)를 json으로 변환
            contentType : "application/json; charset=utf-8", 
            success : function(result, status, xhr) {
                if(callback) {
                    callback(result);
                }
            },
            error : function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    } //end add

    function getList(param, callback, error) {
        let bno = param.bno;
        let page = param.page || 1;  //page값 없으면 1값 입력

        $.ajax({
            type: 'get', 
            url: '/replies/pages/'+bno+"/"+page, 
            success : function(result, status, xhr) {
                if(callback) {
                    callback(result.replyCnt, result.list);
                }
            },
            error : function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }//end getList

    function remove(rno, callback, error) {

        $.ajax({
            type:'delete',
            url:'/replies/'+rno,

            success: function(deleteResult, status, xhr) {
                if(callback) {
                    callback(deleteResult);
                }
            },
            error : function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }//end remove

    function get(rno, callback, error) {
        $.ajax({
            type:'get',
            url: '/replies/'+rno,
            success: function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error) {
                    error(err);
                }
            }
        })
    }//end get

    function update(reply, callback, error) {
        $.ajax({
            type:'put',
            url:'/replies/' + reply.rno,
            data: JSON.stringify(reply),
            contentType : "application/json; charset=utf-8",
            success: function(result, status, xhr){
                if(callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, err) {
                if(error){
                    error(err);
                }
            }
        })
    }

    function displayTime(timeValue) {

        let today = new Date();
        let gap = today.getTime() - timeValue;

        let dateObj = new Date(timeValue);
        let str = "";

        if(gap < (1000*60*60*24)) {
            let hh = dateObj.getHours();
            let mi = dateObj.getMinutes();
            let ss = dateObj.getSeconds();

            return [ (hh>9 ? '' : '0')+ hh, ":",
                (mi>9 ? '':'0')+ mi, ":",
                (ss>9 ? '':'0')+ ss
                ].join('');
        }else{
            let yy = dateObj.getFullYear();
            let mm = dateObj.getMonth()+1;
            let dd = dateObj.getDate();

            return [
                yy, '/', (mm>9 ? '' : '0')+ mm, '/', (dd>9 ? '':'0')+dd
            ].join('');
        }
    }//end displayTime

    

    return {
        add : add,
        getList : getList, 
        remove : remove,
        get : get,
        update : update,
        displayTime : displayTime
    };
})();
