/** 判断为空 */
function isEmpty(value) {
    return value === undefined || value === null || value === 'null' || value === 'undefined' || value.length === 0;
}

/** 判断非空 */
function isNotEmpty(value) {
    return value !== undefined && value !== null && value !== 'null' && value !== 'undefined' && value.length !== 0;
}

/** 先解除 在添加 */
function onOnly($node, types, selectorOrFn, fn) {
    if (!$.isFunction(selectorOrFn)) {
        $node.off(types, selectorOrFn).on(types, selectorOrFn, fn);
    } else {
        $node.off(types).on(types, selectorOrFn);
    }
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 */
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
};

function getUrlDefaultIcon(url) {
    var httpIndex = -1;
    if (url.indexOf('http://') > -1) {
        httpIndex = 7;
    }
    if (url.indexOf('https://') > -1) {
        httpIndex = 8;
    }
    if (httpIndex > -1) {
        var head = url.substr(0, httpIndex);
        var ipAndPostAndParam = url.substr(httpIndex);
        var s_index = ipAndPostAndParam.indexOf('/');
        // var ipAndPost = ipAndPostAndParam.substr(0, (s_index > -1 ? s_index : (ipAndPostAndParam.length - 1)));
        return url.substr(0, httpIndex + s_index) + '/favicon.ico';

        // var m_index = ipAndPost.indexOf(':');
        // var ip = ipAndPost.substr(0, (m_index > -1 ? m_index : (ipAndPost.length - 1)));
        // var post = m_index > -1 ? ipAndPost.substr(m_index) : null;
    }
    return "";

}

/**
 * 一个form提交
 * 参数类似
 * formSubmit({url: '', inputArr: [{name: 'name1', value: 'value1'},],});
 * @param config
 * @param config.url
 * @param config.method
 * @param config.target
 * @param config.data
 */
function formSubmit(config) {
    config = config || {};
    var method = config.method || 'POST';
    var url = config.url;
    if (!url) throw 'url为空';
    var $form = $("<form></form>");
    $form.attr("style", "display:none");
    if (config.data) {
        $.each(config.data, function (k, v) {
            var $input = $("<input/>");
            $input.attr("type", "hidden");
            $input.attr("name", k);
            $input.attr("value", v);
            $form.append($input);
        });
    }
    url && $form.attr('action', url);
    $form.attr("method", method);
    config.target && $form.attr("target", config.target);
    $("body").append($form);
    $form.submit();
    $form.remove();
}

// string转dom节点
function parseDom(str){
    var div = document.createElement("div");
    div.innerHTML = str;
    return div.childNodes[0];
}

// 图片转Base64
function getBase64 (img, callback) {
    const reader = new FileReader()
    reader.addEventListener('load', () => callback(reader.result))
    reader.readAsDataURL(img)
}

/**
 * 一个form提交
 * 参数类似
 * formSubmit({url: 'url', data: {'test': 1,},});
 * @param url 地址
 * @param method 方法
 * @param target 打开方式
 * @param data 数据
 */
function formSubmit1 ({ method = 'POST', url, target, data }) {
    if (!url) { throw Error('url为空') }
    const form = document.createElement('form')
    form.action = url
    form.method = method
    form.style.display = 'none'
    target && (form.target = target)
    if (data) {
        for (const k in data) {
            if (Object.prototype.hasOwnProperty.call(data, k)) {
                const v = data[k]
                const input = document.createElement('input')
                input.type = 'hidden'
                input.name = k
                input.value = v
                form.appendChild(input)
            }
        }
    }
    document.getElementsByTagName('body')[0].appendChild(form)
    form.submit()
    form.remove()
}
