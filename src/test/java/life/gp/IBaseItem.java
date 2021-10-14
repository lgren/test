package life.gp;

public abstract class IBaseItem {
    // 代码
    public abstract String getSymbol();
    // 名称
    public abstract String getName();
    // 涨幅
    public abstract Double getPercent();
    // 换手率
    public abstract Double getTurnoverRatio();
    // 流通市值/万
    public abstract Double getNmcW();
    // 换手率
    public abstract Double getCurrent();
    // 量比
    public abstract Double getVolumeRatio();
    // 量比
    public abstract void setVolumeRatio(Double volumeRatio);

    @Override
    public String toString() {
        return "{" +
                "代码:'" + getSymbol() + '\'' +
                "名称:'" + getName() + '\'' +
                "涨幅:'" + getPercent() + '\'' +
                "换手率:'" + getTurnoverRatio() + '\'' +
                "流通市值_万:'" + getNmcW() + '\'' +
                "换手率:'" + getCurrent() + '\'' +
                "量比:'" + getVolumeRatio() + '\'' +
                '}';
    }
}
