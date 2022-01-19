package com.lgren.office.aspose;

import com.aspose.words.License;
import com.lgren.util.LgrenUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Consumer;

public class AsposeBase {
    public static void setLicense() {
        LgrenUtil.timer(d -> com.aspose.words.License.licenseGo());
        LgrenUtil.timer(d -> com.aspose.cells.License.licenseGo());
        LgrenUtil.timer(d -> com.aspose.slides.License.licenseGo());
        LgrenUtil.timer(d -> com.aspose.ocr.License.licenseGo());
    }

    /*
<License>
  <Data>
    <LicensedTo>Shanghai Hudun Information Technology Co., Ltd</LicensedTo>
    <EmailTo>317701809@qq.com</EmailTo>
    <LicenseType>Developer OEM</LicenseType>
    <LicenseNote>Limited to 1 developer.</LicenseNote>
    <OrderID>140714065432</OrderID>
    <UserID>266166</UserID>
    <OEM>This is a redistributable license</OEM>
    <Products>
      <Product>Aspose.Total for .NET</Product>
    </Products>
    <EditionType>Enterprise</EditionType>
    <SerialNumber>ea712420-fbb2-41e7-9ede-39f7b3a43e4a</SerialNumber>
    <SubscriptionExpiry>20150714</SubscriptionExpiry>
    <LicenseVersion>3.0</LicenseVersion>
    <LicenseInstructions>http://www.aspose.com/corporate/purchase/license-instructions.aspx</LicenseInstructions>
  </Data>
  <Signature>Jv8ksvWRYLPzqyQX6J5pAUk4VNc1sogN5xq3ep7Piiy0iGQXg3p8yiGILjPpyCn7G2fkpcnCWPPffoWKqELUc40CiQWsuSmvAuLpvdJCVArt41CpaHNPAUKhrpOzrruzMPWgOB/ByzBVyvUkXl5g2OYHS29gVFlCJ2Ps+p0LnIQ=</Signature>
</License>
*/
}
