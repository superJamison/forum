new Vue({
    el: '#app',
    data: {
        id: 0,
        question: {}
    },
    methods: {
        getQuestionId(){
            let url = location.search; //获取url中"?"符后的字串 ('?endId=.....')
            console.log(url);
            let theRequest = new Object();
            if ( url.indexOf( "?" ) !== -1 ) {
                let str = url.substr( 1 ); //substr()方法返回从参数值开始到结束的字符串；
                let strs = str.split( "&" );
                for ( let i = 0; i < strs.length; i++ ) {
                    theRequest[ strs[ i ].split( "=" )[ 0 ] ] = ( strs[ i ].split( "=" )[ 1 ] );
                }
            }
            console.log(decodeURI(theRequest.name));
        }
    },
    created(){
        this.getQuestionId()
    }
})