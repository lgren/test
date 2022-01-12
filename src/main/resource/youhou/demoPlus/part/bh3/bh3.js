const BH3Common = {
  key: 0,
  getKey() {
    return this.key++
  }
}

class AttributeBase {
  /**
   *
   * @param type {number} 类型
   */
  constructor(type) {
    this.id = BH3Common.getKey()

    this.type = type;
  }

  /**
   *
   * @return {string}
   */
  getTypeStr() {
    return AttributeDO.TYPE_STR[this.type]
  }

  /**
   *
   * @return {number}
   */
  getOrder() {
    return AttributeDO.TYPE_SHOW_ORDER[this.type]
  }
}

/** 属性 */
class AttributeDO extends AttributeBase {
  /**
   *
   * @param type {AttributeDO.TYPE} 类型
   * @param data {number} 数值
   * @param time {number} 加成时间
   * @param desc {string} 条件说明
   */
  constructor(type, data, time = -1, desc = undefined) {
    super(type);
    this.id = BH3Common.getKey()

    this.data = data;
    this.time = time;
    this.desc = desc;
  }
}

/** @type {Object<string, number>} */
AttributeDO.TYPE = {
  其他: 0,
  数值: {
    攻击: 0b1,// 攻击
    会心: 0x2,// 会心
    生命: 0x4,// 生命
    防御: 0x8,// 防御
  },
  百分比数值: {
    物理: 0x10,// 物理
    冰伤: 0x20,// 冰伤
    火伤: 0x40,// 火伤
    雷伤: 0x80,// 雷伤
  },
  加成: {
    全伤: 0x100,// 全伤
    物理: 0x200,// 物理
    冰伤: 0x400,// 冰伤
    火伤: 0x800,// 火伤
    雷伤: 0x1000,// 雷伤
  },
  易伤: {
    全伤: 0x2000,// 全伤
    物理: 0x4000,// 物理
    冰伤: 0x8000,// 冰伤
    火伤: 0x10000,// 火伤
    雷伤: 0x20000,// 雷伤
  }
}
/** @type {Object<number, string>} */
AttributeDO.TYPE_STR = {
  0: '其他',
  0b1: '攻击数值',
  0x2: '会心数值',
  0x4: '生命数值',
  0x8: '防御数值',
  0x10: '物理%',
  0x20: '冰伤%',
  0x40: '火伤%',
  0x80: '雷伤%',
  0x100: '全伤(加成)',
  0x200: '物理(加成)',
  0x400: '冰伤(加成)',
  0x800: '火伤(加成)',
  0x1000: '雷伤(加成)',
  0x2000: '全伤(易伤)',
  0x4000: '物理(易伤)',
  0x8000: '冰伤(易伤)',
  0x10000: '火伤(易伤)',
  0x20000: '雷伤(易伤)',
}
/** @type {Object<number, number>} */
AttributeDO.TYPE_SHOW_ORDER = {
  0: 9999,
  0b1: 1,
  0x2: 4,
  0x4: 2,
  0x8: 3,
  0x10: 23,
  0x20: 33,
  0x40: 43,
  0x80: 53,
  0x100: 11,
  0x200: 21,
  0x400: 31,
  0x800: 41,
  0x1000: 51,
  0x2000: 12,
  0x4000: 22,
  0x8000: 32,
  0x10000: 42,
  0x20000: 52,
}

/** 装备 */
class Equip {
  /**
   *
   * @param suit {SuitDO} 套装
   * @param name {string} 名字
   * @param type {Equip.TYPE} 类型
   * @param pos {Equip.POS} 位置
   * @param sm {int} 生命
   * @param gj {int} 攻击
   * @param fy {int} 防御
   * @param hx {int} 会心
   * @param imgUrl {string} 图片url
   */
  constructor(suit, name, type, pos, sm = 0, gj = 0, fy = 0, hx = 0, imgUrl = undefined) {
    this.id = BH3Common.getKey()

    this.name = name;
    this.type = type;
    this.pos = pos;
    this.imgUrl = imgUrl;

    this.suit = suit;
    this.descList = [];

    this.attributeList = [];
    sm > 0 && this.attributeList.push(new AttributeDO(AttributeDO.TYPE.数值.生命, sm))
    gj > 0 && this.attributeList.push(new AttributeDO(AttributeDO.TYPE.数值.攻击, gj))
    hx > 0 && this.attributeList.push(new AttributeDO(AttributeDO.TYPE.数值.会心, hx))
    fy > 0 && this.attributeList.push(new AttributeDO(AttributeDO.TYPE.数值.防御, fy))
  }

  /**
   *
   * @param name {string} 名字
   * @param gj {int} 攻击
   * @param hx {int} 会心
   * @param imgUrl {string} 图片url
   * @return {Equip}
   */
  static createArmsZP(name, gj = 0, hx = 0, imgUrl = undefined) {
    return new Equip(undefined, name, Equip.TYPE.ARMS.ZP, Equip.POS.ARMS, 0, gj, 0, hx, imgUrl)
  }

  /**
   *
   * @param suit {SuitDO} 套装
   * @param name {string} 名字
   * @param sm {int} 生命
   * @param gj {int} 攻击
   * @param fy {int} 防御
   * @param hx {int} 会心
   * @param imgUrl {string} 图片url
   * @return {Equip}
   */
  static createEquipUP(suit, name, sm = 0, gj = 0, fy = 0, hx = 0, imgUrl = undefined) {
    return new Equip(suit, name, Equip.TYPE.EQUIP, Equip.POS.UP, sm, gj, fy, hx, imgUrl)
  }

  /**
   *
   * @param suit {SuitDO} 套装
   * @param name {string} 名字
   * @param sm {int} 生命
   * @param gj {int} 攻击
   * @param fy {int} 防御
   * @param hx {int} 会心
   * @param imgUrl {string} 图片url
   * @return {Equip}
   */
  static createEquipMiddle(suit, name, sm = 0, gj = 0, fy = 0, hx = 0, imgUrl = undefined) {
    return new Equip(suit, name, Equip.TYPE.EQUIP, Equip.POS.MIDDLE, sm, gj, fy, hx, imgUrl)
  }

  /**
   *
   * @param suit {SuitDO} 套装
   * @param name {string} 名字
   * @param sm {int} 生命
   * @param gj {int} 攻击
   * @param fy {int} 防御
   * @param hx {int} 会心
   * @param imgUrl {string} 图片url
   * @return {Equip}
   */
  static createEquipDown(suit, name, sm = 0, gj = 0, fy = 0, hx = 0, imgUrl = undefined) {
    return new Equip(suit, name, Equip.TYPE.EQUIP, Equip.POS.DOWN, sm, gj, fy, hx, imgUrl)
  }

  /**
   *
   * @param type {AttributeDO.TYPE} 类型
   * @param data {number} 数值
   * @param time {number} 加成时间
   * @param desc {string} 条件说明
   * @return {Equip}
   */
  addAttribute(type, data, time = -1, desc = undefined) {
    data > 0 && this.attributeList.push(new AttributeDO(type, data, time, desc))
    return this;
  }

  /**
   *
   * @param desc {string} 条件说明
   * @return {Equip}
   */
  addAttributeOther(desc = undefined) {
    this.attributeList.push(new AttributeDO(AttributeDO.TYPE.其他, 0, undefined, desc))
    return this;
  }

  /**
   *
   * @param descList 描述
   * @return {Equip}
   */
  addDesc(...descList) {
    this.descList.push(descList)
    return this;
  }

  /**
   *
   * @return {AttributeDO[]}
   */
  getAttrList() {
    return this.attributeList
  }
}

Equip.TYPE = {
  EQUIP: 0x1,
  ARMS: {
    ZP: 0x2,
  },
}
Equip.POS = {
  ARMS: 0b0001,
  UP: 0b0010,
  MIDDLE: 0b0100,
  DOWN: 0b1000,
}

/** 套装 */
class SuitDO {
  /**
   *
   * @param name {string} 套装名
   */
  constructor(name) {
    this.id = BH3Common.getKey()

    this.name = name;
    this.descList = [];

    /** @type {{string: AttributeDO[]}} 套装名 */
    this.suitAttributeListMap = {}
  }

  /**
   *
   * @param name {string} 套装名
   * @return {SuitDO}
   */
  static create(name) {
    return new SuitDO(name)
  }

  /**
   *
   * @param descList 描述
   * @return {SuitDO}
   */
  addDesc(...descList) {
    this.descList.push(descList)
    return this;
  }

  /**
   *
   * @param num {int} 几件套
   * @param type {AttributeDO.TYPE} 类型
   * @param data {number} 数值
   * @param time {number} 加成时间
   * @param condition {string} 条件说明
   * @return {SuitDO}
   */
  addSuitAttribute(num, type, data, time = -1, condition = undefined) {
    this.suitAttributeListMap[num] == null && Vue.set(this.suitAttributeListMap, num, [])
    this.suitAttributeListMap[num].push(new AttributeDO(type, data, time, condition))
    return this;
  }

  /**
   *
   * @param num {int} 几件套
   * @param desc {string} 条件说明
   * @return {SuitDO}
   */
  addAttributeOther(num, desc = undefined) {
    this.suitAttributeListMap[num] == null && Vue.set(this.suitAttributeListMap, num, [])
    this.suitAttributeListMap[num].push(new AttributeDO(AttributeDO.TYPE.其他, 0, undefined, desc))
    return this;
  }
}

class Suit {
  /**
   *
   * @param suit {SuitDO}
   */
  constructor(suit) {
    this.key = BH3Common.getKey()

    this.suit= suit;
    /** @type {Equip[]} 套装名 */
    this.equipList= [];
  }

  /**
   *
   * @param equip {Equip}
   */
  addEquip(equip) {
    this.equipList.push(equip);
  }

  /**
   *
   * @param equip {Equip}
   */
  delEquip(equip) {
    this.equipList.splice(this.equipList.indexOf(equip), 1);
  }

  /**
   *
   * @return {AttributeDO[]}
   */
  getAttrList() {
    const allSuitAttributeList = []

    for (let [k, v] of Object.entries(this.suit.suitAttributeListMap)) {
      if (this.equipList.length >= k) {
        allSuitAttributeList.push(...v)
      }
    }
    return allSuitAttributeList
  }

  getName() {
    return this.suit.name
  }
}

/**
 * 一个完整角色代表一列
 */
class Attribute extends AttributeBase {
  constructor(attribute) {
    super(attribute.type);
    this.id = BH3Common.getKey()
    this.dataAll = undefined
    this.descList = []
    this.attributeList = []
  }

  /**
   *
   * @param attribute {AttributeDO}
   * @return {Attribute}
   */
  addAttr(attribute) {
    this.attributeList.push(attribute)
    Vue.set(this, 'dataAll', this.attributeList.reduce((r, n) => {
      n.desc && this.descList.push(n.desc)
      return r += (n.data || 0)
    }, 0))
    return this;
  }

  /**
   *
   * @return {string}
   */
  getAllDescHtml() {
    return `${this.descList.reduce((r,n,i) => r + (i !== 0 ? '<br/><br/>' : '') + (i + 1) + '.' + n, '')}`
  }
}

/** 一列的属性集合 代表一个完整装备穿搭 */
class EquipUse {
  constructor() {
    this.id = BH3Common.getKey()
    /** @type {{string: Equip}}*/
    this.posEquipMap = {}
    /** @type {{string: Attribute}}*/
    this.typeAttrMap = {}
    /** @type {EquipUse}*/
    this.equipUseR = undefined
  }

  /**
   * 主要计算装备变更信息
   * @return {EquipUse}
   */
  calcEquip() {
    Vue.set(this, 'typeAttrMap', {})
    const typeAttrMap = this.typeAttrMap;
    /** 属性提出及记录方法 @param attr {AttributeDO} */
    const attributeFunc = attr => {
      util.get(typeAttrMap, attr.type, () => new Attribute(attr)).addAttr(attr)
    }
    /** @type {{string: Suit}}*/
    const suitMap = {}
    // 将装备所有属性提出
    Object.values(this.posEquipMap).forEach(v => {
      v.suit && util.get(suitMap, v.suit.name, () => new Suit(v.suit)).addEquip(v)
      v.getAttrList().forEach(attributeFunc)
    })
    // 将套装所有属性提出
    Object.values(suitMap).forEach(s => s.getAttrList().forEach(attributeFunc))
    return this
  }

  /**
   *
   * @param equip {Equip}
   * @return {EquipUse}
   */
  setEquip(equip) {
    Vue.set(this.posEquipMap, equip.pos, equip)
    this.calcEquip()
    return this
  }

  /**
   *
   * @param attribute {Attribute}
   * @return {Attribute}
   */
  getDataAll(attribute) {
    return util.nullable(this.typeAttrMap[attribute.type]).map(a => a.dataAll).get()
  }

  /**
   *
   * @param attribute {Attribute}
   * @return {boolean}
   */
  hasAttr(attribute) {
    return !!this.typeAttrMap[attribute.type]
  }

  /**
   *
   * @param attribute {Attribute}
   * @return {Attribute}
   */
  getAttr(attribute) {
    return this.typeAttrMap[attribute.type]
  }

  compareWith(equipUseR) {
    Vue.set(this, 'equipUseR', equipUseR)
  }

  compareDataAll(attribute) {
    return (this.getDataAll(attribute) || 0) - (this.equipUseR.getDataAll(attribute) || 0)
  }
}

class EquipContext {
  constructor() {
    /** @type {EquipUse[]} */
    this.equipUseList = []
  }


  /** @return {AttributeBase[]} */
  getAllAttributeList() {
    return this.equipUseList
      .flatMap(eu => Object.keys(eu.typeAttrMap))
      .filter((n, i, l) => l.indexOf(n) === i)
      .map(type => new AttributeBase(type * 1))
      .sort((l, r) => l.getOrder() - r.getOrder())
  }

  /**
   * @param equipUseArr {EquipUse}
   * @return {EquipContext}
   */
  addEquipUse(...equipUseArr) {
    this.equipUseList.push(...equipUseArr)
    return this;
  }

  /**
   * @param equipUse {EquipUse}
   * @return {EquipContext}
   */
  delEquipUse(equipUse) {
    this.equipUseList.splice(this.equipUseList.indexOf(equipUse), 1)
    return this;
  }
}












