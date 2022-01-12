const bh3Map = {};
bh3Map.armsMap = {
  武器: Equip.createArmsZP('武器'),
  蓝焰银隼0019: Equip.createArmsZP('蓝焰银隼0019', 292, 17, 'https://uploadstatic.mihoyo.com/bh3-wiki/2021/12/06/50494840/0270b3cb79ae6697864589ad3b3f1f65_3466700950433153133.png')
    .addDesc('苍蓝光啸', '蓄力炮，一层：单发炮弹，二层：双发更强炮弹，三层：三发超强炮弹。角色全伤害提高30.0%，使用QTE造成伤害时提高全场目标受到的冰冻元素伤害15.0%，持续10秒，效果持续期间，角色使用必杀技命中目标时，效果延长10秒，每个目标最多延长1次（该效果唯一生效，不可叠加）')
    .addDesc('蓄能轰击', '[能量消耗:0][冷却:16秒]角色向摇杆方向冲锋一段距离后召唤武器，发射一发能量弹，命中目标后造成400%攻击力的冰冻元素范围伤害。能量弹穿过次生银翼的分裂屏障后会变为强化能量弹，命中目标造成400%攻击力的冰冻元素伤害，并产生一个大范围黑洞，将周围敌人拉近后爆炸，造成400.0%攻击力的冰冻元素伤害，强化能量弹命中时减少武器技能2.0秒冷却时间。次生银翼使用时，释放武器技能后可以直接衔接普通攻击地面第四段或空中第三段')
    .addAttribute(AttributeDO.TYPE.加成.全伤, 30)
    .addAttribute(AttributeDO.TYPE.易伤.冰伤, 15, 10, '使用QTE造成伤害时提高全场目标受到的冰冻元素伤害15.0%，持续10秒，效果持续期间，角色使用必杀技命中目标时，效果延长10秒，每个目标最多延长1次')
  ,
  真理之钥: Equip.createArmsZP('真理之钥', 294, 19, 'https://uploadstatic.mihoyo.com/bh3-wiki/2021/09/07/77124895/75d78f817fe9bb792c7a8136d5856cd8_7784376157397884319.png')
    .addDesc('万象蓝图', '蓄力炮，一层：单发炮弹，二层：双发更强炮弹，三层：三发超强炮弹。角色造成的冰冻元素伤害提高30%')
    .addDesc('理性造物', '[能量消耗:5][冷却:25秒]构造武器攻击敌人，造成500.0%+9*85.0%+300%攻击力的冰冻元素伤害，最后一击将其冻结6.0秒；使用后，角色造成的全提高35%，持续7秒。理之律者使用时，主动技能会使敌人进入持续7秒的解析状态，使用必杀技时，立即恢复5点能量值')
    .addAttribute(AttributeDO.TYPE.加成.冰伤, 30)
    .addAttribute(AttributeDO.TYPE.加成.全伤, 35, 7, '使用后，角色造成的全提高35%，持续7秒。理之律者使用时，主动技能会使敌人进入持续7秒的解析状态，使用必杀技时，立即恢复5点能量值')
  ,
};
bh3Map.suitMap = {
  真理之翼套装: SuitDO.create('真理之翼套装')
    .addDesc('武装解构', '装备该套圣痕下位时，释放必杀技或造成分支攻击伤害也可获得一层【隼翼】效果，冷却时间3秒，每种触发条件冷却时间独立计算。【隼翼】效果达到满层时，提高角色全伤害20.0%。QTE叠加【隼翼】效果后，若【隼翼】效果达到满层，该套装下位圣痕的冰冻元素伤害提高效果每层额外提高10.0%')
    .addDesc('特别企划', '普通攻击（包括蓄力和分支）时命中敌人时额外造成25.0%攻击力的冰冻元素伤害，对每个目标的触发间隔0.3秒，【隼翼】效果达到满层时，角色每秒回复30.0点生命')
    .addSuitAttribute(2, AttributeDO.TYPE.加成.全伤, 20, undefined, '【隼翼】效果达到满层时，提高角色全伤害20.0%')
    .addSuitAttribute(2, AttributeDO.TYPE.加成.冰伤, 30, undefined, '若【隼翼】效果达到满层，该套装下位圣痕的冰冻元素伤害提高效果每层额外提高10.0%')
    .addAttributeOther(2, '装备该套圣痕下位时，释放必杀技或造成分支攻击伤害也可获得一层【隼翼】效果，冷却时间3秒，每种触发条件冷却时间独立计算。')
    .addSuitAttribute(3, AttributeDO.TYPE.百分比数值.冰伤, 25, undefined, '普通攻击（包括蓄力和分支）时命中敌人时额外造成25.0%攻击力的冰冻元素伤害，对每个目标的触发间隔0.3秒')
    .addAttributeOther(3, '【隼翼】效果达到满层时，角色每秒回复30.0点生命')
  ,
};
bh3Map.equipMap = {
  圣痕: {
    上: Equip.createEquipUP(null, '圣痕-上'),
    中: Equip.createEquipMiddle(null, '圣痕-中'),
    下: Equip.createEquipDown(null, '圣痕-下'),
  },
  次生: {
    上: Equip.createEquipUP(bh3Map.suitMap.真理之翼套装, '布洛妮娅 · 次生银翼(上)', 386, 113, 66, undefined, 'https://uploadstatic.mihoyo.com/bh3-wiki/2021/12/06/6100274/d8aa1d602fd11640c35dd7551fdd876c_5991082007261953531.png')
      .addDesc('【零号玩家】全伤害提高25.0%，分支攻击命中时会召唤一只飞鸟俯冲向目标，对小范围内敌人造成200.0%攻击力冰冻元素伤害，视为分支伤害，冷却时间8秒')
      .addAttribute(AttributeDO.TYPE.加成.全伤, 25)
      .addAttribute(AttributeDO.TYPE.百分比数值.冰伤, 200, undefined, '分支攻击命中时会召唤一只飞鸟俯冲向目标，对小范围内敌人造成200.0%攻击力冰冻元素伤害，视为分支伤害，冷却时间8秒')
    ,
    中: Equip.createEquipMiddle(bh3Map.suitMap.真理之翼套装, '布洛妮娅 · 次生银翼(中)', 459, 0, 221, 8, 'https://uploadstatic.mihoyo.com/bh3-wiki/2021/12/06/50494840/f618951133824d28ab6d76ecaa6c6fcb_2793158675627625538.png')
      .addDesc('【自由骑士】全伤害提高30.0%，拥有【隼翼】效果时，角色受到的全伤害降低30.0%')
      .addAttribute(AttributeDO.TYPE.加成.全伤, 30)
      .addAttributeOther('拥有【隼翼】效果时，角色受到的全伤害降低30.0%'),
    下: Equip.createEquipDown(bh3Map.suitMap.真理之翼套装, '布洛妮娅 · 次生银翼(下)', 459, 0, 221, 8, 'https://uploadstatic.mihoyo.com/bh3-wiki/2021/12/06/50494840/472ee474acf3dcd71bb1a5cebdee1eda_6830077355360542884.png')
      .addDesc('【战士本能】造成QTE伤害时获得一层【隼翼】效果，每层提高冰冻元素伤害25.0%，持续20秒，冷却时间5秒，最多叠加三层，重复触发刷新持续时间')
      .addAttribute(AttributeDO.TYPE.加成.冰伤, 75, 20, '【战士本能】造成QTE伤害时获得一层【隼翼】效果，每层提高冰冻元素伤害25.0%，持续20秒，冷却时间5秒，最多叠加三层，重复触发刷新持续时间'),
  }
};
bh3Map.allEquipMap = {}
bh3Map.allEquipList = []
Object.values(bh3Map.equipMap).flatMap(o => Object.values(o)).forEach(n => (bh3Map.allEquipMap[n.name] = n) && bh3Map.allEquipList.push(n));
