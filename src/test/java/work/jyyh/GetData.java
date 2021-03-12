package work.jyyh;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.Getter;
import org.junit.Test;

import java.util.Objects;

/**
 * TODO
 * @author lgren
 * @since 2020-10-29 10:35 上午
 */
public class GetData {

    private static String getUrl(UrlEnum urlEnum, int... pageAndPageSize) {
        int page = 1;
        int pageSize = 100;
        if (pageAndPageSize.length > 0) {
            page = pageAndPageSize[0];
            if (pageAndPageSize.length > 1) {
                pageSize = pageAndPageSize[1];
            }
        }

        return String.format(Objects.requireNonNull(urlEnum.getUrlFormat(), "没有找到[" + urlEnum.name() + "]类型"), page, pageSize);
    }

    @Test
    public void getCookie() {
        HttpResponse response = HttpUtil.createGet("http://cdhrss.chengdu.gov.cn/").execute();
        System.out.println();
    }

    @Test
    public void getData() {
        // HttpUtil.createGet(getUrl(UrlEnum.政策文件))
        //
        //         .header("Accept",  "text/html, */*; q=0.01")
        //         .header("Accept-Encoding",  "gzip, deflate")
        //         .header("Accept-Language",  "zh-CN,zh;q=0.9")
        //         .header("Connection",  "keep-alive")
        //         .header("Host", "cdhrss.chengdu.gov.cn")
        //         .header("Referer", "http://cdhrss.chengdu.gov.cn/cdrsj/c109727/list_more.shtml")
        //         .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36 QQBrowser/4.5.123.400")
        //         .header("X-Requested-With", "XMLHttpRequest")
        //         .execute();

        HttpResponse response = HttpUtil.createGet(getUrl(UrlEnum.政策文件))
                // .header("Referer", "http://cdhrss.chengdu.gov.cn/cdrsj/c109727/list_more.shtml")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36 QQBrowser/4.5.123.400")
                // .cookie("yfx_c_g_u_id_10000063=_ck20021911000416157317775867171; azSsQE5NvspcS=5rndVQN72WtFoHBADNLSkNabO1Dp5mt83EOuwDzg2LCWfZK_aFAdCu_KsneEmvSD19nm4.ceMrfeKN8DsIVRaaA; yfx_c_g_u_id_10000057=_ck20062917482211519855593118601; yfx_f_l_v_t_10000057=f_t_1593424102143__r_t_1593424102143__v_t_1593424102143__r_c_0; Hm_lvt_a458cc5cc881b98da2a16b6c121539a2=1593424133; fp_ver=4.7.14; BSFIT_EXPIRATION=1602335856881; BSFIT_DEVICEID=ByZED7pnzLF7U0SySknPVqLTkUqmEBEVP3mm7_uefqj2Nml5RPzOvnCbEWe-eNLaIcPmPv4yHBl6AuozFxenBed_fA6ZRDaJ35fPm2KhyCnXdvNh8cut9x3LmNS3e5nXSj1rCu-XuPOAJZsTaLXMN_urVuPpkdhq; yfx_f_l_v_t_10000063=f_t_1582081204602__r_t_1603938366410__v_t_1603938366410__r_c_6; azSsQE5NvspcT=5UyLL325FtoVqqqmTQbPG5qhPhqybb4Wxeptua9ED5_90VQ3SR4BPYhwfeiMqdJj6cl1odOHXyOLyRKsNFO9FQDAsN86au7QRTWkzL0co1zbPnAftL_AIjIRojFiESciWh1mtGt1unx3cvFI1WpFI5zOzaH2YPVb4TZE3MAe3CbWThaQtliZTThDEQ1WlJUNMErpsINC8Gr47kHAB5nSz7CBqo8Qed7U9YjmXQuW0ZGgig_38I.LJqpac2B7qFG549HazQyf9X3GxWXqUCivuAJXPNPs1_2uwUYZFJ9gCak7xMSS49XRD1a8ll7C.ThfFg")
                .execute();
        System.out.println(response.body());
    }


    /**
     * 成都市人力资源和社会保障局 http://cdhrss.chengdu.gov.cn/ 数据
     */
    enum UrlEnum {
        政策文件("http://cdhrss.chengdu.gov.cn/es-search/search/e444eaeca74c4be6a186e84834d16a7b?_template=zhaofa/cdsrs_list&_isAgg=1&_pageSize=%2$s&page=%1$s"),
        通知公告("http://cdhrss.chengdu.gov.cn/es-search/search/102bf310355e471c9f5699f8e09754de?_template=zhaofa/cdsrs_list&_isAgg=1&_pageSize=%2$s&page=%1$s");
        @Getter
        private String urlFormat;

        UrlEnum(String urlFormat) {
            this.urlFormat = urlFormat;
        }
    }
}
