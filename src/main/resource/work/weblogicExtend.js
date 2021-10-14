// 在这里，可以随便写你的代码，并且，你的代码中
// 1. 可以进行页面上的所有DOM操作
// 2. 可以访问页面上原本已挂载的所有Js变量，比如页面上已经有了jQuery，你可以直接使用
// 3. 可以依赖注入一个第三方js脚本，然后在你的代码中直接使用，如：依赖jQuery后直接使用
// 4. 好了，你的代码可以这样写：

(() => {
  // https://code.jquery.com/jquery-3.4.1.min.js
  initConstant()

  initEventLgren()

  intGenerateNode();

  if (lgren) {
    // 如果是配置数据源
    if (lgren._type === 'configDatasource') {
      const _saveBtn = $('button[name=save]:not(:disabled)');
      if (_saveBtn[0]) {
        _saveBtn.click()
      } else {
        if (lgren.step === 0) {
          opener.window.close()
          setTimeout(() => {
            lgren.step = 1; setNext(lgren); disableButtons();switchPortlet("CreateGlobalJDBCDataSource","CreateGlobalJDBCDataSourcePortlet","genericTableForm");
          }, 2000)
        } else if (lgren.step === 1) {
          $('#CreateGlobalJDBCDataSourcePortletname').val(lgren.name)
          $('#CreateGlobalJDBCDataSourcePortletjndiName').val(lgren.jndiName)

          setTimeout(() => {
            lgren.step = 2; setNext(lgren); disableButtons();nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/selectDatabaseDriver");
          }, 1000)
        } else if (lgren.step === 2) {
          setTimeout(() => {
            lgren.step = 3; setNext(lgren); disableButtons();nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/selectTransactionOptions");
          }, 1000)
        } else if (lgren.step === 3) {
          setTimeout(() => {
            lgren.step = 4; setNext(lgren); disableButtons();nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/addConnectionProperties");
          }, 1000)
        } else if (lgren.step === 4) {
          $('#CreateGlobalJDBCDataSourcePortletdatabaseName').val(lgren.dbName)
          $('#CreateGlobalJDBCDataSourcePortlethostName').val(lgren.dbIp)
          $('#CreateGlobalJDBCDataSourcePortletport').val(lgren.dbPort)
          $('#CreateGlobalJDBCDataSourcePortletdatabaseUserName').val(lgren.dbUser)
          $('#CreateGlobalJDBCDataSourcePortletpassword').val(lgren.dbPass)
          $('#CreateGlobalJDBCDataSourcePortletconfirmPassword').val(lgren.dbPass)

          setTimeout(() => {
            lgren.step = 5; setNext(lgren); disableButtons();nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/testConnection");
          }, 1000)
        } else if (lgren.step === 5) {
          setTimeout(() => {
            lgren.step = 6; setNext(lgren); disableButtons();nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/selectTargets");
          }, 1000)
        } else if (lgren.step === 6) {

          setCheckbox('[name="CreateGlobalJDBCDataSourcePortlettargetBean.chosenStandaloneServers"]', lgren.publishPort)
          // setTimeout(() => {
          //   lgren.step = 7; setNext(lgren); disableButtons();nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/save");
          // }, 1000)
        }
      }
    }
  }
})();

function initConstant() {
  window.lgren = getLast()
  window._toDataSourcePage = () => window.open($('#linkGlobalJDBCDataSourceTablePage').attr('href'))// 数据源界面按钮
}

function intGenerateNode() {
  const _div = $(`<div style=""></div>`)
  $(`body`).prepend(_div)
  _div.append(`<button class="logic">登陆</button>`)
  _div.append(`<button class="configDatasource">配置数据源</button>`)
}

function initEventLgren() {
  const _html = $(`html`);
  _html
    // 登陆
    .on(`click`, `.logic`, e => {
      if ($('#j_username')[0]) {
        $('#j_username').val(`weblogic`)
        $('#j_password').val(`cdgllwzz#2403`)
        $('.formButton').click()
      } else {
        console.log(`已登录`)
      }
    })
    // 配置数据源
    .on(`click`, `.configDatasource`, e => {
      const x = e.clientX + 10
      const y = e.clientY + 10
      let _div = $('.configDatasourceWin')
      // 如果不存在
      if (!_div[0]) {
        _div = $(`<form class="configDatasourceWin" style="position: fixed; left: ${x}px; top: ${y}px; width: 400px; background-color: #339cce;">
          <div style="display: flex;"><span style="width: 100px;">显示名称</span><input type="text" name="name" style="flex: 1;"></div>
          <div style="display: flex;"><span style="width: 100px;">jndi名称</span><input type="text" name="jndiName" style="flex: 1;"></div>
          <div style="display: flex;"><span style="width: 100px;">数据库名称</span><input type="text" name="dbName" style="flex: 1;"></div>
          <div style="display: flex;"><span style="width: 100px;">ip</span><input type="text" name="dbIp" style="flex: 1;"></div>
          <div style="display: flex;"><span style="width: 100px;">端口</span><input type="text" name="dbPort" style="flex: 1;"></div>
          <div style="display: flex;"><span style="width: 100px;">用户名</span><input type="text" name="dbUser" style="flex: 1;"></div>
          <div style="display: flex;"><span style="width: 100px;">密码</span><input type="text" name="dbPass" style="flex: 1;"></div>
          <div style="display: flex;"><span style="width: 100px;">发布端口</span><div>
            <div><label>8003: <input type="checkbox" name="publishPort" value="8003"></label></div>
            <div><label>8005: <input type="checkbox" name="publishPort" value="8005"></label></div>
            <div><label>8007: <input type="checkbox" name="publishPort" value="8007"></label></div>
            <div><label>8009: <input type="checkbox" name="publishPort" value="8009"></label></div>
            <div><label>8011: <input type="checkbox" name="publishPort" value="8011"></label></div>
            <div><label>8013: <input type="checkbox" name="publishPort" value="8013"></label></div>
          </div></div>
          <div>
            <button class="okBtn">确定</button><button class="closeBtn">关闭</button>
          </div></form>`);
        _html.append(_div)
      }
      setFormVal('.configDatasourceWin', lgren)
    })
    .on(`click`, `.configDatasourceWin .okBtn`, e => {
      const val = getFormVal('.configDatasourceWin')
      setNext(val)
      window._toDataSourcePage()
    })
    .on(`click`, `.configDatasourceWin .closeBtn`, e => {
      $('.configDatasourceWin').remove()
    })
}

function setFormVal(formSelector, val={}) {
  $(formSelector).find('input').each((i,o) => {
    let v = val[o.name];
    if (v) {
      if (o.type === 'checkbox') {
        if (v.split(',').indexOf(o.value) > -1) {
          o.checked = true
        }
      } else {
        o.value = v;
      }
    }
  })
}

function setCheckbox(selector, val='') {
  const vArr = val.split(',');
  $(selector).each((i, o) => {
    if (vArr.filter(s => o.value.indexOf(s) > -1).length > 0) {
      o.checked = true
    }
  });
}

function getFormVal(formSelector) {
  return $(formSelector).serializeArray().reduce((r, o) => {
    r[o.name] = !r[o.name] ? o.value : (r[o.name] + ',' + o.value);
    return r
  }, ({_type: 'configDatasource', step: 0,}))

}

function setNext(val) {
  setLocal('lgren', {last: true, val})
}

function setLocal(key, val={}) {
  localStorage.setItem(key, JSON.stringify(val))
}

function getLast() {
  let val = localStorage.getItem('lgren');
  if (val) {
    val = JSON.parse(val)
    if (val.last) {
      localStorage.setItem('lgren', JSON.stringify({last: false, val: val.val}))
      return val.val
    }
  }
  return undefined
}
