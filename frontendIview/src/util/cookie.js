const setCookie = (c_name, value, expiredays) => {
  var exdate = new Date();
  exdate.setTime(exdate.getTime() + expiredays);
  document.cookie = c_name + "=" + escape(value) + ((expiredays===null) ? "" : ";expires=" + exdate.toGMTString()) + ";path=/;domain=" + process.env.VUE_APP_DOAMIN;
}

const getCookie = (name) => {
  var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
  if (arr = document.cookie.match(reg)) {
    return (arr[2]);
  } else {
    return null;
  }
}

const delCookie = (name) => {
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval = getCookie(name);
  if (cval !== null) {
    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString() + ";path=/;domain=" + process.env.VUE_APP_DOAMIN;
  }
}

export {
  setCookie,
  getCookie,
  delCookie
}
